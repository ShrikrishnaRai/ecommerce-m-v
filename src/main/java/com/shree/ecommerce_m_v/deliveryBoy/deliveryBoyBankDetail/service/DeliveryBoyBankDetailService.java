package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyBankDetail.service;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.entity.DeliveryBoyInfoEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyBankDetail.model.dto.DeliveryBoyBankDetailDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyBankDetail.model.entity.DeliveryBoyBankDetailEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyBankDetail.repository.DeliveryBoyBankDetailRepository;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyBankDetail.service.mapper.DeliveryBoyBankDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DeliveryBoyBankDetailService extends DeliveryBoyBankDetailMapper {

    @Autowired
    private DeliveryBoyBankDetailRepository deliveryBoyBankDetailRepository;

    public Page<DeliveryBoyBankDetailDTO> getAllDeliveryBoysBankDetails(int page) {
        Pageable pageable = PageRequest.of(page,10);
        Page<DeliveryBoyBankDetailEntity> result = deliveryBoyBankDetailRepository.findAll(pageable);
        return result.map(deliveryBoyBankDetailEntity -> toDTO(deliveryBoyBankDetailEntity));
    }

    public DeliveryBoyBankDetailDTO getDeliveryBoyBankDetailById(long id) {
        return toDTO(deliveryBoyBankDetailRepository.getOne(id));
    }

    public String saveDeliveryBoyBankDetail(DeliveryBoyBankDetailDTO deliveryBoyBankDetailDTO) {
        return deliveryBoyBankDetailRepository.save(toEntity(deliveryBoyBankDetailDTO)).getAccountNumber();
    }

    public DeliveryBoyBankDetailDTO updateDeliveryBoyBankDetail(long id, DeliveryBoyBankDetailDTO deliveryBoyBankDetailDTO) {

        DeliveryBoyBankDetailEntity deliveryBoyBankDetailEntity = deliveryBoyBankDetailRepository.getOne(id);
        if (deliveryBoyBankDetailDTO.getDeliveryBoyMergerDTO() != null) {
            deliveryBoyBankDetailEntity.setDeliveryBoy(DeliveryBoyInfoEntity.builder()
                    .deliveryBoyId(deliveryBoyBankDetailDTO.getDeliveryBoyMergerDTO().getDeliveryBoyId())
                    .name(deliveryBoyBankDetailDTO.getDeliveryBoyMergerDTO().getName())
                    .build());
        }
        deliveryBoyBankDetailEntity.setBankName(deliveryBoyBankDetailDTO.getBankName());
        deliveryBoyBankDetailEntity.setAccountNumber(deliveryBoyBankDetailDTO.getAccountNumber());
        deliveryBoyBankDetailEntity.setAccountHolder(deliveryBoyBankDetailDTO.getAccountHolder());
        deliveryBoyBankDetailRepository.save(deliveryBoyBankDetailEntity);

        return toDTO(deliveryBoyBankDetailEntity);
    }

    public String deleteDeliveryBoyBankDetail(long id) {
        deliveryBoyBankDetailRepository.deleteById(id);

        return "Delivery Boy Bank Detail with id :" + id + " deleted";
    }
}
