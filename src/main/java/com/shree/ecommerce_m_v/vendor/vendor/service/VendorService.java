package com.shree.ecommerce_m_v.vendor.vendor.service;

import com.shree.ecommerce_m_v.utils.email.otp.response.ResponseVal;
import com.shree.ecommerce_m_v.vendor.vendor.service.dto.VendorDTO;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

public interface VendorService {
    Page<VendorDTO> getVendorList(int page,String sortBy,String orderBy);

    String saveVendor(VendorDTO vendorDTO);

    VendorEntity getVendorEntity(VendorDTO vendorDTO);

    String findByEmail(String email,String contact,String username);

    String deleteVendorWithId(Long id);

    VendorDTO updateVendor(Long vendorId, VendorDTO vendorDTO);

    VendorDTO getVendorWithId(Long id);

    Page<VendorDTO> getVendorWithName(String vendorName);

    ResponseVal otpVerification(int otpNum, VendorEntity vendorEntity);

    VendorDTO findVendorEntityByEmail(@Param("email") String email);


}
