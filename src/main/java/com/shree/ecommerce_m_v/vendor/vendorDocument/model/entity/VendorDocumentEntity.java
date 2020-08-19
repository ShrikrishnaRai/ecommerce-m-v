package com.shree.ecommerce_m_v.vendor.vendorDocument.model.entity;

import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import lombok.*;

import javax.persistence.*;

/**Needs revision*/

@Data
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="VendorDocumentEntity")
@Table(name="vendor_document")
public class VendorDocumentEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vendorDocumentId;
    private String panDocument;
    private String vatDocument;
    private String taxClearance;

    @OneToOne(mappedBy = "vendorDocumentEntity", cascade = CascadeType.MERGE)
    private VendorEntity vendorEntity;


}
