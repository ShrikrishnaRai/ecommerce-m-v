package com.shree.ecommerce_m_v.customer.wishlist.service;

import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import com.shree.ecommerce_m_v.customer.wishlist.model.DTO.WishlistDTO;
import com.shree.ecommerce_m_v.customer.wishlist.repository.WishlistRepository;
import com.shree.ecommerce_m_v.customer.wishlist.service.mapper.WishListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListServiceImpl extends WishListMapper implements  WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;


    @Override
    public String saveWishlist(WishlistDTO wishlistDTO) {
        wishlistRepository.save(toEntity(wishlistDTO));
        return ResponseValue.SAVE_SUCCESS;
    }

    @Override
    public List<WishlistDTO> getListOfWishList() {
        return toDTOs(wishlistRepository.findAll());
    }

    @Override
    public WishlistDTO getWishListByCustomerId(Long customerId) {

        return toDTO(wishlistRepository.getWishlistEntityByCustomerId(customerId));
    }

    @Override
    public WishlistDTO getWishListById(Long wishListId) {
        return toDTO(wishlistRepository.getOne(wishListId));
    }
}
