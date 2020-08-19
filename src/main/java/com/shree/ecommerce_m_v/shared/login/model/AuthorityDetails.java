package com.shree.ecommerce_m_v.shared.login.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityDetails {
    private int id;
    public static String role;
    public static int userId;
    public static String username;
    public static String userImageUrl;
}
