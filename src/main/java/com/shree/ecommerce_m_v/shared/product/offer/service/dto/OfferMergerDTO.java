package com.shree.ecommerce_m_v.shared.product.offer.service.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferMergerDTO {
    private Long offerId;
    private String offerName;

}
