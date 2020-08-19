package com.shree.ecommerce_m_v.customer.preBooking.model.DTO;

import com.shree.ecommerce_m_v.customer.customer.model.DTO.CustomerMergerDTO;
import com.shree.ecommerce_m_v.customer.preBooking.model.entity.Status;
import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PreBookingDTO {

    private Long preBookingId;
    private int quantity;
    private double price;
    private double totalPrice;
    private Status status;

    private List<ProductMergerDTO> productMergerDTOList= new ArrayList<>();

    private CustomerMergerDTO customerMergerDTO;

}
