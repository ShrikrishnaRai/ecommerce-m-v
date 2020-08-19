package com.shree.ecommerce_m_v.vendor.vendorWallet.repository;

import com.shree.ecommerce_m_v.vendor.vendorWallet.model.entity.VendorWalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface VendorWalletRepository extends JpaRepository<VendorWalletEntity,Long> {

    @Query(value="Select * from vendor_wallet where vendor_id_fk=?1",nativeQuery=true)
    VendorWalletEntity getVendorWalletEntityByVendorId(@Param("vendorId") Long vendorId);
}
