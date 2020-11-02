package com.shree.ecommerce_m_v.vendor.vendorWallet.service;

import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import com.shree.ecommerce_m_v.vendor.vendorWallet.service.dto.VendorWalletDTO;
import com.shree.ecommerce_m_v.vendor.vendorWallet.model.entity.VendorWalletEntity;
import com.shree.ecommerce_m_v.vendor.vendorWallet.repository.VendorWalletRepository;
import com.shree.ecommerce_m_v.vendor.vendorWallet.service.mapper.VendorWalletMapper;
import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VendorWalletService extends VendorWalletMapper {

    @Autowired
    private VendorWalletRepository vendorWalletRepository;


    public String saveVendorWallet(VendorWalletDTO vendorWalletDTO) {
        vendorWalletRepository.save(toEntity(vendorWalletDTO));
        return ResponseValue.SAVE_SUCCESS;
    }


    public String deleteVendorWallet(Long vendorWalletId) {
        vendorWalletRepository.deleteById(vendorWalletId);
        return ResponseValue.DELETE_SUCCESS;
    }


    public VendorWalletDTO updateVendorWallet(Long vendorWalletId, VendorWalletDTO vendorWalletDTO) {
        VendorWalletEntity vendorWalletEntity=vendorWalletRepository.getOne(vendorWalletId);
        if(vendorWalletDTO.getVendorMergerDTO()!=null){
           vendorWalletEntity.setVendorEntity(VendorEntity.builder()
                    .vendorId(vendorWalletDTO.getVendorMergerDTO().getVendorId())
                    .vendorName(vendorWalletDTO.getVendorMergerDTO().getVendorName())
                    .build());
        }
        vendorWalletEntity.setCurrentAmount(vendorWalletDTO.getCurrentAmount());
        vendorWalletRepository.saveAndFlush(vendorWalletEntity);
        return toDTO(vendorWalletEntity);

    }


    public Page<VendorWalletDTO> getListOfVendorWallet(int page) {
        Pageable pageable= PageRequest.of(page,20);
        Page<VendorWalletEntity> vendorWalletEntities=vendorWalletRepository.findAll(pageable);
        return vendorWalletEntities.map(vendorWalletEntity -> toDTO(vendorWalletEntity));
    }


    public VendorWalletDTO getVendorWalletByVendorId(Long vendorId) {
        return toDTO(vendorWalletRepository.getVendorWalletEntityByVendorId(vendorId));
    }
}
