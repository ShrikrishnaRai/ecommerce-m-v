package com.shree.ecommerce_m_v.vendor.vendor.service.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class PaymentSettingsDTO {

    private boolean onCashDelivery;
    private boolean visa;
    private boolean masterCard;
    private boolean khalti;
    private boolean esewa;
    private boolean imePay;

}
