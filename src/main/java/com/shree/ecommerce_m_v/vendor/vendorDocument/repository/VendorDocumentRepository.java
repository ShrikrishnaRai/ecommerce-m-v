package com.shree.ecommerce_m_v.vendor.vendorDocument.repository;

import com.shree.ecommerce_m_v.vendor.vendorDocument.model.entity.VendorDocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorDocumentRepository extends JpaRepository<VendorDocumentEntity,Long> {
}
