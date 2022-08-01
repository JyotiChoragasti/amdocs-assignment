package com.amdocs.media.assignement.authorizationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amdocs.media.assignement.authorizationservice.dao.AuthorizeDao;
import com.amdocs.media.assignement.authorizationservice.entity.AuthorizeEntity;

@Service
@ResponseBody
public class AuthorizeService {


	@Autowired
	private AuthorizeDao authorizationDao;

	public AuthorizeEntity registerUserProfile(AuthorizeEntity authorizeEntity) {
		// TODO Auto-generated method stub
		return authorizationDao.save(authorizeEntity);

	}

	public AuthorizeEntity loginUserProfile(AuthorizeEntity authorizeEntity) {
		// TODO Auto-generated method stub
		return authorizationDao.findByUsername(authorizeEntity.getUsername());

	}

}
