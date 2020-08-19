package com.shree.ecommerce_m_v.customer.order.cancelOrder.resource;

import com.shree.ecommerce_m_v.customer.order.cancelOrder.model.dto.CancelOrderDTO;
import com.shree.ecommerce_m_v.customer.order.cancelOrder.service.CancelOrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cancelOrder")
@Api(tags = "Cancel Order", description = "Orders canceled by Customers")
public class CancelOrderResource {

    @Autowired
    private CancelOrderService cancelOrderService;

    @GetMapping
    public ResponseEntity<List<CancelOrderDTO>> getCancelOrders(@RequestHeader(value = "Authorization",required = false) String Authorization) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cancelOrderService.getListOfCancelOrder());
    }

    @GetMapping("/{cancelOrderId}")
    public ResponseEntity<CancelOrderDTO> getCancelOrderById(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                             @PathVariable("cancelOrderId") Long cancelOrderId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cancelOrderService.getCancelOrderById(cancelOrderId));
    }

    @PostMapping
    public ResponseEntity<String> saveCanceOrder(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                 @RequestBody CancelOrderDTO cancelOrderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cancelOrderService.saveCancelOrder(cancelOrderDTO));
    }

    @DeleteMapping("/{cancelOrderId}")
    public ResponseEntity<String> deleteCancelOrder(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                    @PathVariable Long cancelOrderId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(cancelOrderService.deleteCancelOrder(cancelOrderId));
    }
}
