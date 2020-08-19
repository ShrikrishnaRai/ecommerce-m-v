package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.resource;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.model.dto.DeliveryBoyWalletDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.service.DeliveryBoyWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveryBoyWallet")
public class DeliveryBoyWalletResource {

    @Autowired
    private DeliveryBoyWalletService deliveryBoyWalletService;

    @GetMapping
    public ResponseEntity<List<DeliveryBoyWalletDTO>> getListOfDeliveryBoysWallets(@RequestHeader(value = "Authorization",required = false) String Authorization) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(deliveryBoyWalletService.getAllDeliveryBoysWallet());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryBoyWalletDTO> getDeliveryBoyWallet(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                                     @PathVariable long id) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(deliveryBoyWalletService.getDeliveryBoyWalletById(id));
    }

    @PostMapping
    public ResponseEntity<Double> saveDeliveryBoyWallet(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                        @RequestBody DeliveryBoyWalletDTO deliveryBoyWalletDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(deliveryBoyWalletService.saveDeliveryBoyWallet(deliveryBoyWalletDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryBoyWalletDTO> updateDeliveryBoyWallet(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                                        @PathVariable long id, @RequestBody DeliveryBoyWalletDTO deliveryBoyWalletDTO) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(deliveryBoyWalletService.updateDeliveryBoyWallet(id, deliveryBoyWalletDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDeliveryBoyWallet(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                          @PathVariable long id) {

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(deliveryBoyWalletService.deleteDeliveryBoyWallet(id));
    }


}
