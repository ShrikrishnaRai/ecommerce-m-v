package com.shree.ecommerce_m_v.customer.customerWallet.service;

import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.customer.customerWallet.model.dto.CustomerWalletDTO;
import com.shree.ecommerce_m_v.customer.customerWallet.model.entity.CustomerWalletEntity;
import com.shree.ecommerce_m_v.customer.customerWallet.repository.CustomerWalletRepository;
import com.shree.ecommerce_m_v.customer.customerWallet.service.mapper.CustomerWalletMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerWalletService extends CustomerWalletMapper{

    @Autowired
    private CustomerWalletRepository customerWalletRepository;


    public CustomerWalletDTO getCustomerWalletById(long id) {
        return toDTO(customerWalletRepository.getOne(id));
    }

    public String saveCustomerWallet(CustomerWalletDTO customerWalletDTO) {
        customerWalletRepository.save(toEntity(customerWalletDTO));
        ;
        return "Added successfully";
    }

    public CustomerWalletDTO updateCustomerWallet(long id, CustomerWalletDTO customerWalletDTO) {
        CustomerWalletEntity customerWalletEntity = customerWalletRepository.getOne(id);
        customerWalletEntity.setCurrentAmount(customerWalletDTO.getCurrentAmount());
        if (customerWalletDTO.getCustomerMergerDTO() != null) {
            customerWalletEntity.setCustomerEntity(CustomerEntity.builder()
                    .customerId(customerWalletDTO.getCustomerMergerDTO().getId())
                    .username(customerWalletDTO.getCustomerMergerDTO().getUsername())
                    .build());
        }
        customerWalletRepository.saveAndFlush(customerWalletEntity);
        return toDTO(customerWalletEntity);
    }

    public String deleteCustomerWalletById(long id) {
        customerWalletRepository.deleteById(id);
        return "Deleted successfully";
    }
}
