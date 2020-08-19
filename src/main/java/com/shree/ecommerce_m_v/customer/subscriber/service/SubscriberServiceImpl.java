package com.shree.ecommerce_m_v.customer.subscriber.service;

import com.shree.ecommerce_m_v.customer.subscriber.model.dto.SubscriberDTO;
import com.shree.ecommerce_m_v.customer.subscriber.repository.SubscriberRepository;
import com.shree.ecommerce_m_v.customer.subscriber.service.mapper.SubscriberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriberServiceImpl extends SubscriberMapper implements SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;

    @Override
    public List<SubscriberDTO> getAllSubscribers() {

        return subscriberRepository.findAll()
                .stream()
                .map(subscriberEntity -> toDTO(subscriberEntity))
                .collect(Collectors.toList());
    }

    @Override
    public SubscriberDTO getSubscriberWithEmail(String email) {
        return toDTO(subscriberRepository.getSubscriberEntityByEmail(email));
    }

    @Override
    public String deleteSubscriber(Long subscriberId) {
        subscriberRepository.findById(subscriberId);
        return "Unsubscribed";
    }

    @Override
    public SubscriberDTO getSubscriberById(Long subscriberId) {
        return toDTO(subscriberRepository.getOne(subscriberId));
    }
}
