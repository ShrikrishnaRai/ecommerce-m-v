package com.shree.ecommerce_m_v.customer.shoppingCart.service.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartMergerDTO {

    private Long shoppingCartId;
    private int quantity;
    private double grandTotal;

}
