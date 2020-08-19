package com.shree.ecommerce_m_v.customer.preBooking.service;

import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.customer.preBooking.model.DTO.PreBookingDTO;
import com.shree.ecommerce_m_v.customer.preBooking.model.entity.PreBookingEntity;
import com.shree.ecommerce_m_v.customer.preBooking.repository.PreBookingRepository;
import com.shree.ecommerce_m_v.customer.preBooking.service.mapper.PreBookingMapper;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PreBookingServiceImpl extends PreBookingMapper implements  PreBookingService {

    @Autowired
    private PreBookingRepository preBookingRepository;

    @Override
    public Page<PreBookingDTO> getListOfPreBookings(int page, String sortBy, String orderBy) {
        Pageable pageable = PageRequest.of(page,10, Sort.by(Sort.Direction.valueOf(orderBy),sortBy));
        Page<PreBookingEntity> result = preBookingRepository.findAll(pageable);
        return result.map(this::toDTO);
    }

    @Override
    public PreBookingDTO getPreBookingById(Long preBookingId) {
        return toDTO(preBookingRepository.getOne(preBookingId));
    }

    @Override
    public String savePreBooking(PreBookingDTO preBookingDTO) {
        preBookingRepository.save(toEntity(preBookingDTO));
        return ResponseValue.SAVE_SUCCESS;
    }

    @Override
    public PreBookingDTO updatePreBookingById(Long preBookingId, PreBookingDTO preBookingDTO) {
        PreBookingEntity preBookingEntity = preBookingRepository.getOne(preBookingId);
        preBookingEntity.setQuantity(preBookingDTO.getQuantity());
        preBookingEntity.setPrice(preBookingDTO.getPrice());
        preBookingEntity.setTotalPrice(preBookingDTO.getTotalPrice());
        preBookingEntity.setStatus(preBookingDTO.getStatus());
        if(preBookingDTO.getProductMergerDTOList() !=null){
            preBookingEntity.setProductEntities(preBookingDTO.getProductMergerDTOList()
                    .stream()
                    .map(productMergerDTO -> ProductEntity.builder()
                            .productId(productMergerDTO.getProductId())
                            .productName(productMergerDTO.getProductName())
                            .build())
                    .collect(Collectors.toList()));
        }
        if(preBookingDTO.getCustomerMergerDTO() !=null){
            preBookingEntity.setCustomerEntity(CustomerEntity.builder()
                    .customerId(preBookingDTO.getCustomerMergerDTO().getId())
                    .username(preBookingDTO.getCustomerMergerDTO().getUsername())
                    .build());
        }
        preBookingRepository.saveAndFlush(preBookingEntity);
        return toDTO(preBookingEntity);
    }

    @Override
    public String deletePreBookingById(Long preBookingId) {
        preBookingRepository.deleteById(preBookingId);
        return ResponseValue.DELETE_SUCCESS;
    }
}
