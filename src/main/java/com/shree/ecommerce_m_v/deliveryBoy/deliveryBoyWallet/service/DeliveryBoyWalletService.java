package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.service;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.entity.DeliveryBoyInfoEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.model.dto.DeliveryBoyWalletDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.model.entity.DeliveryBoyWalletEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.repository.DeliveryBoyWalletRepository;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.service.mapper.DeliveryBoyWalletMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryBoyWalletService extends DeliveryBoyWalletMapper {

    @Autowired
    private DeliveryBoyWalletRepository deliveryBoyWalletRepository;


    public List<DeliveryBoyWalletDTO> getAllDeliveryBoysWallet() {
        return toDTOs(deliveryBoyWalletRepository.findAll());
    }

    public Double saveDeliveryBoyWallet(DeliveryBoyWalletDTO deliveryBoyWalletDTO) {
        return deliveryBoyWalletRepository.save(toEntity(deliveryBoyWalletDTO)).getCurrentAmount();
    }

    public DeliveryBoyWalletDTO getDeliveryBoyWalletById(long id) {
        return toDTO(deliveryBoyWalletRepository.getOne(id));
    }

    public DeliveryBoyWalletDTO updateDeliveryBoyWallet(long id, DeliveryBoyWalletDTO deliveryBoyWalletDTO) {

        DeliveryBoyWalletEntity deliveryBoyWalletEntity = deliveryBoyWalletRepository.getOne(id);
        if (deliveryBoyWalletDTO.getDeliveryBoyMergerDTO() != null) {
            deliveryBoyWalletEntity.setDeliveryBoy(DeliveryBoyInfoEntity.builder()
                    .deliveryBoyId(deliveryBoyWalletDTO.getDeliveryBoyMergerDTO().getDeliveryBoyId())
                    .name(deliveryBoyWalletDTO.getDeliveryBoyMergerDTO().getName())
                    .build());

        }
        deliveryBoyWalletEntity.setCurrentAmount(deliveryBoyWalletDTO.getCurrentAmount());
        deliveryBoyWalletRepository.saveAndFlush(deliveryBoyWalletEntity);

        return toDTO(deliveryBoyWalletEntity);
    }

    public String deleteDeliveryBoyWallet(long id) {
        deliveryBoyWalletRepository.deleteById(id);
        return "Delivery Boy Wallet with id :" + id + " deleted";
    }
}
