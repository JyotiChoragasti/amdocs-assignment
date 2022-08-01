package com.amdocs.media.assignement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amdocs.media.assignement.entity.ProfileDO;

@Repository
public interface ProfileDao extends JpaRepository<ProfileDO, Integer> {

}
