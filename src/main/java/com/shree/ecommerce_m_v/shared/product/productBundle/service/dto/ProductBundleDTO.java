package com.shree.ecommerce_m_v.shared.product.productBundle.service.dto;

import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import com.shree.ecommerce_m_v.shared.product.productBundle.model.Status;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shree Rai
 * @Date 2/7/20
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ProductBundleDTO {
    private Long productBundleId;
    private String productBundleCode;
    private String productBundleName;
    private double productBundlePrice;
    private Status status;
    private String tag;
    private boolean isTaxable;
    private double taxAmount;
    private double totalQuantity;
    private int remainingQuantity;

    private List<ProductMergerDTO> productMergerDTOList = new ArrayList<>();


}
