package com.shree.ecommerce_m_v.vendor.coupon.resource;

import com.shree.ecommerce_m_v.vendor.coupon.service.dto.CouponDTO;
import com.shree.ecommerce_m_v.vendor.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupon")
public class CouponResource {

    @Autowired
    private CouponService couponService;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<CouponDTO>>> getCoupons(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @RequestParam(defaultValue = "0",required = false,value = "page") int page,
            @RequestParam(defaultValue = "couponCode",required = false,value = "sortBy") String sortBy,
            @RequestParam(defaultValue = "ASC",required = false,value = "orderBy") String orderBy,
            PagedResourcesAssembler<CouponDTO> assembler){
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(couponService.getCoupons(page,sortBy,orderBy)));
    }

    @GetMapping("/{couponId}")
    public ResponseEntity<CouponDTO> getCouponById(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                   @PathVariable("couponId") final Long couponId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(couponService.getCouponById(couponId));
    }

    @PostMapping
    public ResponseEntity<String> saveCoupon(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                             @RequestBody CouponDTO couponDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(couponService.saveCoupon(couponDTO));
    }

    @DeleteMapping("/{couponId}")
    public ResponseEntity<String> deleteCoupon(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                               @PathVariable("couponId") Long couponId){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(couponService.deleteCoupon(couponId));
    }

    @PutMapping("/{couponId}")
    public ResponseEntity<CouponDTO> updateCoupon(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                  @PathVariable("couponId") Long couponId,
                                                  @RequestBody CouponDTO couponDTO){
        return ResponseEntity.status(HttpStatus.OK)
                .body(couponService.updateCoupon(couponId,couponDTO));
    }
}
