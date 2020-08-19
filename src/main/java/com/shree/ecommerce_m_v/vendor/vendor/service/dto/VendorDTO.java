package com.shree.ecommerce_m_v.vendor.vendor.service.dto;

import com.shree.ecommerce_m_v.vendor.commission.model.dto.CommissionMergerDTO;
import com.shree.ecommerce_m_v.vendor.coupon.service.dto.CouponMergerDTO;
import com.shree.ecommerce_m_v.vendor.vendor.model.Address;
import com.shree.ecommerce_m_v.vendor.vendor.model.Contact;
import com.shree.ecommerce_m_v.vendor.vendor.model.Status;
import com.shree.ecommerce_m_v.vendor.vendor.model.VendorType;
import com.shree.ecommerce_m_v.vendor.vendorDocument.service.dto.VendorDocumentMergerDTO;
import com.shree.ecommerce_m_v.vendor.vendorReview.service.dto.VendorReviewMergerDTO;
import com.shree.ecommerce_m_v.vendor.vendorWallet.service.dto.VendorWalletMergerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendorDTO implements Serializable {

    private static final long serialVersionUID = 916910473498817140L;
    private Long id;
    private String vendorName;
    @Embedded
    private Address address;
    @Embedded
    private Contact contact;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private VendorType vendorType;
    @Column(unique = true)
    private String token;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(unique = true)
    private String panNo;
    @Column(unique = true)
    private String taxClearanceNo;
    private String businessType;
    private String longitude;
    private String latitude;
    private CommissionMergerDTO commissionMergerDTO;
    private double commissionAmount;
    private String image;


    private List<CouponMergerDTO> couponMergerDTOS=new ArrayList<>();

    private List<VendorReviewMergerDTO> vendorReviewMergerDTOS=new ArrayList<>();

    private VendorWalletMergerDTO vendorWalletMergerDTO;

    private VendorDocumentMergerDTO vendorDocumentMergerDTO;

//    private List<Offer>

}
