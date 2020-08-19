package com.shree.ecommerce_m_v.customer.shoppingCart.service.DTO;

import com.shree.ecommerce_m_v.customer.customer.model.DTO.CustomerMergerDTO;
import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import lombok.*;

@Data
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDTO {

    private Long shoppingCartId;
    private int quantity;
    private double grandTotal;
    private ProductMergerDTO productMergerDTO;
    private CustomerMergerDTO customerMergerDTO;

}
