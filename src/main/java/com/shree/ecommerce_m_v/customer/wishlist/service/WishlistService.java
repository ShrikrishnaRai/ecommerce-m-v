package com.shree.ecommerce_m_v.customer.wishlist.service;

import com.shree.ecommerce_m_v.customer.wishlist.model.DTO.WishlistDTO;

import java.util.List;

public interface WishlistService {

    String saveWishlist(WishlistDTO wishlistDTO);

    List<WishlistDTO> getListOfWishList();

    WishlistDTO getWishListByCustomerId(Long customerId);

    WishlistDTO getWishListById(Long wishListId);


}
