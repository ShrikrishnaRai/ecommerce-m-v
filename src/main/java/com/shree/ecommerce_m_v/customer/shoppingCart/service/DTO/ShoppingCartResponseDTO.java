package com.shree.ecommerce_m_v.customer.shoppingCart.service.DTO;

import com.shree.ecommerce_m_v.customer.customer.model.DTO.CustomerMergerDTO;
import com.shree.ecommerce_m_v.shared.product.product.model.DTO.ProductResponseMergedDTO;
import lombok.*;

@Data
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartResponseDTO {

    private Long shoppingCartId;
    private int quantity;
    private double grandTotal;
    private ProductResponseMergedDTO productResponseMergedDTO;
    private CustomerMergerDTO customerMergerDTO;

}
