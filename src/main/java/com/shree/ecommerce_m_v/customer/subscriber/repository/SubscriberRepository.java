package com.shree.ecommerce_m_v.customer.subscriber.repository;

import com.shree.ecommerce_m_v.customer.subscriber.model.entity.SubscriberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends JpaRepository<SubscriberEntity,Long> {

    @Query(value="Select * from subscriber where email=?1",nativeQuery=true)
    SubscriberEntity getSubscriberEntityByEmail(String email);

}
