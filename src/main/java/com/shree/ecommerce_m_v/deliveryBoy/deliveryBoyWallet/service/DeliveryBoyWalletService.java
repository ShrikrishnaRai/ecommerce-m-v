package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.service;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.model.dto.DeliveryBoyWalletDTO;

import java.util.List;

public interface DeliveryBoyWalletService {


    List<DeliveryBoyWalletDTO> getAllDeliveryBoysWallet();

    Double saveDeliveryBoyWallet(DeliveryBoyWalletDTO deliveryBoyWalletDTO);

    DeliveryBoyWalletDTO getDeliveryBoyWalletById(long id);

    DeliveryBoyWalletDTO updateDeliveryBoyWallet(long id, DeliveryBoyWalletDTO deliveryBoyWalletDTO);

    String deleteDeliveryBoyWallet(long id);
}
