package com.shree.ecommerce_m_v.customer.preBooking.model.DTO;

import lombok.*;

@Data
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PreBookingMergerDTO {

    private Long preBookingId;
    private int quantity;


}
