package com.shree.ecommerce_m_v.customer.deliveryAddress.service;

import com.shree.ecommerce_m_v.customer.deliveryAddress.model.DTO.DeliveryAddressDTO;

import java.util.List;

public interface DeliveryAddressService {

    String saveDeliveryAddress(DeliveryAddressDTO deliveryAddressDTO);

    String deleteDeliveryAddress(Long deliveryAddressId);

    DeliveryAddressDTO getDeliveryAddressById(final Long deliveryAddressId);

    List<DeliveryAddressDTO> getListOfDeliveryAddress();




}
