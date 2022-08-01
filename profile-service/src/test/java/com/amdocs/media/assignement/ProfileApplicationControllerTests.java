package com.amdocs.media.assignement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.amdocs.media.assignement.controller.ProfileController;
import com.amdocs.media.assignement.entity.ProfileDO;
import com.amdocs.media.assignement.model.Profile;
import com.amdocs.media.assignement.service.ProfileService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes= {ProfileApplicationControllerTests.class})
public class ProfileApplicationControllerTests {

	@Mock
	ProfileService profileService;
	
	@InjectMocks
	ProfileController profileController;
	
	@Test
	@Order(1)
	public void test_createUserProfile() {
		/*
		 * Profile profile=new Profile(); profile.setUsername("user1");
		 * profile.setPassword("pwd1"); profile.setPhoneNumber("67889632456");
		 * profile.setAddress1("Toronto"); ProfileDO profileDO=new ProfileDO();
		 * profileDO.setAddress1(profile.getAddress1());
		 * profileDO.setPhoneNumber(profile.getPhoneNumber());
		 * profileDO.setUpdateOrDelete(true);
		 * when(profileService.createUserProfile(profileDO)).
		 * thenReturn("User profile has been created successfully, UserId :"+profileDO.
		 * getUserId()); String response=profileController.createUserProfile(profile);
		 * assertEquals("User profile has been created successfully, UserId :"+profileDO
		 * .getUserId(),response);
		 */
		
	}
	
}
