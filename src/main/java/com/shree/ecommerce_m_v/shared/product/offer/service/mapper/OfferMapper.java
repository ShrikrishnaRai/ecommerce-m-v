package com.shree.ecommerce_m_v.shared.product.offer.service.mapper;


import com.shree.ecommerce_m_v.vendor.vendor.service.dto.VendorMergerDTO;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import com.shree.ecommerce_m_v.shared.product.offer.service.dto.OfferDTO;
import com.shree.ecommerce_m_v.shared.product.offer.model.entity.OfferEntity;
import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class OfferMapper {

    private List<ProductEntity> productEntities=new ArrayList<>();

    public List<ProductEntity> getProductEntities(){
        return this.productEntities;
    }

    protected OfferEntity toEntity(OfferDTO offerDTO) {

        VendorEntity vendorEntity = new VendorEntity();
        if(offerDTO.getVendorMergerDTO()!=null){
            vendorEntity= VendorEntity.builder()
                    .vendorId(offerDTO.getVendorMergerDTO().getVendorId())
                    .vendorName(offerDTO.getVendorMergerDTO().getVendorName())
                    .build();
        }
        if(offerDTO.getProductMergerDTOList().size()!=0){
            for(ProductMergerDTO productMergerDTO:offerDTO.getProductMergerDTOList()){
                productEntities.add(ProductEntity.builder()
                        .productId(productMergerDTO.getProductId())
                        .productName(productMergerDTO.getProductName())
                        .build());
            }
        }
        return OfferEntity.builder()
                .offerName(offerDTO.getOfferName())
                .description(offerDTO.getDescription())
                .validFrom(offerDTO.getValidFrom())
                .validTo(offerDTO.getValidTo())
                .status(offerDTO.getStatus())
                .discountType(offerDTO.getDiscountType())
                .discountValue(offerDTO.getDiscountValue())
                .minimumOrder(offerDTO.getMinimumOrder())
                .vendorEntity(offerDTO.getVendorMergerDTO() !=null? vendorEntity :null)
                .productEntityList(offerDTO.getProductMergerDTOList().size()!=0? productEntities:null)
                .build();
    }


    protected OfferDTO toDTO(OfferEntity offerEntity) {

        VendorMergerDTO vendorMergerDTO = new VendorMergerDTO();
        if(offerEntity.getVendorEntity()!=null){
            vendorMergerDTO= VendorMergerDTO.builder()
                    .vendorId(offerEntity.getVendorEntity().getVendorId())
                    .vendorName(offerEntity.getVendorEntity().getVendorName())
                    .build();
        }
        List<ProductMergerDTO> productMergerDTOS=new ArrayList<>();
        if(offerEntity.getProductEntityList().size()!=0){
            for(ProductEntity productEntity:offerEntity.getProductEntityList()){
                productMergerDTOS.add(ProductMergerDTO.builder()
                        .productId(productEntity.getProductId())
                        .productName(productEntity.getProductName())
                        .build());
            }
        }
        return OfferDTO.builder()
                .offerId(offerEntity.getOfferId())
                .offerName(offerEntity.getOfferName())
                .description(offerEntity.getDescription())
                .validFrom(offerEntity.getValidFrom())
                .validTo(offerEntity.getValidTo())
                .status(offerEntity.getStatus())
                .discountType(offerEntity.getDiscountType())
                .discountValue(offerEntity.getDiscountValue())
                .minimumOrder(offerEntity.getMinimumOrder())
                .vendorMergerDTO(vendorMergerDTO)
                .productMergerDTOList(productMergerDTOS)
                .build();
    }
}
