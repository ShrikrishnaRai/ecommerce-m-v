package com.shree.ecommerce_m_v.config.security.user.springSecurityUserDetails;

import com.shree.ecommerce_m_v.config.security.user.model.UserEntity;
import com.shree.ecommerce_m_v.customer.customer.service.CustomerService;
import com.shree.ecommerce_m_v.shared.login.model.AuthorityDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SpringSecurityUserDetails implements UserDetails {

    @Autowired
    private CustomerService customerService;

    private static final long serialVersionUID = -7131091473769477312L;
    private final String userName;
    private final String password;
    private final List<GrantedAuthority> authorities = new ArrayList<>();

    public SpringSecurityUserDetails(UserEntity userEntity) {
        this.userName = userEntity.getEmail();
        this.password = userEntity.getPassword();
        userEntity
                .getAuthorities()
                .forEach(authority -> {
                    Logger logger = LoggerFactory.getLogger(SpringSecurityUserDetails.class);
                    logger.info("role" + authority.getName());
                    AuthorityDetails.role = authority.getName();
                    authorities.add(new SimpleGrantedAuthority(authority.getName()));
                });
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
