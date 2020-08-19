package com.shree.ecommerce_m_v.shared.product.product.model.entity;

import com.shree.ecommerce_m_v.customer.order.order.model.entity.OrderDetailEntity;
import com.shree.ecommerce_m_v.customer.preBooking.model.entity.PreBookingEntity;
import com.shree.ecommerce_m_v.customer.shoppingCart.model.entity.ShoppingCartEntity;
import com.shree.ecommerce_m_v.customer.wishlist.model.entity.WishlistEntity;
import com.shree.ecommerce_m_v.shared.product.brand.model.entity.BrandEntity;
import com.shree.ecommerce_m_v.shared.product.category.model.entity.CategoryEntity;
import com.shree.ecommerce_m_v.shared.product.offer.model.entity.OfferEntity;
import com.shree.ecommerce_m_v.shared.product.product.model.DiscountType;
import com.shree.ecommerce_m_v.shared.product.product.model.ProductType;
import com.shree.ecommerce_m_v.shared.product.product.model.Status;
import com.shree.ecommerce_m_v.shared.product.product.model.TaxType;
import com.shree.ecommerce_m_v.shared.product.productBundle.model.entity.ProductBundleEntity;
import com.shree.ecommerce_m_v.shared.product.productImage.model.entity.ProductImageEntity;
import com.shree.ecommerce_m_v.shared.product.productReview.model.entity.ProductReviewEntity;
import com.shree.ecommerce_m_v.shared.product.productStock.model.entity.ProductStockEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ProductEntity")
@Table(name = "product")
@Data
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductEntity extends AbstractAuditingEntity {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productCode;
    @Column(name = "product_name")
    private String productName;
    private String slug;
    private String quality;
    private double salePrice;
    private double purchasePrice;
    private DiscountType discountType;
    private double discountValue;
    private boolean isTaxable;
    private TaxType taxType;
    private double taxAmount;
    private boolean isPrebooking;
    private String description;
    private Status status;
    private double packagingFee;
    private String unit;
    private ProductType productType;
    @Column(length = 3000)
    private String productImageUrl;
    private String productHeader;


    @OneToOne(mappedBy = "productEntity", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private ProductDetailEntity productDetailEntities;

    @OneToOne(mappedBy = "productEntity", orphanRemoval = true)
    private ShoppingCartEntity shoppingCartEntity;


    @OneToOne(mappedBy = "productEntity", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private ProductStockEntity productStockEntity;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "brand_entity_fk", referencedColumnName = "brand_id")
    private BrandEntity brandEntity;

    @OneToMany(mappedBy = "productEntity", cascade = {CascadeType.ALL})
    private List<ProductImageEntity> productImageEntities = new ArrayList<>();

    @OneToMany(mappedBy = "productEntity")
    private List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "product_bundle_detail",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "bundle_id")
    )
    private List<ProductBundleEntity> productBundleEntityList = new ArrayList<>();

    @ManyToMany(mappedBy = "productEntities")
    private List<PreBookingEntity> preBookingEntityList = new ArrayList<>();


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "offer_entity_fk", referencedColumnName = "offer_id")
    private OfferEntity offerEntity;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_entity_fk", referencedColumnName = "category_id")
    private CategoryEntity categoryEntity;


    @OneToMany(mappedBy = "productEntity")
    private List<ProductReviewEntity> productReviewEntityList = new ArrayList<>();

    @ManyToMany(mappedBy = "productEntityList")
    private List<WishlistEntity> wishlistEntities;


    public ProductEntity(Long productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }


}
