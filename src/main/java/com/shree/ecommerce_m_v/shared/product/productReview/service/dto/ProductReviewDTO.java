package com.shree.ecommerce_m_v.shared.product.productReview.service.dto;

import com.shree.ecommerce_m_v.customer.customer.model.DTO.CustomerMergerDTO;
import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shree Rai
 * @Date 2/7/20
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductReviewDTO {
    private long id;
    private float rating;
    private String review;
    private String reply;
    private ProductMergerDTO productMergerDTO;
    private CustomerMergerDTO customerMergerDTO;

    public ProductReviewDTO(float rating, String review, String reply) {
        this.rating = rating;
        this.review = review;
        this.reply = reply;
    }


}
