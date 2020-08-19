package com.shree.ecommerce_m_v.customer.order.orderService.resource;

import com.shree.ecommerce_m_v.customer.order.orderService.model.dto.OrderServiceDTO;
import com.shree.ecommerce_m_v.customer.order.orderService.service.OrderServiceServiceClass;
import com.shree.ecommerce_m_v.utils.values.AuthoritiesConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderServiceDetails")
public class OrderServiceResource {

    @Autowired
    private OrderServiceServiceClass orderServiceClass;

    @GetMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")" +
            "&& hasAuthority('" + AuthoritiesConstants.ADMIN + "\')")
    public ResponseEntity<List<OrderServiceDTO>> getListOfOrderService(@RequestHeader(value = "Authorization", required = false) String Authorization) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderServiceClass.getListOfOrderService());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")" +
            "&& hasAuthority('" + AuthoritiesConstants.ADMIN + "\')")
    public ResponseEntity<OrderServiceDTO> getOrderServiceById(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                               @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderServiceClass.getOrderServiceById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")

    public ResponseEntity<String> saveOrderService(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                   @RequestBody OrderServiceDTO orderServiceDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderServiceClass.saveOrderService(orderServiceDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")

    public ResponseEntity<OrderServiceDTO> updateOrderService(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                              @PathVariable long id, @RequestBody OrderServiceDTO orderServiceDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderServiceClass.updateOrderService(id, orderServiceDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<String> deleteOrderService(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                     @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(orderServiceClass.deleteOrderService(id));
    }
}
