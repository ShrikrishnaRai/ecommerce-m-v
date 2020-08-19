package com.shree.ecommerce_m_v.shared.product.offer.service.dto;

import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import com.shree.ecommerce_m_v.vendor.vendor.service.dto.VendorMergerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfferDTO {

    private Long offerId;
    private String offerName;
    private String description;
    private LocalDate validFrom;
    private LocalDate validTo;
    private String status;
    private double discountValue;
    private String discountType;
    private double minimumOrder;

    private VendorMergerDTO vendorMergerDTO;

    private List<ProductMergerDTO> productMergerDTOList = new ArrayList<>();


}
