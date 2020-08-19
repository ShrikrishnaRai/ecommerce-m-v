
package com.shree.ecommerce_m_v.shared.product.product.service.mapper;

import com.shree.ecommerce_m_v.customer.preBooking.model.DTO.PreBookingMergerDTO;
import com.shree.ecommerce_m_v.customer.preBooking.model.entity.PreBookingEntity;
import com.shree.ecommerce_m_v.shared.product.brand.model.entity.BrandEntity;
import com.shree.ecommerce_m_v.shared.product.brand.service.dto.BrandMergerDTO;
import com.shree.ecommerce_m_v.shared.product.category.model.entity.CategoryEntity;
import com.shree.ecommerce_m_v.shared.product.category.service.dto.CategoryMergerDTO;
import com.shree.ecommerce_m_v.shared.product.color.model.entity.ColorEntity;
import com.shree.ecommerce_m_v.shared.product.color.service.dto.ColorMergerDTO;
import com.shree.ecommerce_m_v.shared.product.offer.model.entity.OfferEntity;
import com.shree.ecommerce_m_v.shared.product.offer.service.dto.OfferMergerDTO;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductDetailEntity;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductDto;
import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductDetailDTO;
import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import com.shree.ecommerce_m_v.shared.product.productBundle.model.entity.ProductBundleEntity;
import com.shree.ecommerce_m_v.shared.product.productBundle.service.dto.ProductBundleMergerDTO;
import com.shree.ecommerce_m_v.shared.product.productImage.model.entity.ProductImageEntity;
import com.shree.ecommerce_m_v.shared.product.productImage.service.dto.ProductImageMergerDTO;
import com.shree.ecommerce_m_v.shared.product.productReview.model.entity.ProductReviewEntity;
import com.shree.ecommerce_m_v.shared.product.productReview.service.dto.ProductReviewMergerDTO;
import com.shree.ecommerce_m_v.shared.product.productStock.model.entity.ProductStockEntity;
import com.shree.ecommerce_m_v.shared.product.productStock.service.dto.ProductStockMergerDTO;
import com.shree.ecommerce_m_v.shared.product.size.model.entity.SizeEntity;
import com.shree.ecommerce_m_v.shared.product.size.service.dto.SizeMergerDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ProductMapper {


    protected ProductEntity toEntity(ProductDto productDTO) {
        BrandEntity brandEntity = new BrandEntity();
        if (productDTO.getBrandMergerDTO() != null) {
            brandEntity = BrandEntity.builder()
                    .brandId(productDTO.getBrandMergerDTO().getBrandId())
                    .brandName(productDTO.getBrandMergerDTO().getBrandName())
                    .build();
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        if (productDTO.getCategoryMergerDTO() != null) {
            categoryEntity = CategoryEntity.builder()
                    .categoryId(productDTO.getCategoryMergerDTO().getId())
                    .categoryName(productDTO.getCategoryMergerDTO().getCategoryName())
                    .build();
        }

        OfferEntity offerEntity = new OfferEntity();
        if (productDTO.getOfferMergerDTO() != null) {
            offerEntity = OfferEntity.builder()
                    .offerId(productDTO.getOfferMergerDTO().getOfferId())
                    .offerName(productDTO.getOfferMergerDTO().getOfferName())
                    .build();
        }
        ProductStockEntity productStockEntity = new ProductStockEntity();
        if (productDTO.getProductStockMergerDTO() != null) {
            productStockEntity = ProductStockEntity.builder()
                    .productStockId(productDTO.getProductStockMergerDTO().getProductStockId())
                    .totalQuantity(productDTO.getProductStockMergerDTO().getTotalQuantity())
                    .remainingQuantity(productDTO.getProductStockMergerDTO().getRemainingQuantity())
                    .build();
        }


        List<ProductBundleEntity> productBundleEntities = new ArrayList<>();
        if (productDTO.getProductBundleMergerDTOS().size() != 0) {
            for (ProductBundleMergerDTO productBundleMergerDTO : productDTO.getProductBundleMergerDTOS()) {
                productBundleEntities.add(ProductBundleEntity.builder()
                        .productBundleId(productBundleMergerDTO.getProductBundleId())
                        .productBundleCode(productBundleMergerDTO.getProductBundleCode())
                        .build());
            }
        }

        ProductEntity productEntity = ProductEntity.builder()
                .productId(productDTO.getProduct_id())
                .productCode(productDTO.getProductCode())
                .productName(productDTO.getProductName())
                .slug(productDTO.getSlug())
                .quality(productDTO.getQuality())
                .salePrice(productDTO.getSalePrice())
                .purchasePrice(productDTO.getPurchasePrice())
                .discountType(productDTO.getDiscountType())
                .discountValue(productDTO.getDiscountValue())
                .isTaxable(productDTO.isTaxable())
                .taxType(productDTO.getTaxType())
                .taxAmount(productDTO.getTaxAmount())
                .isPrebooking(productDTO.isPrebooking())
                .description(productDTO.getDescription())
                .status(productDTO.getStatus())
                .packagingFee(productDTO.getPackagingFee())
                .unit(productDTO.getUnit())
                .productType(productDTO.getProductType())
                .brandEntity(productDTO.getBrandMergerDTO() != null ? brandEntity : null)
                .categoryEntity(productDTO.getCategoryMergerDTO() != null ? categoryEntity : null)
                .offerEntity(productDTO.getOfferMergerDTO() != null ? offerEntity : null)
                .productStockEntity(productDTO.getProductBundleMergerDTOS() != null ? productStockEntity : null)
                .productBundleEntityList(productDTO.getProductBundleMergerDTOS() != null ? productBundleEntities : null)
                .productImageUrl(productDTO.getProductImageUrl())
                .productHeader(productDTO.getProductHeader())
                .build();

        List<ProductImageEntity> productImageEntities = new ArrayList<>();
        if (productDTO.getProductImageMergerDTOS().size() != 0) {
            for (ProductImageMergerDTO productImageMergerDTO : productDTO.getProductImageMergerDTOS()) {
                productImageEntities.add(ProductImageEntity.builder()
                        .productImageId(productImageMergerDTO.getProductImageId())
                        .productImage(productImageMergerDTO.getProductImage())
                        .productEntity(productEntity)
                        .build());
            }
        }

        List<SizeEntity> sizeEntityList = new ArrayList<>();
        if (productDTO.getProductDetailDTOS().getSizeEntity().size() != 0) {
            for (SizeMergerDTO sizeMergerDTO : productDTO.getProductDetailDTOS().getSizeEntity()) {
                sizeEntityList.add(SizeEntity.builder()
                        .sizeId(sizeMergerDTO.getSizeId())
                        .size(sizeMergerDTO.getSize())
                        .build());
            }
        }

        productEntity.setProductImageEntities(productDTO.getProductImageMergerDTOS().size() != 0 ? productImageEntities : null);
        productEntity.setProductDetailEntities(productDTO.getProductDetailDTOS() != null ?
                ProductDetailEntity.builder()
                        .sizeEntityList(
                                sizeEntityList
                        )
                        .colorEntity(productEntity.getProductDetailEntities().getColorEntity() != null ? (ColorEntity.builder()
                                .colorId(productEntity.getProductDetailEntities().getColorEntity().getColorId())
                                .colorName(productEntity.getProductDetailEntities().getColorEntity().getColorName())
                                .colorCode(productEntity.getProductDetailEntities().getColorEntity().getColorCode())
                                .build()) : null)
                        .productEntity(productEntity)
                        .totalQuantity(productDTO.getProductDetailDTOS().getTotalQuantity())
                        .remainingQuantity(productDTO.getProductDetailDTOS().getRemainingQuantity())
                        .isAvailable(productDTO.getProductDetailDTOS().isAvailable())
                        .build() : null);
        return productEntity;
    }

    protected ProductDto toDTO(ProductEntity productEntity) {
        BrandMergerDTO brandMergerDTO = new BrandMergerDTO();
        if (productEntity.getBrandEntity() != null) {
            brandMergerDTO = BrandMergerDTO.builder()
                    .brandId(productEntity.getBrandEntity().getBrandId())
                    .brandName(productEntity.getBrandEntity().getBrandName())
                    .build();
        }
        CategoryMergerDTO categoryMergerDTO = new CategoryMergerDTO();
        if (productEntity.getCategoryEntity() != null) {
            categoryMergerDTO = CategoryMergerDTO.builder()
                    .id(productEntity.getCategoryEntity().getCategoryId())
                    .categoryName(productEntity.getCategoryEntity().getCategoryName())
                    .build();
        }

        OfferMergerDTO offerMergerDTO = new OfferMergerDTO();
        if (productEntity.getOfferEntity() != null) {
            offerMergerDTO = OfferMergerDTO.builder()
                    .offerId(productEntity.getOfferEntity().getOfferId())
                    .offerName(productEntity.getOfferEntity().getOfferName())
                    .build();
        }
        ProductStockMergerDTO productStockMergerDTO = new ProductStockMergerDTO();
        if (productEntity.getProductStockEntity() != null) {
            productStockMergerDTO = ProductStockMergerDTO.builder()
                    .productStockId(productEntity.getProductStockEntity().getProductStockId())
                    .totalQuantity(productEntity.getProductStockEntity().getTotalQuantity())
                    .remainingQuantity(productEntity.getProductStockEntity().getRemainingQuantity())
                    .build();
        }


        List<ProductBundleMergerDTO> productBundleMergerDTOS = new ArrayList<>();
        if (productEntity.getProductBundleEntityList().size() != 0) {
            for (ProductBundleEntity productBundleEntity : productEntity.getProductBundleEntityList()) {
                productBundleMergerDTOS.add(ProductBundleMergerDTO.builder()
                        .productBundleId(productBundleEntity.getProductBundleId())
                        .productBundleCode(productBundleEntity.getProductBundleCode())
                        .build());
            }
        }

        List<ProductReviewMergerDTO> productReviewMergerDTOS = new ArrayList<>();
        if (productEntity.getProductReviewEntityList().size() != 0) {
            for (ProductReviewEntity productReviewEntity : productEntity.getProductReviewEntityList()) {
                productReviewMergerDTOS.add(ProductReviewMergerDTO.builder()
                        .productReviewId(productReviewEntity.getProductReviewId())
                        .rating(productReviewEntity.getRating())
                        .build());
            }
        }

        List<PreBookingMergerDTO> preBookingMergerDTOS = new ArrayList<>();
        if (productEntity.getPreBookingEntityList().size() != 0) {
            for (PreBookingEntity preBookingEntity : productEntity.getPreBookingEntityList()) {
                preBookingMergerDTOS.add(PreBookingMergerDTO.builder()
                        .preBookingId(preBookingEntity.getPreBookingId())
                        .quantity(preBookingEntity.getQuantity())
                        .build());
            }
        }

        List<SizeMergerDTO> sizeMergerDTOList = new ArrayList<>();
        if (productEntity.getProductDetailEntities() != null && productEntity.getProductDetailEntities().getSizeEntityList() != null) {
            for (SizeEntity sizeEntity : productEntity.getProductDetailEntities().getSizeEntityList()) {
                sizeMergerDTOList.add(SizeMergerDTO.builder()
                        .sizeId(sizeEntity.getSizeId())
                        .size(sizeEntity.getSize())
                        .build());
            }
        }
        List<ProductImageMergerDTO> productImageMergerDTOS = new ArrayList<>();
        if (productEntity.getProductImageEntities() != null) {
            for (ProductImageEntity pr : productEntity.getProductImageEntities()) {
                productImageMergerDTOS.add(ProductImageMergerDTO.builder()
                        .productImageId(pr.getProductImageId())
                        .productImage(pr.getProductImage())
                        .build());
            }
        }

        return ProductDto.builder()
                .product_id(productEntity.getProductId())
                .productCode(productEntity.getProductCode())
                .productName(productEntity.getProductName())
                .slug(productEntity.getSlug())
                .quality(productEntity.getQuality())
                .salePrice(productEntity.getSalePrice())
                .purchasePrice(productEntity.getPurchasePrice())
                .discountType(productEntity.getDiscountType())
                .discountValue(productEntity.getDiscountValue())
                .isTaxable(productEntity.isTaxable())
                .taxType(productEntity.getTaxType())
                .taxAmount(productEntity.getTaxAmount())
                .isPrebooking(productEntity.isPrebooking())
                .description(productEntity.getDescription())
                .status(productEntity.getStatus())
                .packagingFee(productEntity.getPackagingFee())
                .unit(productEntity.getUnit())
                .productType(productEntity.getProductType())
                .brandMergerDTO(brandMergerDTO)
                .categoryMergerDTO(categoryMergerDTO)
                .offerMergerDTO(offerMergerDTO)
                .productStockMergerDTO(productStockMergerDTO)
                .preBookingMergerDTOS(preBookingMergerDTOS)
                .productReviewMergerDTOS(productReviewMergerDTOS)
                .productBundleMergerDTOS(productBundleMergerDTOS)
                .productImageMergerDTOS(productImageMergerDTOS)
                .productDetailDTOS(productEntity.getProductDetailEntities() != null ?
                        ProductDetailDTO.builder()
                                .sizeEntity(sizeMergerDTOList)
                                .colorEntity(productEntity.getProductDetailEntities().getColorEntity() != null ? ColorMergerDTO.builder()
                                        .colorId(productEntity.getProductDetailEntities().getColorEntity().getColorId())
                                        .colorName(productEntity.getProductDetailEntities().getColorEntity().getColorName())
                                        .colorCode(productEntity.getProductDetailEntities().getColorEntity().getColorCode())
                                        .build() : new ColorMergerDTO())
                                .productEntity(ProductMergerDTO.builder()
                                        .productId(productEntity.getProductDetailEntities().getProductEntity().getProductId())
                                        .productName(productEntity.getProductDetailEntities().getProductEntity().getProductName())
                                        .build())
                                .totalQuantity(productEntity.getProductDetailEntities().getTotalQuantity())
                                .remainingQuantity(productEntity.getProductDetailEntities().getRemainingQuantity())
                                .isAvailable(productEntity.getProductDetailEntities().isAvailable())
                                .build() : new ProductDetailDTO())
                .productImageUrl(productEntity.getProductImageUrl())
                .productHeader(productEntity.getProductHeader())
                .build();

    }

    protected List<ProductDto> toDTOList(List<ProductEntity> productEntityList) {
        return productEntityList.stream()
                .map(productEntity -> toDTO(productEntity))
                .collect(Collectors.toList());
    }

    protected List<ProductMergerDTO> toMergerDTOList(List<ProductEntity> productEntityList) {
        return productEntityList.stream()
                .map(productEntity -> {
                    ProductMergerDTO productMergerDTO = new ProductMergerDTO();
                    productMergerDTO.setProductId(productEntity.getProductId());
                    productMergerDTO.setProductName(productEntity.getProductName());
                    return productMergerDTO;
                }).collect(Collectors.toList());
    }

    protected ProductMergerDTO toMergerDTO(ProductEntity productEntity) {
        return new ProductMergerDTO(productEntity.getProductId(),
                productEntity.getProductName());
    }
}
