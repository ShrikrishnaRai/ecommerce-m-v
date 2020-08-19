package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.repository;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.DTO.DeliveryBoyIdAndName;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.entity.DeliveryBoyInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface DeliveryBoyRepository extends JpaRepository<DeliveryBoyInfoEntity, Long> {

    @Query(value = "SELECT * FROM delivery_boy d WHERE d.name like %:name%", nativeQuery = true)
    Page<DeliveryBoyInfoEntity> getDeliveryBoyByName(@Param("name") String name, Pageable pageable);

    List<DeliveryBoyIdAndName> getAllBy();
}
