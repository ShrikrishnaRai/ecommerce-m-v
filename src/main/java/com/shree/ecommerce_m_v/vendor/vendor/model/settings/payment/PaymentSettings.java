package com.shree.ecommerce_m_v.vendor.vendor.model.settings.payment;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class PaymentSettings {
    private boolean onCashDelivery;
    private boolean visa;
    private boolean masterCard;
    private boolean khalti;
    private boolean esewa;
    private boolean imePay;


}
