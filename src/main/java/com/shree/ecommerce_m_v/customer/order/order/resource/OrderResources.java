package com.shree.ecommerce_m_v.customer.order.order.resource;

import com.shree.ecommerce_m_v.customer.order.order.model.DTO.OrderResponseDTO.OrderRequestDTO;
import com.shree.ecommerce_m_v.customer.order.order.model.DTO.OrderResponseDTO.OrderResponseDTO;
import com.shree.ecommerce_m_v.customer.order.order.service.OrderService;
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
@RequestMapping("/orders")
public class OrderResources {

    @Autowired
    private OrderService orderService;

    @GetMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<PagedModel<EntityModel<OrderResponseDTO>>> getOrdersLists(
            @RequestHeader(value = "Authorization", required = false) String Authorization,
            @RequestParam(defaultValue = "0", required = false, value = "page") int page,
            @RequestParam(defaultValue = "dateCreated", required = false, value = "sortBy") String sortBy,
            @RequestParam(defaultValue = "DESC", required = false, value = "orderBy") String orderBy,
            PagedResourcesAssembler<OrderResponseDTO> assembler) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(orderService.getOrders(page, sortBy, orderBy)));
    }

    @PostMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<String> saveOrder(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                            @RequestBody OrderRequestDTO orderRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.saveOrder(orderRequestDTO));
    }

    @GetMapping("/{orderId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")" +
            "|| hasAuthority('" + AuthoritiesConstants.ADMIN + "\')" +
            "|| hasAuthority('" + AuthoritiesConstants.DELIVERY_BOY + "\')")
    public ResponseEntity<OrderResponseDTO> getOrderById(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                         @PathVariable("orderId") Long orderId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.getOrderById(orderId));
    }

    @PutMapping("/{orderId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<OrderResponseDTO> updateOrder(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                        @PathVariable("orderId") Long orderId,
                                                        @RequestBody OrderRequestDTO orderRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.updateOrder(orderId, orderRequestDTO));
    }

    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<List<OrderResponseDTO>> getOrderByCustomerId(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                                       @PathVariable("customerId") Long customerId) {
        return
                ResponseEntity.status(HttpStatus.OK)
                        .body(orderService.getOrderByCustomerId(customerId));
    }
}
