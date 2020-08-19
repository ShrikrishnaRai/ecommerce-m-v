package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyDocument.service;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyDocument.model.DTO.DeliveryBoyDocumentDTO;
import org.springframework.data.domain.Page;

public interface DeliveryBoyDocumentService {

    String saveDeliveryBoyDocument(DeliveryBoyDocumentDTO deliveryBoyDocumentDTO);

    Page<DeliveryBoyDocumentDTO> getAllDeliveryBoyDocuments(int page);

    DeliveryBoyDocumentDTO findDeliveryBoyDocumentById(long id);

    DeliveryBoyDocumentDTO updateDeliveryBoyDocument(long id, DeliveryBoyDocumentDTO deliveryBoyDocumentDTO);

    String deleteDeliveryBoyDocument(Long id);
}
