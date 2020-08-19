package com.shree.ecommerce_m_v.customer.customer.model.DTO;

import com.shree.ecommerce_m_v.customer.complain.service.dto.ComplainMergerDTO;
import com.shree.ecommerce_m_v.customer.customer.model.entity.Address;
import com.shree.ecommerce_m_v.customer.customer.model.entity.Status;
import com.shree.ecommerce_m_v.customer.customerWallet.model.dto.CustomerWalletMergerDTO;
import com.shree.ecommerce_m_v.customer.order.orderService.model.dto.OrderServiceMergerDTO;
import com.shree.ecommerce_m_v.customer.preBooking.model.DTO.PreBookingMergerDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class CustomerDTO {
    private long customerId;
    private String customerName;
    private String contactNo;
    private Address address;
    private String zipCode;
    private String email;
    private String username;
    private String password;
    private String verificationCode;
    private Status status;
    private String image;

    private List<PreBookingMergerDTO> preBookingMergerDTOList= new ArrayList<>();

    private CustomerWalletMergerDTO customerWalletMergerDTO;

    private List<ComplainMergerDTO> complainMergerDTOS= new ArrayList<>();

    private List<OrderServiceMergerDTO> orderServiceMergerDTOS= new ArrayList<>();




}
