package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyBankDetail.resource;


import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyBankDetail.model.dto.DeliveryBoyBankDetailDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyBankDetail.service.DeliveryBoyBankDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deliveryBoyBankDetail")
public class DeliveryBoyBankDetailResource {

    @Autowired
    private DeliveryBoyBankDetailService deliveryBoyBankDetailService;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<DeliveryBoyBankDetailDTO>>> getAllDeliveryBoyBankDetails(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @RequestParam(defaultValue = "0",required = false,value = "page") int page,
            PagedResourcesAssembler<DeliveryBoyBankDetailDTO> assembler) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(deliveryBoyBankDetailService.getAllDeliveryBoysBankDetails(page)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryBoyBankDetailDTO> getDeliveryBoyBankDetailById(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                                                 @PathVariable long id) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(deliveryBoyBankDetailService.getDeliveryBoyBankDetailById(id));
    }


    @PostMapping
    public ResponseEntity<String> saveDeliveryBoyBankDetail(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                            @RequestBody DeliveryBoyBankDetailDTO deliveryBoyBankDetailDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(deliveryBoyBankDetailService.saveDeliveryBoyBankDetail(deliveryBoyBankDetailDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryBoyBankDetailDTO> updateDeliveryBoyBankDetail(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                                                @PathVariable long id, @RequestBody DeliveryBoyBankDetailDTO deliveryBoyBankDetailDTO) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(deliveryBoyBankDetailService.updateDeliveryBoyBankDetail(id, deliveryBoyBankDetailDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDeliveryBoyBankDetail(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                              @PathVariable long id) {

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(deliveryBoyBankDetailService.deleteDeliveryBoyBankDetail(id));
    }
}
