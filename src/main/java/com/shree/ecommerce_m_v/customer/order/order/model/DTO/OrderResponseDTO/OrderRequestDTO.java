package com.shree.ecommerce_m_v.customer.order.order.model.DTO.OrderResponseDTO;

import com.shree.ecommerce_m_v.customer.customer.model.DTO.CustomerMergerDTO;
import com.shree.ecommerce_m_v.customer.deliveryAddress.model.DTO.DeliveryAddressMergerDTO;
import com.shree.ecommerce_m_v.customer.order.deliveryRate.model.DTO.DeliveryRateMergerDTO;
import com.shree.ecommerce_m_v.customer.order.invoice.model.DTO.InvoiceMergerDTO;
import com.shree.ecommerce_m_v.customer.order.order.model.DTO.OrderDetailDTO;
import com.shree.ecommerce_m_v.customer.order.order.model.entity.OrderStatus;
import com.shree.ecommerce_m_v.customer.order.order.model.entity.TaxType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    private LocalDate deliveryDate;
    private String deliveryInstruction;
    private double couponAmount;
    private double discountAmount;
    private double discountPercentage;
    private double totalAmount;
    private double subTotal;
    private TaxType taxType;
    private double taxValue;
    private double extraCharge;
    private OrderStatus orderStatus;


    private DeliveryAddressMergerDTO deliveryAddressMergerDTO;

    private DeliveryRateMergerDTO deliveryRateMergerDTO;

    private InvoiceMergerDTO invoiceMergerDTO;

    private CustomerMergerDTO customerMergerDTO;

    private List<OrderDetailDTO> orderDetailDTO;
}
