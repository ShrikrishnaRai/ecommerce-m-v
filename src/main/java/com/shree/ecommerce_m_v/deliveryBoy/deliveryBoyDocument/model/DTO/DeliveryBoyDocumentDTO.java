package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyDocument.model.DTO;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.DTO.DeliveryBoyMergerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryBoyDocumentDTO {

    private long deliveryBoyDocumentId;
    private String licenseImage;
    private String citizenshipImage;
    private String bluebookImage;
    private String vehicleImage;

    private DeliveryBoyMergerDTO deliveryBoyMergerDTO;


}
