package com.shree.ecommerce_m_v.customer.shoppingCart.service;

import com.shree.ecommerce_m_v.customer.shoppingCart.service.DTO.ShoppingCartDTO;
import com.shree.ecommerce_m_v.customer.shoppingCart.service.DTO.ShoppingCartResponseDTO;

import java.util.List;

public interface ShoppingCartService {

    List<ShoppingCartResponseDTO> getShoppingCartByCustomerId(Long customerId);

    String saveShoppingCart(ShoppingCartDTO shoppingCartDTO);

    ShoppingCartDTO updateShoppingCart(Long shoppingCartId, ShoppingCartDTO shoppingCartDTO);

    boolean checkCartAvailability(Long customerId, Long productId);

    String deleteProductFromShoppingCart(Long productId);

}
