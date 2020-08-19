package com.shree.ecommerce_m_v.customer.customerWallet.service;

import com.shree.ecommerce_m_v.customer.customerWallet.model.dto.CustomerWalletDTO;

public interface CustomerWalletService {

    CustomerWalletDTO getCustomerWalletById(long id);


    String saveCustomerWallet(CustomerWalletDTO customerWalletDTO);

    CustomerWalletDTO updateCustomerWallet(long id, CustomerWalletDTO customerWalletDTO);

    String deleteCustomerWalletById(long id);


}
