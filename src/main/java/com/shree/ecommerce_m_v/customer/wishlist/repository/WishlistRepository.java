package com.shree.ecommerce_m_v.customer.wishlist.repository;

import com.shree.ecommerce_m_v.customer.wishlist.model.entity.WishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface WishlistRepository extends JpaRepository<WishlistEntity,Long> {

    @Query(value="Select * from wishlist where customer_id_fk=?1",nativeQuery=true)
    WishlistEntity getWishlistEntityByCustomerId(@Param("customerId") Long customerId);
}
