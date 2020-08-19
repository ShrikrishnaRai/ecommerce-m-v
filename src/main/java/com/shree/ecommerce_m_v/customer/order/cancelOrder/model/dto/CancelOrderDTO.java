package com.shree.ecommerce_m_v.customer.order.cancelOrder.model.dto;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CancelOrderDTO {

    private Long cancelOrderId;
    private String reason;

}
