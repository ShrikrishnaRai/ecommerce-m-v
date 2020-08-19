package com.shree.ecommerce_m_v.customer.customer.service;

import com.shree.ecommerce_m_v.config.security.user.model.UserEntity;
import com.shree.ecommerce_m_v.config.security.user.repository.UserRepository;
import com.shree.ecommerce_m_v.customer.complain.repository.ComplainRepository;
import com.shree.ecommerce_m_v.customer.complain.service.dto.ComplainMergerDTO;
import com.shree.ecommerce_m_v.customer.customer.model.DTO.CustomerDTO;
import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.customer.customer.repository.CustomerRepository;
import com.shree.ecommerce_m_v.customer.customer.service.mapper.CustomerMapper;
import com.shree.ecommerce_m_v.customer.customerWallet.model.entity.CustomerWalletEntity;
import com.shree.ecommerce_m_v.customer.order.orderService.model.dto.OrderServiceMergerDTO;
import com.shree.ecommerce_m_v.customer.order.orderService.model.entity.OrderServiceEntity;
import com.shree.ecommerce_m_v.customer.order.orderService.repository.OrderServiceRepository;
import com.shree.ecommerce_m_v.customer.preBooking.model.DTO.PreBookingMergerDTO;
import com.shree.ecommerce_m_v.customer.preBooking.repository.PreBookingRepository;
import com.shree.ecommerce_m_v.utils.email.otp.OtpConfig;
import com.shree.ecommerce_m_v.utils.email.otp.response.ResponseVal;
import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

@Service
public class CustomerServiceIMPL extends CustomerMapper implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ComplainRepository complainRepository;
    @Autowired
    private PreBookingRepository preBookingRepository;
    @Autowired
    private OrderServiceRepository orderServiceRepository;
    @Autowired
    private OtpConfig otpConfig;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<CustomerDTO> getAllCustomers(int page) {

        Pageable pageable = PageRequest.of(page, 10);
        Page<CustomerEntity> result = customerRepository.findAll(pageable);
        return result.map(this::toDTO);
    }

    @Override
    public CustomerDTO getCustomerById(Long customerId) {
        return toDTO(customerRepository.getOne(customerId));

    }

    @Override
    @Transactional
    public String saveCustomer(CustomerDTO customerDTO) {
        userRepository.save(UserEntity.builder()
                .activated(true)
                .email(customerDTO.getEmail())
                .password(customerDTO.getPassword())
                .login(new Random().toString())
                .build());
        CustomerEntity customerEntity = toEntity(customerDTO);
        for (ComplainMergerDTO complainMergerDTO : customerDTO.getComplainMergerDTOS()) {
            if (complainRepository.existsById(complainMergerDTO.getComplainId())) {
                customerEntity.addComplainOfCustomer(complainRepository.getOne(complainMergerDTO.getComplainId()));
            }
        }
        for (PreBookingMergerDTO preBookingMergerDTO : customerDTO.getPreBookingMergerDTOList()) {
            if (preBookingRepository.existsById(preBookingMergerDTO.getPreBookingId())) {
                customerEntity.addComplainOfCustomer(complainRepository.getOne(preBookingMergerDTO.getPreBookingId()));
            }
        }

        for (OrderServiceMergerDTO orderServiceMergerDTO : customerDTO.getOrderServiceMergerDTOS()) {
            if (orderServiceRepository.existsById(orderServiceMergerDTO.getOrderServiceId())) {
                customerEntity.addCustomerToOrderService(orderServiceRepository.getOne(orderServiceMergerDTO.getOrderServiceId()));
            }
        }
        customerRepository.save(customerEntity);
        return ResponseValue.SAVE_SUCCESS;

    }


    @Override
    public String deleteCustomerWithId(Long id) {
        customerRepository.deleteById(id);
        return ResponseValue.DELETE_SUCCESS;
    }

    @Override
    public CustomerDTO getCustomerByName(String name) {
        return toDTO(customerRepository.findByCustomerName(name));
    }

    @Override
    public CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerRepository.getOne(customerId);
        if (customerDTO.getComplainMergerDTOS().size() != 0) {
            for (ComplainMergerDTO complainMergerDTO : customerDTO.getComplainMergerDTOS()) {
                if (complainRepository.existsById(complainMergerDTO.getComplainId())) {
                    customerEntity.addComplainOfCustomer(complainRepository.getOne(complainMergerDTO.getComplainId()));
                }
            }
        }

        if (customerDTO.getPreBookingMergerDTOList().size() != 0) {
            for (PreBookingMergerDTO preBookingMergerDTO : customerDTO.getPreBookingMergerDTOList()) {
                if (preBookingRepository.existsById(preBookingMergerDTO.getPreBookingId())) {
                    customerEntity.addCustomerToPreBooking(preBookingRepository.getOne(preBookingMergerDTO.getPreBookingId()));
                }
            }
        }

        if (customerDTO.getOrderServiceMergerDTOS().size() != 0) {
            for (OrderServiceEntity orderServiceEntity : super.getOrderServiceEntities()) {
                if (orderServiceRepository.existsById(orderServiceEntity.getOrderServiceId())) {
                    customerEntity.addCustomerToOrderService(orderServiceRepository.getOne(orderServiceEntity.getOrderServiceId()));
                }
            }
        }

        if (customerDTO.getCustomerWalletMergerDTO() != null) {
            customerEntity.setCustomerWalletEntity(CustomerWalletEntity.builder()
                    .customerWalletId(customerDTO.getCustomerWalletMergerDTO().getCustomerWalletId())
                    .currentAmount(customerDTO.getCustomerWalletMergerDTO().getCurrentAmount())
                    .build());
        }
        customerEntity.setCustomerName(customerDTO.getCustomerName());
        customerEntity.setAddress(customerDTO.getAddress());
        customerEntity.setContactNo(customerDTO.getContactNo());
        customerEntity.setZipCode(customerDTO.getZipCode());
        customerEntity.setEmail(customerDTO.getEmail());
        customerEntity.setUsername(customerDTO.getUsername());
        customerEntity.setPassword(customerDTO.getPassword());
        customerEntity.setVerificationCode(customerDTO.getVerificationCode());
        customerEntity.setStatus(customerDTO.getStatus());
        customerEntity.setImage(customerDTO.getImage());

        customerRepository.saveAndFlush(customerEntity);
        return toDTO(customerEntity);
    }

    @Override
    public CustomerEntity getCustomerEntity(CustomerDTO customerDTO) {
        return toEntity(customerDTO);
    }

    @Override
    public String findByEmail(String email, String username, String contactNo) {
        return customerRepository.findByEmail(email, username, contactNo);
    }

    @Override
    public CustomerEntity findCustomerByEmail(String email) {
        return customerRepository.findCustomerEntitiesByEmail(email);
    }

    @Override
    public ResponseVal otpVerification(int otpNum, CustomerEntity customerEntity) throws Exception {

        ResponseVal status = new ResponseVal();
        if (otpNum > 0) {
            int serverOtp = otpConfig.getOtp(customerEntity.getEmail());
            if (otpNum == serverOtp) {
                otpConfig.clearOtp(customerEntity.getEmail());
                status.setCode(200);
                status.setMessage("Successfully registered");
                customerRepository.save(customerEntity);
            } else {
                status.setCode(200);
                status.setMessage("Invalid Otp");
            }
        }
        return status;
    }


}
