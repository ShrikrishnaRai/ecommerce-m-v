package com.shree.ecommerce_m_v.customer.shoppingCart.repository;

import com.shree.ecommerce_m_v.customer.shoppingCart.model.entity.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long> {

    @Query(value = "Select * from shopping_cart where customer_id_fk=?1", nativeQuery = true)
    List<ShoppingCartEntity> getShoppingCartEntityByCustomerId(@Param("customerId") Long customerId);

    //This method prevents from re adding product to cart
    @Query(value = "Select * from shopping_cart where customer_id_fk=?1 && product_id_fk=?2", nativeQuery = true)
    List<ShoppingCartEntity> checkCartAvailability(@Param("customerId") Long customerId, @Param("productId") Long productId);
}
