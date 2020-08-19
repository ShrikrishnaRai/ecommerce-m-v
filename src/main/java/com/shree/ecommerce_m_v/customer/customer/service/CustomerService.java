package com.shree.ecommerce_m_v.customer.customer.service;

import com.shree.ecommerce_m_v.customer.customer.model.DTO.CustomerDTO;
import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.utils.email.otp.response.ResponseVal;
import org.springframework.data.domain.Page;

public interface CustomerService {
    Page<CustomerDTO> getAllCustomers(int page);

    CustomerDTO getCustomerById(final Long customerId);

    String saveCustomer(CustomerDTO customerDTO);

    String deleteCustomerWithId(Long id);

    CustomerDTO getCustomerByName(String name);

    CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO);

    CustomerEntity getCustomerEntity(CustomerDTO customerDTO);

    ResponseVal otpVerification(int otpNum, CustomerEntity customerEntity) throws Exception;

    String findByEmail(String email, String username, String contactNo);

    CustomerEntity findCustomerByEmail(String email);

}
