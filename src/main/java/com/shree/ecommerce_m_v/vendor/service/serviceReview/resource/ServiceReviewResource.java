package com.shree.ecommerce_m_v.vendor.service.serviceReview.resource;

import com.shree.ecommerce_m_v.vendor.service.serviceReview.service.dto.ServiceReviewDTO;
import com.shree.ecommerce_m_v.vendor.service.serviceReview.service.ServiceReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/serviceReview")
public class ServiceReviewResource {

    @Autowired
    private ServiceReviewService serviceReviewService;

    @GetMapping
    public ResponseEntity<List<ServiceReviewDTO>> getListOfServiceReview(@RequestHeader(value = "Authorization",required = false) String Authorization) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(serviceReviewService.getListOfServiceReview());
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<List<ServiceReviewDTO>> getServiceReviewOfServiceByServiceId(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @PathVariable Long serviceId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(serviceReviewService.getServiceReviewOfServiceWithServiceId(serviceId));
    }

    @PostMapping
    public ResponseEntity<String> saveServiceReview(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                    @RequestBody ServiceReviewDTO serviceReviewDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(serviceReviewService.saveServiceReview(serviceReviewDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteServiceReview(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                      @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(serviceReviewService.deleteServiceReview(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceReviewDTO> updateServiceReview(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @PathVariable long id,
            @RequestBody ServiceReviewDTO serviceReviewDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(serviceReviewService.updateServiceReview(id, serviceReviewDTO));
    }


}
