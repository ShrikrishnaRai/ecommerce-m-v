package com.shree.ecommerce_m_v.vendor.vendorReview.resource;

import com.shree.ecommerce_m_v.utils.values.AuthoritiesConstants;
import com.shree.ecommerce_m_v.vendor.vendorReview.service.dto.VendorReviewDTO;
import com.shree.ecommerce_m_v.vendor.vendorReview.service.VendorReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendorReview")
public class VendorReviewResource {

    @Autowired
    private VendorReviewService vendorReviewService;

    @PostMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")

    public ResponseEntity<String> saveVendorReview(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                   @RequestBody VendorReviewDTO vendorReviewDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vendorReviewService.saveVendorReview(vendorReviewDTO));
    }

    @GetMapping("/{vendorId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + AuthoritiesConstants.SUPER_ADMIN + "\")")

    public ResponseEntity<List<VendorReviewDTO>> getReviewOfVendorWithVendorId(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                                               @PathVariable("vendorId") Long vendorId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(vendorReviewService.getReviewOfVendorWithVendorId(vendorId));
    }

    @PutMapping("/{vendorReviewId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")

    public ResponseEntity<VendorReviewDTO> updateVendorReview(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                              @PathVariable Long vendorReviewId,
                                                              @RequestBody VendorReviewDTO vendorReviewDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(vendorReviewService.updateVendorReview(vendorReviewId, vendorReviewDTO));
    }
}
