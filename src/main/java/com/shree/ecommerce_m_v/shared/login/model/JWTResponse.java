package com.shree.ecommerce_m_v.shared.login.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class JWTResponse implements Serializable {
    private static final long serialVersionUID = 5354650810202112242L;
    private  String jwt;
    private  String role;
    private  String userName;
    private  String userImageUrl;
    private  int userId;

}
