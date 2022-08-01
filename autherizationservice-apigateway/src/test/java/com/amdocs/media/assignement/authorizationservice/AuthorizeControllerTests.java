package com.amdocs.media.assignement.authorizationservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.amdocs.media.assignement.authorizationservice.conroller.AuthorizeController;
import com.amdocs.media.assignement.authorizationservice.entity.AuthorizeEntity;
import com.amdocs.media.assignement.authorizationservice.service.AuthorizeService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes= {AuthorizeControllerTests.class})
public class AuthorizeControllerTests {
	@Mock
	AuthorizeService authorizeService;

	@InjectMocks
	AuthorizeController authorizeController;

	@Test
	@Order(1)
	public void test_loginUserProfile() {
		AuthorizeEntity authorize1=new AuthorizeEntity("user1", "pwd1");
		when(authorizeService.loginUserProfile(authorize1)).thenReturn(authorize1);
		ResponseEntity<String> response=authorizeController.loginUserProfile(authorize1);
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals("OK",response.getBody());


	}



}
