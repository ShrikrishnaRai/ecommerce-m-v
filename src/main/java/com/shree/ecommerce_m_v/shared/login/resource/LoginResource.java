package com.shree.ecommerce_m_v.shared.login.resource;

import com.shree.ecommerce_m_v.shared.login.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginResource {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody final LoginVM loginVM) throws Exception {
        return ResponseEntity.ok(loginService.login(loginVM.getEmail(), loginVM.getPassword()));
    }

}
