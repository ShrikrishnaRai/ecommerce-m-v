package com.shree.ecommerce_m_v.customer.complain.service;

import com.shree.ecommerce_m_v.customer.complain.service.dto.ComplainDTO;
import org.springframework.data.domain.Page;

public interface ComplainService {

    Page<ComplainDTO> getAllComplains(int page, String sortBy, String orderBy);

    ComplainDTO getComplainById(long id);

    String saveComplain(ComplainDTO complainDTO);

    String deleteComplainById(long id);
}
