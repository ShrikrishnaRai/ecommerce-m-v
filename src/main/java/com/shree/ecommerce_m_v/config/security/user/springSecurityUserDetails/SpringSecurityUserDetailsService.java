package com.shree.ecommerce_m_v.config.security.user.springSecurityUserDetails;

import com.shree.ecommerce_m_v.config.security.user.repository.UserRepository;
import com.shree.ecommerce_m_v.config.security.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpringSecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = userRepository.findOneByEmailIgnoreCase(username);
        userEntityOptional.orElseThrow(() -> new UsernameNotFoundException("Not found:" + username));
        return userEntityOptional.map(SpringSecurityUserDetails::new).get();
    }
}

