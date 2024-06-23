package com.ecom.services;



import com.ecom.model.UserDtls;
import com.ecom.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	@Override
	public UserDtls saveuser(UserDtls user) {
		user.setRole("ROLE_USER");
//		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(user.getPassword());
		UserDtls saveuser = userRepository.save(user);
		return saveuser;
	}


	@Override
	public UserDtls getUserByEmail(String email) {
		return userRepository.findByEmail(email);}
}