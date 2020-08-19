package com.shree.ecommerce_m_v.customer.customer.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shree.ecommerce_m_v.config.security.user.model.UserEntity;
import com.shree.ecommerce_m_v.customer.complain.model.entity.ComplainEntity;
import com.shree.ecommerce_m_v.customer.couponUser.model.entity.CouponUser;
import com.shree.ecommerce_m_v.customer.customerWallet.model.entity.CustomerWalletEntity;
import com.shree.ecommerce_m_v.customer.order.order.model.entity.OrderEntity;
import com.shree.ecommerce_m_v.shared.product.offer.transaction.OfferTransactionEntity;
import com.shree.ecommerce_m_v.customer.order.orderService.model.entity.OrderServiceEntity;
import com.shree.ecommerce_m_v.customer.preBooking.model.entity.PreBookingEntity;
import com.shree.ecommerce_m_v.customer.subscriber.model.entity.SubscriberEntity;
import com.shree.ecommerce_m_v.shared.product.productReview.model.entity.ProductReviewEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import com.shree.ecommerce_m_v.customer.wishlist.model.entity.WishlistEntity;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CustomerEntity")
@Table(name = "customer")
public class CustomerEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long customerId;
    private String customerName;
    @NaturalId(mutable = true)
    private String contactNo;
    @Embedded
    private Address address;
    private String zipCode;
    @Email
    @NaturalId(mutable = true)
    private String email;
    @NaturalId(mutable = true)
    private String username;
    private String password;
    @NaturalId(mutable = true)
    private String verificationCode;
    private Status status;
    private String image;

    @OneToMany(mappedBy = "customerEntity")
    private List<OfferTransactionEntity> offerTransactionEntityList = new ArrayList<>();

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "customer_wallet_id_fk", referencedColumnName = "customer_wallet_id")
    private CustomerWalletEntity customerWalletEntity;

    @OneToMany(mappedBy = "customerEntity")
    private List<ComplainEntity> complainEntities = new ArrayList<>();

    @OneToMany(mappedBy = "customerEntity")
    private List<OrderServiceEntity> orderServiceEntities = new ArrayList<>();

    @OneToMany(mappedBy = "customerEntity")
    private List<PreBookingEntity> preBookingEntityList = new ArrayList<>();

    @ManyToMany(mappedBy = "customerEntityList")
    private List<CouponUser> couponUsers = new ArrayList<>();

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "wishlist_id_fk")
    private WishlistEntity wishlistEntity;

    @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.MERGE)
    private List<OrderEntity> orderEntities = new ArrayList<>();

    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "customerEntity")
    private SubscriberEntity subscriberEntity;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_fk", referencedColumnName = "id")
    @JsonIgnore
    private UserEntity userEntity;

    @OneToOne(mappedBy = "customerEntity")
    private ProductReviewEntity productReviewEntity;

    public void addComplainOfCustomer(ComplainEntity complainEntity) {
        complainEntities.add(complainEntity);
        complainEntity.setCustomerEntity(this);
    }

    public void addCustomerToPreBooking(PreBookingEntity preBookingEntity) {
        preBookingEntityList.add(preBookingEntity);
        preBookingEntity.setCustomerEntity(this);
    }

    public void addCustomerToOrderService(OrderServiceEntity orderServiceEntity) {
        orderServiceEntities.add(orderServiceEntity);
        orderServiceEntity.setCustomerEntity(this);
    }


}
