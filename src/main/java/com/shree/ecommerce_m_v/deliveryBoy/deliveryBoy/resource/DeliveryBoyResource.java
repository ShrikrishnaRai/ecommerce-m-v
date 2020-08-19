package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.resource;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.DTO.DeliveryBoyIdAndName;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.DTO.DeliveryBoyInfoDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.service.DeliveryBoyService;
import com.shree.ecommerce_m_v.utils.values.AuthoritiesConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveryBoy")
public class DeliveryBoyResource {

    @Autowired
    private DeliveryBoyService deliveryBoyService;

    @GetMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.SUPER_ADMIN + "\")" +
            "&& hasAuthority('" + AuthoritiesConstants.ADMIN + "\')")
    public ResponseEntity<PagedModel<EntityModel<DeliveryBoyInfoDTO>>> getAllDeliveryBoy(@RequestHeader(required = false, value = "Authorization") String Authorization,
                                                                                         @RequestParam(defaultValue = "0", required = false, value = "page") int page,
                                                                                         PagedResourcesAssembler<DeliveryBoyInfoDTO> assembler) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(deliveryBoyService.getAllDeliveryBoys(page)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.DELIVERY_BOY + "\")" +
            "&& hasAuthority('" + AuthoritiesConstants.ADMIN + "\')" +
            "&& hasAuthority('" + AuthoritiesConstants.SUPER_ADMIN + "\')")
    public ResponseEntity<DeliveryBoyInfoDTO> getDeliveryBoyById(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                                 @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(deliveryBoyService.getDeliveryBoyById(id));
    }

    @PostMapping
    public ResponseEntity<String> saveDeliveryBoy(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                  @RequestBody DeliveryBoyInfoDTO deliveryBoyInfoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(deliveryBoyService.saveDeliveryBoy(deliveryBoyInfoDTO));
    }

    @GetMapping("/search/{name}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")" +
            "&& hasAuthority('" + AuthoritiesConstants.SUPER_ADMIN + "\')")
    public ResponseEntity<PagedModel<EntityModel<DeliveryBoyInfoDTO>>> getDeliveryBoyByName(@RequestHeader(required = false, value = "Authorization") String Authorization,
                                                                                            @PathVariable String name,
                                                                                            @RequestParam(defaultValue = "0", required = false, value = "page") int page,
                                                                                            PagedResourcesAssembler<DeliveryBoyInfoDTO> assembler) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(deliveryBoyService.getDeliveryBoyByName(name)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority(\"" + AuthoritiesConstants.SUPER_ADMIN + "\",\"" + AuthoritiesConstants.DELIVERY_BOY + "\")")
    public ResponseEntity<String> deleteDeliveryBoyById(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                        @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(deliveryBoyService.deleteDeliveryBoy(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.DELIVERY_BOY + "\")")
    public ResponseEntity<DeliveryBoyInfoDTO> updateDeliveryBoy(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                                @PathVariable long id, @RequestBody DeliveryBoyInfoDTO deliveryBoyInfoDTO) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(deliveryBoyService.updateDeliveryBoy(id, deliveryBoyInfoDTO));
    }

    @GetMapping("/idAndName")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.SUPER_ADMIN + "\")")
    public ResponseEntity<List<DeliveryBoyIdAndName>> getAllDeliveryBoyAndName(@RequestHeader(value = "Authorization", required = false) String Authorization) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(deliveryBoyService.getDeliveryBoyIdAndName());
    }

}
