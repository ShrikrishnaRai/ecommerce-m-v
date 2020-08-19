package com.shree.ecommerce_m_v.customer.order.invoice.model.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceMergerDTO {

    private Long invoiceId;
    private String invoiceNumber;


}
