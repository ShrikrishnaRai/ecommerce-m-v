package com.shree.ecommerce_m_v.customer.wishlist.model.DTO;

import com.shree.ecommerce_m_v.customer.customer.model.DTO.CustomerMergerDTO;
import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WishlistDTO {

    private Long wishListId;
    private CustomerMergerDTO customerMergerDTO;
    private List<ProductMergerDTO> productMergerDTOList= new ArrayList<>();


}
