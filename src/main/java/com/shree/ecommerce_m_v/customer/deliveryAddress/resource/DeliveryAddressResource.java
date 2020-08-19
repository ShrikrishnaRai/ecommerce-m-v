package com.shree.ecommerce_m_v.customer.deliveryAddress.resource;

import com.shree.ecommerce_m_v.customer.deliveryAddress.model.DTO.DeliveryAddressDTO;
import com.shree.ecommerce_m_v.customer.deliveryAddress.service.DeliveryAddressService;
import com.shree.ecommerce_m_v.utils.values.AuthoritiesConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveryAddress")
public class DeliveryAddressResource {

    @Autowired
    private DeliveryAddressService deliveryAddressService;

    @PostMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")

    public ResponseEntity<String> saveAddress(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                              @RequestBody DeliveryAddressDTO deliveryAddressDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(deliveryAddressService.saveDeliveryAddress(deliveryAddressDTO));
    }

    @DeleteMapping("/{deliveryAddressId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")

    public ResponseEntity<String> deleteDeliveryAddress(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                        @PathVariable("deliveryAddressId") Long deliveryAddressId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(deliveryAddressService.deleteDeliveryAddress(deliveryAddressId));
    }

    @GetMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")" +
            "&& hasAuthority('" + AuthoritiesConstants.ADMIN + "\')" +
            "&& hasAuthority('" + AuthoritiesConstants.DELIVERY_BOY + "\')")
    public ResponseEntity<List<DeliveryAddressDTO>> getListOfDeliveryAddress(
            @RequestHeader(value = "Authorization", required = false) String Authorization) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(deliveryAddressService.getListOfDeliveryAddress());

    }


    @GetMapping("/{deliveryAddressId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")" +
            "&& hasAuthority('" + AuthoritiesConstants.DELIVERY_BOY + "\')")
    public ResponseEntity<DeliveryAddressDTO> getDeliveryAddressById(
            @RequestHeader(value = "Authorization", required = false) String Authorization,
            @PathVariable("deliveryAddressId") final Long deliveryAddressId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(deliveryAddressService.getDeliveryAddressById(deliveryAddressId));
    }
}
