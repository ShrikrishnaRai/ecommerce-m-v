package com.shree.ecommerce_m_v.customer.preBooking.service;

import com.shree.ecommerce_m_v.customer.preBooking.model.DTO.PreBookingDTO;
import org.springframework.data.domain.Page;

public interface PreBookingService {

    Page<PreBookingDTO> getListOfPreBookings(int page, String sortBy, String orderBy);

    PreBookingDTO getPreBookingById( Long preBookingId);

    String savePreBooking(PreBookingDTO preBookingDTO);

    PreBookingDTO updatePreBookingById(Long preBookingId,PreBookingDTO preBookingDTO);

    String deletePreBookingById(Long preBookingId);
}
