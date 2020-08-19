package com.shree.ecommerce_m_v.customer.customer.repository;

import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {


    @Query(value = "Select email from customer where email=?1 or username=?2 or contact_no=?3", nativeQuery = true)
    String findByEmail(@Param("email") String email, @Param("username") String username, @Param("contactNo") String contactNo);

    @Query(value = "Select * from customer where customer_id=?1", nativeQuery = true)
    String findByCustomerId(@Param("customerId") Long customerId);

    @Query(value = "Select * from customer where customer_name like %:name%", nativeQuery = true)
    CustomerEntity findByCustomerName(@Param("name") String name);

    @Query(value = "select * from customer c where c.email=:email", nativeQuery = true)
    CustomerEntity findCustomerEntitiesByEmail(@Param("email") String email);
}
