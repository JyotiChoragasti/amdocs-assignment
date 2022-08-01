package com.amdocs.media.assignement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.amdocs.media.assignement.dao.ProfileDao;
import com.amdocs.media.assignement.entity.ProfileDO;
import com.amdocs.media.assignement.model.Profile;
import com.amdocs.media.assignement.model.UserAuthorization;

import reactor.core.publisher.Mono;

@Service
public class ProfileService {

	@Autowired
	KafkaTemplate<String,String> kafkaTemplate;

	@Autowired
	private WebClient.Builder webClientBuilder;

	@Autowired
	private ProfileDao profileDao;
	public String createUserProfile(ProfileDO profileDO) {
		profileDao.save(profileDO);
		return "User profile has been created successfully, UserId :"+profileDO.getUserId();

	}

	public String deleteUserProfile(Profile  profile) {
		ProfileDO profileDO=profileDao.getReferenceById(profile.getId());
		if(profileDO.isUpdateOrDelete()==true) {
			UserAuthorization userAuthorization= new UserAuthorization();
			userAuthorization.setUsername(profile.getUsername());
			userAuthorization.setPassword(profile.getPassword()); 
			Mono<String> response=webClientBuilder.build() .post()
					.uri("http://localhost:8082/assignement/login/")
					.body(Mono.just(userAuthorization),UserAuthorization.class).retrieve()
					.bodyToMono(String.class)
					.onErrorResume(WebClientResponseException.class,
							ex -> ex.getRawStatusCode() == 401 ? Mono.empty() : Mono.error(ex));


			profileDao.delete(profileDO);
			return "User profile has been deleted, UserId :"+profileDO.getUserId();


		}
		profileDao.delete(profileDO);
		return "User profile has been deleted, UserId :"+profileDO.getUserId();

	}

	public String updateUserProfile(Profile profile) {
		// TODO Auto-generated method stub
		ProfileDO profileDO=profileDao.getReferenceById(profile.getId());
		if(profileDO.isUpdateOrDelete()==true) {
			UserAuthorization userAuthorization= new UserAuthorization();
			userAuthorization.setUsername(profile.getUsername());
			userAuthorization.setPassword(profile.getPassword()); 
			Mono<String> response=webClientBuilder.build() .post()
					.uri("http://localhost:8082/assignement/login/")
					.body(Mono.just(userAuthorization),UserAuthorization.class).retrieve()
					.bodyToMono(String.class)
					.onErrorResume(WebClientResponseException.class,
							ex -> ex.getRawStatusCode() == 401 ? Mono.empty() : Mono.error(ex));

			profileDO.setAddress1(profile.getAddress1());
			profileDO.setPhoneNumber(profile.getPhoneNumber());
			profileDao.save(profileDO);
			return "User profile has been updated, UserId :"+profileDO.getUserId();


		}




		return "User profile has been updated, UserId :"+profileDO.getUserId();

	}

	public List<ProfileDO> getAllUserProfile() {
		// TODO Auto-generated method stub
		List<ProfileDO>  userProfileList=profileDao.findAll();
		return userProfileList;

	}
}
