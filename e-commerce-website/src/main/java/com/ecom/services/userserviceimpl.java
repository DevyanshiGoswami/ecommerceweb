package com.ecom.services;



import com.ecom.model.UserDtls;
import com.ecom.repositories.userepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class userserviceimpl implements userservice {

	@Autowired
	private userepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public UserDtls saveuser(UserDtls user) {
		user.setRole("ROLE_USER");
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		UserDtls saveuser = userRepository.save(user);
		return saveuser;
	}


	@Override
	public UserDtls getUserByEmail(String email) {
		return userRepository.findByEmail(email);}
}