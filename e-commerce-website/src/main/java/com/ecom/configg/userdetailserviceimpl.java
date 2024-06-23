package com.ecom.configg;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Service;
    
    import com.ecom.model.UserDtls;
import com.ecom.repositories.userepository;
    
    @Service
    public class userdetailserviceimpl implements UserDetailsService {
    
        @Autowired
        private userepository userRepository;
    
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
            UserDtls user = userRepository.findByEmail(username);
    
            if (user == null) {
                throw new UsernameNotFoundException("user not found");
            }
            return new customuser(user);
        }
    
    }   
