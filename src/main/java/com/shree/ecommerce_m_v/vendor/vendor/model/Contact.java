package com.shree.ecommerce_m_v.vendor.vendor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Contact {

    @Column(unique = true)
    private String contactNo;
    @Column(unique = true)
    private String email;

}
