package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.resource;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.model.dto.DeliveryBoyWalletHistoryDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.model.dto.DeliveryBoyWalletHistoryResponseDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.service.DeliveryBoyWalletHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deliveryBoyWalletTransactionHistory")
public class DeliveryBoyWalletHistoryResource {

    @Autowired
    private DeliveryBoyWalletHistoryService deliveryBoyWalletHistoryService;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<DeliveryBoyWalletHistoryResponseDTO>>> getListOfDeliveryBoyWalletHistory(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @RequestParam(defaultValue = "0",value = "page",required = false)int page,
            PagedResourcesAssembler<DeliveryBoyWalletHistoryResponseDTO> assembler) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(deliveryBoyWalletHistoryService.getAllDeliveryBoyWalletHistories(page)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryBoyWalletHistoryResponseDTO> getDeliveryBoyWalletHistoryById(@RequestHeader(required = false,value = "Authorization") String Authorization,
                                                                                               @PathVariable long id) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(deliveryBoyWalletHistoryService.getDeliveryBoyWalletHistoryById(id));
    }

    @PostMapping
    public ResponseEntity<Double> saveDeliveryBoyWalletHistory(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                               @RequestBody DeliveryBoyWalletHistoryDTO deliveryBoyWalletHistoryDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(deliveryBoyWalletHistoryService.saveDeliveryBoyWalletHistory(deliveryBoyWalletHistoryDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryBoyWalletHistoryResponseDTO> updateDeliveryBoyWalletHistory(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                                                              @PathVariable long id, @RequestBody DeliveryBoyWalletHistoryResponseDTO deliveryBoyWalletHistoryResponseDTO) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(deliveryBoyWalletHistoryService.updateDeliveryBoyWalletHistory(id, deliveryBoyWalletHistoryResponseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDeliveryBoyWalletHistory(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                                 @PathVariable long id) {

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(deliveryBoyWalletHistoryService.deleteDeliveryBoyWalletHistory(id));
    }
}
