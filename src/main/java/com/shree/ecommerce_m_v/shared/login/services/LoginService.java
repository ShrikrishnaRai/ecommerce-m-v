package com.shree.ecommerce_m_v.shared.login.services;

import com.shree.ecommerce_m_v.shared.login.model.JWTResponse;

public interface LoginService {
    JWTResponse login(String email, String password) throws Exception;
}
