package com.ecom.services;


import com.ecom.model.UserDtls;

public interface UserService {

	public UserDtls saveuser(UserDtls user);

	public UserDtls getUserByEmail(String email);
	
}