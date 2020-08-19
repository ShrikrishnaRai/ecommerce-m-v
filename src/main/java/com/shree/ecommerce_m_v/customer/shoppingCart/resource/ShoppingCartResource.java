package com.shree.ecommerce_m_v.customer.shoppingCart.resource;

import com.shree.ecommerce_m_v.customer.shoppingCart.service.DTO.ShoppingCartDTO;
import com.shree.ecommerce_m_v.customer.shoppingCart.service.DTO.ShoppingCartResponseDTO;
import com.shree.ecommerce_m_v.customer.shoppingCart.service.ShoppingCartService;
import com.shree.ecommerce_m_v.utils.values.AuthoritiesConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartResource {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<List<ShoppingCartResponseDTO>> getShoppingCartByCustomerId(
            @RequestHeader(value = "Authorization", required = false) String Authorization,
            @PathVariable("customerId") Long customerId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(shoppingCartService.getShoppingCartByCustomerId(customerId));
    }

    @PostMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<String> saveShoppingCart(
            @RequestHeader(value = "Authorization", required = false) String Authorization,
            @RequestBody ShoppingCartDTO shoppingCartDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(shoppingCartService.saveShoppingCart(shoppingCartDTO));
    }

    @PutMapping("/{shoppingCartId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<ShoppingCartDTO> updateShoppingCart(
            @RequestHeader(value = "Authorization", required = false) String Authorization,
            @PathVariable("shoppingCartId") Long shoppingCartId,
            @RequestBody ShoppingCartDTO shoppingCartDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(shoppingCartService.updateShoppingCart(shoppingCartId, shoppingCartDTO));
    }

    @GetMapping("/availability")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<Boolean> checkCartAvailability(
            @RequestHeader(value = "Authorization", required = false) String Authorization,
            @RequestParam("customerId") Long customerId,
            @RequestParam("productId") Long productId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(shoppingCartService.checkCartAvailability(customerId, productId));
    }

    @DeleteMapping("/{cartId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<String> deleteProductFromCart(@RequestHeader(value = "Authorization", required = false) final String Authorization,
                                                        @PathVariable final Long cartId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(shoppingCartService.deleteProductFromShoppingCart(cartId));
    }
}
