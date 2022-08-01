package com.amdocs.media.assignement.authorizationservice.conroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.media.assignement.authorizationservice.entity.AuthorizeEntity;
import com.amdocs.media.assignement.authorizationservice.service.AuthorizeService;

@RestController
@RequestMapping("/assignement")
public class AuthorizeController {
	@Autowired
	private AuthorizeService authorizeService;

	@PostMapping("/register")
	public AuthorizeEntity registerUserProfile(@RequestBody   AuthorizeEntity uuthorizeEntity) {
		return authorizeService.registerUserProfile(uuthorizeEntity);

	}


	@PostMapping("/login")

	public ResponseEntity<String> loginUserProfile(@RequestBody   AuthorizeEntity authorizeEntity) {
		AuthorizeEntity userObj=authorizeService.loginUserProfile(authorizeEntity);

		if(userObj!=null) {

			if(userObj.getPassword().equals(authorizeEntity.getPassword())) {
				//status="OK";
				return ResponseEntity.status(HttpStatus.OK).body("OK");
			}else {
				//status="UNAUTHORIZED";
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication Failed\n");
			}
		}else {
			//status="UNAUTHORIZED";
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication Failed\n");
		}


	}
}
