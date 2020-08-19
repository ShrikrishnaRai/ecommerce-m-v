package com.shree.ecommerce_m_v.utils.email.otp.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVal {

    private int code;
    private String message;
}
