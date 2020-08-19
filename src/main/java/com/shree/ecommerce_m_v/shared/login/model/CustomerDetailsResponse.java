package com.shree.ecommerce_m_v.shared.login.model;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetailsResponse {

    private int id;
    private String customerFullName;
    private int customerId;
    private String customerDisplayPictureUrl;
}
