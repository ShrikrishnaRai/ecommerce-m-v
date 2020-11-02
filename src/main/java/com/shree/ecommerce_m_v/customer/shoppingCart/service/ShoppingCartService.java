package com.shree.ecommerce_m_v.customer.shoppingCart.service;

import com.shree.ecommerce_m_v.customer.shoppingCart.service.DTO.ShoppingCartDTO;
import com.shree.ecommerce_m_v.customer.shoppingCart.model.entity.ShoppingCartEntity;
import com.shree.ecommerce_m_v.customer.shoppingCart.repository.ShoppingCartRepository;
import com.shree.ecommerce_m_v.customer.shoppingCart.service.DTO.ShoppingCartResponseDTO;
import com.shree.ecommerce_m_v.customer.shoppingCart.service.mapper.ShoppingCartMapper;
import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ShoppingCartService extends ShoppingCartMapper{


    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public List<ShoppingCartResponseDTO> getShoppingCartByCustomerId(Long customerId) {
        return toResponseDTOList(shoppingCartRepository.getShoppingCartEntityByCustomerId(customerId));
    }

    public String saveShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        shoppingCartRepository.save(toEntity(shoppingCartDTO));
        return ResponseValue.SAVE_SUCCESS;
    }

    public ShoppingCartDTO updateShoppingCart(Long shoppingCartId, ShoppingCartDTO shoppingCartDTO) {

        ShoppingCartEntity shoppingCartEntity = shoppingCartRepository.getOne(shoppingCartId);
        shoppingCartEntity.setQuantity(shoppingCartDTO.getQuantity());
        shoppingCartEntity.setGrandTotal(shoppingCartDTO.getGrandTotal());
//        if(shoppingCartDTO.getProductMergerDTOList().size()!=0){
//            List<ProductEntity> productEntities= new ArrayList<>();
//            for(ProductMergerDTO productMergerDTO:shoppingCartDTO.getProductMergerDTOList()){
//                productEntities.add(ProductEntity.builder()
//                        .productId(productMergerDTO.getProductId())
//                        .productName(productMergerDTO.getProductName())
//                        .build());
//                shoppingCartEntity.setProductEntities(productEntities);
//            }
//
//        }
        shoppingCartRepository.save(shoppingCartEntity);
        return toDTO(shoppingCartEntity);
    }

    public boolean checkCartAvailability(Long customerId, Long productId) {
        return shoppingCartRepository.checkCartAvailability(customerId, productId).size() != 0;
    }

    public String deleteProductFromShoppingCart(Long productId) {
        shoppingCartRepository.deleteById(productId);
        return ResponseValue.DELETE_SUCCESS;
    }

}
