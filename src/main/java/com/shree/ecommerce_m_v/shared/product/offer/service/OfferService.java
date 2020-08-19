package com.shree.ecommerce_m_v.shared.product.offer.service;

import com.shree.ecommerce_m_v.shared.product.offer.service.dto.OfferDTO;
import org.springframework.data.domain.Page;

public interface OfferService {
    Page<OfferDTO> getAllOffers(int page,String sortBy,String orderBy);

    OfferDTO getOfferById(final Long offerId);

    String deleteOfferWithId(Long id);

    String saveOffer(OfferDTO offerDTO);

    OfferDTO updateOffer(Long id, OfferDTO offerDTO);
}
