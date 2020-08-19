package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.resource;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.model.dto.DeliveryBoyPaymentHistoryDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.model.dto.DeliveryBoyPaymentHistoryResponseDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.service.DeliveryBoyPaymentHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deliveryBoyPaymentHistoryTransaction")
public class DeliveryBoyPaymentHistoryResource {

    @Autowired
    private DeliveryBoyPaymentHistoryService deliveryBoyPaymentHistoryService;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<DeliveryBoyPaymentHistoryResponseDTO>>> getListOfDeliveryBoyPaymentHistory(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @RequestParam(defaultValue = "0",value = "page",required = false) int page,
            PagedResourcesAssembler<DeliveryBoyPaymentHistoryResponseDTO> assembler) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(deliveryBoyPaymentHistoryService.getListOfDeliveryBoyPaymentHistory(page)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryBoyPaymentHistoryResponseDTO> getDeliveryBoyPaymentHistoryById(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(deliveryBoyPaymentHistoryService.getDeliveryBoyPaymentHistoryById(id));
    }

    @PostMapping
    public ResponseEntity<String> saveDeliveryBoyPaymentHistory(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                                @RequestBody DeliveryBoyPaymentHistoryDTO deliveryBoyPaymentHistoryDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(deliveryBoyPaymentHistoryService.saveDeliveryBoyPaymentHistory(deliveryBoyPaymentHistoryDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryBoyPaymentHistoryResponseDTO> updateDeliveryBoyPaymentHistory(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @PathVariable long id,
            @RequestBody DeliveryBoyPaymentHistoryDTO deliveryBoyPaymentHistoryDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(deliveryBoyPaymentHistoryService.updateDeliveryBoyPaymentHistory(id, deliveryBoyPaymentHistoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDeliveryBoyPaymentHistory(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                                  @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(deliveryBoyPaymentHistoryService.deleteDeliveryBoyPaymentHistory(id));
    }
}
