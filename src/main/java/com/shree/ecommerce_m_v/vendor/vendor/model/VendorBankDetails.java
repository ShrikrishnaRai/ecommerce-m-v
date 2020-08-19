package com.shree.ecommerce_m_v.vendor.vendor.model;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Embeddable
public class VendorBankDetails {
    private Long vendorBankDetailsId;
    private String bankName;
    private String bankBranch;
    private String acNumber;
    private String acHolderName;

}
