package com.shree.ecommerce_m_v.vendor.vendorWallet.service;

import com.shree.ecommerce_m_v.vendor.vendorWallet.service.dto.VendorWalletDTO;
import org.springframework.data.domain.Page;

public interface VendorWalletService {

    String saveVendorWallet(VendorWalletDTO vendorWalletDTO);

    String deleteVendorWallet(Long vendorWalletId);

    VendorWalletDTO updateVendorWallet(Long vendorWalletId,VendorWalletDTO vendorWalletDTO);

    Page<VendorWalletDTO> getListOfVendorWallet(int page);

    VendorWalletDTO getVendorWalletByVendorId(Long vendorId);

}
