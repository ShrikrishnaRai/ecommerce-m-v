package com.shree.ecommerce_m_v.shared.product.product.service.dto;

import com.shree.ecommerce_m_v.customer.preBooking.model.DTO.PreBookingMergerDTO;
import com.shree.ecommerce_m_v.shared.product.brand.service.dto.BrandMergerDTO;
import com.shree.ecommerce_m_v.shared.product.category.service.dto.CategoryMergerDTO;
import com.shree.ecommerce_m_v.shared.product.offer.service.dto.OfferMergerDTO;
import com.shree.ecommerce_m_v.shared.product.product.model.DiscountType;
import com.shree.ecommerce_m_v.shared.product.product.model.ProductType;
import com.shree.ecommerce_m_v.shared.product.product.model.Status;
import com.shree.ecommerce_m_v.shared.product.product.model.TaxType;
import com.shree.ecommerce_m_v.shared.product.productBundle.service.dto.ProductBundleMergerDTO;
import com.shree.ecommerce_m_v.shared.product.productImage.service.dto.ProductImageMergerDTO;
import com.shree.ecommerce_m_v.shared.product.productReview.service.dto.ProductReviewMergerDTO;
import com.shree.ecommerce_m_v.shared.product.productStock.service.dto.ProductStockMergerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Serializable {
    private static final long serialVersionUID = 2568697833523458042L;
    private long product_id;
    private String productCode;
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
    private ProductType productType;
    private double packagingFee;
    private String unit;
    private BrandMergerDTO brandMergerDTO;
    private CategoryMergerDTO categoryMergerDTO;
    private List<ProductReviewMergerDTO> productReviewMergerDTOS = new ArrayList<>();
    private List<ProductBundleMergerDTO> productBundleMergerDTOS = new ArrayList<>();
    private List<PreBookingMergerDTO> preBookingMergerDTOS = new ArrayList<>();
    private OfferMergerDTO offerMergerDTO;
    private ProductStockMergerDTO productStockMergerDTO;
    private List<ProductImageMergerDTO> productImageMergerDTOS = new ArrayList<>();
    private ProductDetailDTO productDetailDTOS;
    private String productImageUrl;
    private String productHeader;


    public ProductDto(long product_id, String productName) {
        this.product_id = product_id;
        this.productName = productName;
    }


}
