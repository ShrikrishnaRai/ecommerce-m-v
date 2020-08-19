package com.shree.ecommerce_m_v.customer.wishlist.resource;

import com.shree.ecommerce_m_v.customer.wishlist.model.DTO.WishlistDTO;
import com.shree.ecommerce_m_v.customer.wishlist.service.WishlistService;
import com.shree.ecommerce_m_v.utils.values.AuthoritiesConstants;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishList")
@Api(tags = "WishList" ,description = "Wishlist of Customer for products")
public class WishListResource {

    @Autowired
    private WishlistService wishlistService;

    @GetMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")" +
            "&& hasAuthority('" + AuthoritiesConstants.ADMIN + "\')")
    public ResponseEntity<List<WishlistDTO>> getListOfWishList(@RequestHeader(value = "Authorization",required = false) String Authorization){
        return ResponseEntity.status(HttpStatus.OK)
                .body(wishlistService.getListOfWishList());
    }


    @GetMapping("/{wishListId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")" +
            "&& hasAuthority('" + AuthoritiesConstants.ADMIN + "\')")
    public ResponseEntity<WishlistDTO> getWishListById(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                       @PathVariable("wishListId") Long wishListId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(wishlistService.getWishListById(wishListId));
    }

    @GetMapping("/{customerId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")" +
            "&& hasAuthority('" + AuthoritiesConstants.ADMIN + "\')")
    public ResponseEntity<WishlistDTO> getWishListByCustomerId(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                       @PathVariable("customerId") Long customerId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(wishlistService.getWishListByCustomerId(customerId));
    }



    @PostMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")

    public ResponseEntity<String> saveWishList(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                               @RequestBody WishlistDTO wishlistDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(wishlistService.saveWishlist(wishlistDTO));
    }
}
