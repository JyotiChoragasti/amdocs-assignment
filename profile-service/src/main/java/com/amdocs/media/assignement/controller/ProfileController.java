package com.amdocs.media.assignement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.amdocs.media.assignement.entity.ProfileDO;
import com.amdocs.media.assignement.model.Profile;
import com.amdocs.media.assignement.model.UserAuthorization;
import com.amdocs.media.assignement.service.ProfileService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/assignement")
public class ProfileController {
	@Autowired
	private ProfileService profileService;

	@Autowired
	private WebClient.Builder webClientBuilder;

	@PostMapping("/create")
	public String createUserProfile(@RequestBody   Profile profile) {


		UserAuthorization userAuthorization= new UserAuthorization();
		userAuthorization.setUsername(profile.getUsername());
		userAuthorization.setPassword(profile.getPassword()); 
		String response=webClientBuilder.build() .post()
				.uri("http://localhost:8082/assignement/login/")
				.body(Mono.just(userAuthorization),UserAuthorization.class).retrieve()
				.bodyToMono(String.class)
				.onErrorResume(WebClientResponseException.class,
						ex -> ex.getRawStatusCode() == 401 ? Mono.empty() : Mono.error(ex))
				.block();

		String status =null;
		if(response==null) {
			System.out.println("Authentication Failed"); 
			status="UNAUTHORIZED";
		}else {
			ProfileDO profileDO=new ProfileDO();
			profileDO.setAddress1(profile.getAddress1());
			profileDO.setPhoneNumber(profile.getPhoneNumber());
			profileDO.setUpdateOrDelete(true);
			status= profileService.createUserProfile(profileDO);
		}



		return status;
	}

	@PostMapping("/update")
	public ResponseEntity<String> updateUserProfile(@RequestBody  Profile  profile) {

		String response= profileService.updateUserProfile(profile);
		if(response==null) {
			System.out.println("Not Authorized to do update"); 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT_FOUND");
		}else {
			return ResponseEntity.status(HttpStatus.OK).body("User profile updated successfully\n");
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteUserProfile(@RequestBody  Profile  userId) {
		String response= profileService.deleteUserProfile(userId);
		if(response==null) {
			System.out.println("Not Authorized to do delete"); 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT_FOUND");
		}else {
			return ResponseEntity.status(HttpStatus.OK).body("User profile deleted successfully\n");
		}
	}

	@GetMapping("/getAll")
	public List<ProfileDO> getAllUserProfile() {
		return profileService.getAllUserProfile();
	}
}
