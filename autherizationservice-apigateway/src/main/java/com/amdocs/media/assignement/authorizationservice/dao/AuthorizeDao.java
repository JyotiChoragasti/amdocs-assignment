package com.amdocs.media.assignement.authorizationservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amdocs.media.assignement.authorizationservice.entity.AuthorizeEntity;

@Repository
public interface AuthorizeDao extends JpaRepository<AuthorizeEntity, String>{

	AuthorizeEntity findByUsername(String username);

}
