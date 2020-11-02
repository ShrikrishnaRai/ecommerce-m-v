package com.shree.ecommerce_m_v.customer.complain.service;

import com.shree.ecommerce_m_v.customer.complain.service.dto.ComplainDTO;
import com.shree.ecommerce_m_v.customer.complain.model.entity.ComplainEntity;
import com.shree.ecommerce_m_v.customer.complain.repository.ComplainRepository;
import com.shree.ecommerce_m_v.customer.complain.service.mapper.ComplainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ComplainService extends ComplainMapper {

    @Autowired
    private ComplainRepository complainRepository;

    public Page<ComplainDTO> getAllComplains(int page,String sortBy,String orderBy) {

        Pageable pageable = PageRequest.of(page,2, Sort.by(Sort.Direction.valueOf(orderBy),sortBy));
        Page<ComplainEntity> result =complainRepository.findAll(pageable);
        return result.map(complainEntity -> toDTO(complainEntity));
    }

    public ComplainDTO getComplainById(long id) {
        return toDTO(complainRepository.getOne(id));
    }

    public String saveComplain(ComplainDTO complainDTO) {
        complainRepository.save(toEntity(complainDTO));
        return "Complain  has been successfully added";
    }

    public String deleteComplainById(long id) {


        complainRepository.deleteById(id);
        return "Complain has been removed";
    }
}
