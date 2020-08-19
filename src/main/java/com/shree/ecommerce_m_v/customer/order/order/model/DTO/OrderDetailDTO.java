package com.shree.ecommerce_m_v.customer.order.order.model.DTO;

import com.shree.ecommerce_m_v.shared.product.color.service.dto.ColorMergerDTO;
import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import com.shree.ecommerce_m_v.shared.product.size.service.dto.SizeMergerDTO;
import lombok.*;

@EqualsAndHashCode
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {

    private Long orderDetailId;
    private OrderMergerDTO orderMergerDTO;
    private ProductMergerDTO productMergerDTO;
    private SizeMergerDTO sizeMergerDTO;
    private ColorMergerDTO colorMergerDTO;
    private int totalQuantity;
    private double discountValue;
    private double salePrice;
    private double subTotal;
}
