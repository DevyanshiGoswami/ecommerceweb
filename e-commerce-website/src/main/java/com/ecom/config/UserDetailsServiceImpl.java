package com.ecom.config;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.core.GrantedAuthority;
        import org.springframework.security.core.authority.SimpleGrantedAuthority;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.security.core.userdetails.UsernameNotFoundException;
        import org.springframework.stereotype.Service;
        import com.ecom.model.UserDtls;
        import com.ecom.repositories.UserRepository;

        import javax.management.relation.Role;
        import java.util.ArrayList;
        import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDtls user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return new CustomUser(user);
    }
//     return new org.springframework.security.core.userdetails.User(
//             user.getName(),
//             user.getPassword(),
//             Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
//            );
}