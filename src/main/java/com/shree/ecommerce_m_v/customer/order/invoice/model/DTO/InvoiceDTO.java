package com.shree.ecommerce_m_v.customer.order.invoice.model.DTO;

import com.shree.ecommerce_m_v.customer.customer.model.DTO.CustomerMergerDTO;
import com.shree.ecommerce_m_v.customer.order.order.model.DTO.OrderMergerDTO;
import com.shree.ecommerce_m_v.vendor.vendor.service.dto.VendorMergerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {
    private long invoiceId;
    private String invoiceNumber;

    private String status;

    private OrderMergerDTO orderMergerDTO;

    private VendorMergerDTO vendorMergerDTO;

    private CustomerMergerDTO customerMergerDTO;

    private String pdf;

}
