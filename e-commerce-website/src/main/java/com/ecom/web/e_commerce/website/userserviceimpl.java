package com.ecom.web.e_commerce.website;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class userserviceimpl implements userservice {

	@Autowired
	private userepository userRepository;

	@Override
	public UserDtls saveUser(UserDtls user) {
		UserDtls saveUser = userRepository.save(user);
		return saveUser;
	}

}