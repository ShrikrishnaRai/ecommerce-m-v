package com.shree.ecommerce_m_v.vendor.commission.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommissionMergerDTO {
    private int commissionTypeId;

    private String commissionDescription;


}
