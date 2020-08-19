package com.shree.ecommerce_m_v.vendor.vendor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    private String area;
    private String street;
    private String district;
    private String state;
    private String country;
    private String zipCode;


}
