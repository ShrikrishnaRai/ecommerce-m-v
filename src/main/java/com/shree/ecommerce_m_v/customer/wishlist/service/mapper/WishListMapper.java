package com.shree.ecommerce_m_v.customer.wishlist.service.mapper;

import com.shree.ecommerce_m_v.customer.customer.model.DTO.CustomerMergerDTO;
import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import com.shree.ecommerce_m_v.customer.wishlist.model.DTO.WishlistDTO;
import com.shree.ecommerce_m_v.customer.wishlist.model.entity.WishlistEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class WishListMapper {


    protected WishlistEntity toEntity(WishlistDTO wishlistDTO){
        CustomerEntity customerEntity= new CustomerEntity();
        if(wishlistDTO.getCustomerMergerDTO() != null){
            customerEntity=CustomerEntity.builder()
                    .customerId(wishlistDTO.getCustomerMergerDTO().getId())
                    .username(wishlistDTO.getCustomerMergerDTO().getUsername())
                    .build();
        }
        List<ProductEntity> productEntities=new ArrayList<>();
        if(wishlistDTO.getProductMergerDTOList().size() != 0){
            for(int i=0;i<wishlistDTO.getProductMergerDTOList().size();i++){
                productEntities.add(ProductEntity.builder()
                        .productId(wishlistDTO.getProductMergerDTOList().get(i).getProductId())
                        .productName(wishlistDTO.getProductMergerDTOList().get(i).getProductName())
                        .build());
            }
        }
        return WishlistEntity.builder()
                .wishListId(wishlistDTO.getWishListId())
                .customerEntity(wishlistDTO.getCustomerMergerDTO()!=null? customerEntity: null)
                .productEntityList(productEntities)
                .build();
    }

    protected WishlistDTO toDTO(WishlistEntity wishlistEntity){
        CustomerMergerDTO customerMergerDTO= new CustomerMergerDTO();
        if(wishlistEntity.getCustomerEntity() != null){
            customerMergerDTO=CustomerMergerDTO.builder()
                    .id(wishlistEntity.getCustomerEntity().getCustomerId())
                    .username(wishlistEntity.getCustomerEntity().getUsername())
                    .build();
        }
        List<ProductMergerDTO> productMergerDTOS=new ArrayList<>();
        if(wishlistEntity.getProductEntityList().size() != 0){
            for(int i=0;i<wishlistEntity.getProductEntityList().size();i++){
                productMergerDTOS.add(ProductMergerDTO.builder()
                        .productId(wishlistEntity.getProductEntityList().get(i).getProductId())
                        .productName(wishlistEntity.getProductEntityList().get(i).getProductName())
                        .build());
            }
        }
        return WishlistDTO.builder()
                .wishListId(wishlistEntity.getWishListId())
                .customerMergerDTO(customerMergerDTO)
                .productMergerDTOList(productMergerDTOS)
                .build();
    }

    protected List<WishlistDTO> toDTOs(List<WishlistEntity> wishlistEntities){
        return wishlistEntities.stream()
                .map(wishlistEntity -> toDTO(wishlistEntity))
                .collect(Collectors.toList());

    }
}
