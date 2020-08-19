package com.shree.ecommerce_m_v.utils.paymentGateway.esewa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsewaEntity {

    private double amount;
    private double taxAmount;
    private double productServiceCharge;
    private double productDeliveryCharge;
    private double totalAmount;
    private int merchantCode;
    private String successUrl;
    private String failureUrl;

}
