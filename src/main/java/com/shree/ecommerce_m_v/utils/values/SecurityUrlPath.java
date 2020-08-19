package com.shree.ecommerce_m_v.utils.values;

public class SecurityUrlPath {
    public static final String[] MANAGEMENT_PUBLIC_GET = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/brand",
            "/color",
            "/product",
            "/product/category/**",
            "/product/**",
            "/brand",
            "/category",
            "/preBooking",
            "/productBundle",
            "/productReview",
            "/productStock",
            "/serviceCategory",
            "/productReview/product/**"
    };
    public static final String[] AUTHENTICATION_PUBLIC_POST = {
            "/login",
            "/vendor/signUp",
    };
}
