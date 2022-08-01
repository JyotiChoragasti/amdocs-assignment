package com.amdocs.media.assignement.authorizationservice;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.amdocs.media.assignement.authorizationservice.dao.AuthorizeDao;
import com.amdocs.media.assignement.authorizationservice.entity.AuthorizeEntity;
import com.amdocs.media.assignement.authorizationservice.service.AuthorizeService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes= {AuthorizaeServiceTests.class})
class AuthorizaeServiceTests {

	@InjectMocks
	private AuthorizeService authorizeService;

	@Mock
	private AuthorizeDao authorizeDao;

	@Test
	@Order(1)
	public void registerUserProfileTest() {

		AuthorizeEntity authorize1=new AuthorizeEntity("user1", "pwd1");
		when(authorizeDao.save(authorize1)).thenReturn(authorize1);
		assertEquals(authorize1, authorizeService.registerUserProfile(authorize1));

	}

	@Test
	@Order(2)
	public void loginUserProfileTest() {

		AuthorizeEntity authorize1=new AuthorizeEntity("user1", "pwd1");
		String username="user1";
		when(authorizeDao.findByUsername(username)).thenReturn(authorize1);
		assertEquals(authorize1,authorizeService.loginUserProfile(authorize1));

	}



}
