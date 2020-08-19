package com.shree.ecommerce_m_v.customer.subscriber.service;

import com.shree.ecommerce_m_v.customer.subscriber.model.dto.SubscriberDTO;

import java.util.List;

public interface SubscriberService {

    List<SubscriberDTO> getAllSubscribers();

    SubscriberDTO getSubscriberWithEmail(String email);

    String deleteSubscriber(Long subscriberId);

    SubscriberDTO getSubscriberById(final Long subscriberId);
}
