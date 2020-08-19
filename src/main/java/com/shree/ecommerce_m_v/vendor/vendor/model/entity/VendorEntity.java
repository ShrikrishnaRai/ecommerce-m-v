package com.shree.ecommerce_m_v.vendor.vendor.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shree.ecommerce_m_v.config.security.user.model.UserEntity;
import com.shree.ecommerce_m_v.shared.product.offer.model.entity.OfferEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import com.shree.ecommerce_m_v.vendor.commission.model.entity.CommissionTypeEntity;
import com.shree.ecommerce_m_v.vendor.coupon.model.entity.CouponEntity;
import com.shree.ecommerce_m_v.vendor.packageTrans.model.entity.PackageTransEntity;
import com.shree.ecommerce_m_v.vendor.service.serviceProvider.model.entity.ServiceProviderEntity;
import com.shree.ecommerce_m_v.vendor.vendor.model.Address;
import com.shree.ecommerce_m_v.vendor.vendor.model.Contact;
import com.shree.ecommerce_m_v.vendor.vendor.model.Status;
import com.shree.ecommerce_m_v.vendor.vendor.model.VendorType;
import com.shree.ecommerce_m_v.vendor.vendorDocument.model.entity.VendorDocumentEntity;
import com.shree.ecommerce_m_v.vendor.vendorReview.model.entity.VendorReviewEntity;
import com.shree.ecommerce_m_v.vendor.vendorWallet.model.entity.VendorWalletEntity;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="VendorEntity")
@Table(name = "vendor_info")
public class VendorEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_id")
    private Long vendorId;

    private String vendorName;
    @Embedded
    private Address address;
    @Embedded
    private Contact contact;
    @NaturalId
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

    private double commissionAmount;
    private String image;

    @OneToOne(mappedBy = "vendorEntity",
            cascade = {CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
    private CommissionTypeEntity commissionType;

    @OneToOne
    @JoinColumn(name = "package_trans_id_fk", referencedColumnName = "package_trans_id")
    private PackageTransEntity packageTransEntity;

    @OneToMany(mappedBy = "vendorEntity", cascade = CascadeType.MERGE)
    List<CouponEntity> couponEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "vendorEntity", cascade = CascadeType.MERGE)
    List<OfferEntity> offerEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "vendorEntity")
    List<VendorReviewEntity> vendorReviewEntities = new ArrayList<>();

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="vendor_document_id_fk")
    private VendorDocumentEntity vendorDocumentEntity;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="vendor_wallet_id_fk")
    private VendorWalletEntity vendorWalletEntity;


    @OneToOne(mappedBy="vendorEntity",cascade = CascadeType.PERSIST)
    private ServiceProviderEntity serviceProviderEntity;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_fk", referencedColumnName = "id")
    @JsonIgnore
    private UserEntity userEntity;

    public VendorEntity(Long id, String vendorName) {
        this.vendorId = id;
        this.username = vendorName;
    }

    public void addVendorToCoupon(CouponEntity couponEntity){
        couponEntityList.add(couponEntity);
        couponEntity.setVendorEntity(this);
    }

    public void removeVendorFromCoupon(CouponEntity couponEntity){
        couponEntityList.remove(couponEntity);
        couponEntity.setVendorEntity(null);
    }

}
