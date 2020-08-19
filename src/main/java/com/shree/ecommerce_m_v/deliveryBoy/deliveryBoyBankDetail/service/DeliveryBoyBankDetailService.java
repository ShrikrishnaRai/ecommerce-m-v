package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyBankDetail.service;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyBankDetail.model.dto.DeliveryBoyBankDetailDTO;
import org.springframework.data.domain.Page;

public interface DeliveryBoyBankDetailService {

    Page<DeliveryBoyBankDetailDTO> getAllDeliveryBoysBankDetails(int page);

    DeliveryBoyBankDetailDTO getDeliveryBoyBankDetailById(long id);

    String saveDeliveryBoyBankDetail(DeliveryBoyBankDetailDTO deliveryBoyBankDetailDTO);

    DeliveryBoyBankDetailDTO updateDeliveryBoyBankDetail(long id, DeliveryBoyBankDetailDTO deliveryBoyBankDetailDTO);

    String deleteDeliveryBoyBankDetail(long id);


}
