package com.shree.ecommerce_m_v.shared.login.services;

import com.shree.ecommerce_m_v.config.security.jwt.JwtUtil;
import com.shree.ecommerce_m_v.config.security.user.springSecurityUserDetails.SpringSecurityUserDetailsService;
import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.customer.customer.service.CustomerService;
import com.shree.ecommerce_m_v.shared.login.model.AuthorityDetails;
import com.shree.ecommerce_m_v.shared.login.model.JWTResponse;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import com.shree.ecommerce_m_v.vendor.vendor.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginService  {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final SpringSecurityUserDetailsService userDetailsService;

    private final CustomerService customerService;

    private final VendorRepository vendorRepository;


    @Autowired
    public LoginService(final AuthenticationManager authenticationManager,
                        final JwtUtil jwtUtil,
                        final SpringSecurityUserDetailsService userDetailsService, CustomerService customerService, VendorRepository vendorRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.customerService = customerService;
        this.vendorRepository = vendorRepository;
    }


    public JWTResponse login(final String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email,
                            password));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        JWTResponse jwtResponse = new JWTResponse();
        jwtResponse.setJwt(jwtUtil.generateToken(userDetailsService.loadUserByUsername(email)));
        jwtResponse.setRole(AuthorityDetails.role != null ? AuthorityDetails.role : "");
        if (AuthorityDetails.role.equals("ROLE_USER")) {
            CustomerEntity customerEntity = customerService.findCustomerByEmail(email);
            jwtResponse.setUserId((int) customerEntity.getCustomerId());
            jwtResponse.setUserName(customerEntity.getUsername());
            jwtResponse.setUserImageUrl(customerEntity.getImage());
        }
        if (AuthorityDetails.role.equals("ROLE_ADMIN")) {
            VendorEntity vendorEntity = vendorRepository.findVendorEntityByEmail(email);
            jwtResponse.setUserId(Math.toIntExact(vendorEntity.getVendorId()));
            jwtResponse.setUserName(vendorEntity.getUsername());
            jwtResponse.setUserImageUrl(vendorEntity.getImage());

        }
        return jwtResponse;

    }

}
