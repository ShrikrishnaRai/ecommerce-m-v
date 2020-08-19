package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyDocument.service;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.entity.DeliveryBoyInfoEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyDocument.model.DTO.DeliveryBoyDocumentDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyDocument.model.entity.DeliveryBoyDocumentEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyDocument.repository.DeliveryBoyDocumentRepository;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyDocument.service.mapper.DeliveryBoyDocumentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DeliveryBoyDocumentImpl extends DeliveryBoyDocumentMapper implements DeliveryBoyDocumentService {

    @Autowired
    private DeliveryBoyDocumentRepository deliveryBoyDocumentRepository;

    @Override
    public String saveDeliveryBoyDocument(DeliveryBoyDocumentDTO deliveryBoyDocumentDTO) {
        return deliveryBoyDocumentRepository.save(toEntity(deliveryBoyDocumentDTO)).getVehicleImage();
    }

    @Override
    public Page<DeliveryBoyDocumentDTO> getAllDeliveryBoyDocuments(int page) {
        Pageable pageable = PageRequest.of(page,10);
        Page<DeliveryBoyDocumentEntity> result = deliveryBoyDocumentRepository.findAll(pageable);
        return result.map(deliveryBoyDocumentEntity -> toDTO(deliveryBoyDocumentEntity));
    }

    @Override
    public DeliveryBoyDocumentDTO findDeliveryBoyDocumentById(long id) {
        return toDTO(deliveryBoyDocumentRepository.getOne(id));
    }

    @Override
    public DeliveryBoyDocumentDTO updateDeliveryBoyDocument(long id, DeliveryBoyDocumentDTO deliveryBoyDocumentDTO) {

        DeliveryBoyDocumentEntity deliveryBoyDocumentEntity = deliveryBoyDocumentRepository.getOne(id);
        if (deliveryBoyDocumentDTO.getDeliveryBoyMergerDTO() != null) {
            deliveryBoyDocumentEntity.setDeliveryBoy(DeliveryBoyInfoEntity.builder()
                    .deliveryBoyId(deliveryBoyDocumentDTO.getDeliveryBoyMergerDTO().getDeliveryBoyId())
                    .name(deliveryBoyDocumentDTO.getDeliveryBoyMergerDTO().getName())
                    .build());
        }
        deliveryBoyDocumentEntity.setCitizenshipImage(deliveryBoyDocumentDTO.getCitizenshipImage());
        deliveryBoyDocumentEntity.setLicenseImage(deliveryBoyDocumentDTO.getLicenseImage());
        deliveryBoyDocumentEntity.setVehicleImage(deliveryBoyDocumentDTO.getVehicleImage());
        deliveryBoyDocumentEntity.setBluebookImage(deliveryBoyDocumentDTO.getBluebookImage());
        deliveryBoyDocumentRepository.save(deliveryBoyDocumentEntity);

        return toDTO(deliveryBoyDocumentEntity);
    }

    @Override
    public String deleteDeliveryBoyDocument(Long id) {
        deliveryBoyDocumentRepository.deleteById(id);
        return "Delivery Boy Document with id :" + id + " deleted";
    }
}
