package com.amdocs.media.assignement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProfileDO {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	@Column
    private String phoneNumber;
	
	@Column
    private String address1;
	
	@Column
	private boolean updateOrDelete;
 
 public ProfileDO() {
	 
 }
 

public ProfileDO(int userId, String phoneNumber, String address1, boolean updateOrDelete) {
	super();
	this.userId = userId;
	this.phoneNumber = phoneNumber;
	this.address1 = address1;
	this.updateOrDelete = updateOrDelete;
}


public int getUserId() {
	return userId;
}

public void setUserId(int userId) {
	this.userId = userId;
}

public String getPhoneNumber() {
	return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}

public String getAddress1() {
	return address1;
}

public void setAddress1(String address1) {
	this.address1 = address1;
}

public boolean isUpdateOrDelete() {
	return updateOrDelete;
}

public void setUpdateOrDelete(boolean updateOrDelete) {
	this.updateOrDelete = updateOrDelete;
}



}
