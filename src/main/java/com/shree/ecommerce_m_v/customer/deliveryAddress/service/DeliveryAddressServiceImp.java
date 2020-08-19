package com.shree.ecommerce_m_v.customer.deliveryAddress.service;

import com.shree.ecommerce_m_v.customer.deliveryAddress.model.DTO.DeliveryAddressDTO;
import com.shree.ecommerce_m_v.customer.deliveryAddress.repository.DeliveryAddressRepository;
import com.shree.ecommerce_m_v.customer.deliveryAddress.service.mapper.DeliveryAddressMapper;
import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryAddressServiceImp extends DeliveryAddressMapper implements DeliveryAddressService {

    @Autowired
    private DeliveryAddressRepository deliveryAddressRepository;

    @Override
    public String saveDeliveryAddress(DeliveryAddressDTO deliveryAddressDTO) {
        deliveryAddressRepository.save(toEntity(deliveryAddressDTO));
        return ResponseValue.SAVE_SUCCESS;
    }

    @Override
    public String deleteDeliveryAddress(Long deliveryAddressId) {
        deliveryAddressRepository.deleteById(deliveryAddressId);
        return ResponseValue.DELETE_SUCCESS;
    }

    @Override
    public DeliveryAddressDTO getDeliveryAddressById(Long deliveryAddressId) {
        return toDTO(deliveryAddressRepository.getOne(deliveryAddressId));
    }

    @Override
    public List<DeliveryAddressDTO> getListOfDeliveryAddress() {
        return deliveryAddressRepository.findAll()
                .stream()
                .map(deliveryAddressEntity -> toDTO(deliveryAddressEntity))
                .collect(Collectors.toList());
    }
}
