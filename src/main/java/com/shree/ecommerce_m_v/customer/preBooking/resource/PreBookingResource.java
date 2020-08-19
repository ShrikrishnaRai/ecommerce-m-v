package com.shree.ecommerce_m_v.customer.preBooking.resource;

import com.shree.ecommerce_m_v.customer.preBooking.model.DTO.PreBookingDTO;
import com.shree.ecommerce_m_v.customer.preBooking.service.PreBookingService;
import com.shree.ecommerce_m_v.utils.values.AuthoritiesConstants;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/preBooking")
@Api(tags = "PreBooking", description = "Pre-Booking of products")
public class PreBookingResource {

    @Autowired
    private PreBookingService preBookingService;

    @GetMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")" +
            "&& hasAuthority('" + AuthoritiesConstants.ADMIN + "\')" +
            "&& hasAuthority('" + AuthoritiesConstants.SUPER_ADMIN + "\')" +
            "&& hasAuthority('" + AuthoritiesConstants.DELIVERY_BOY + "\')")
    public ResponseEntity<PagedModel<EntityModel<PreBookingDTO>>> getListOfPreBookings(
            @RequestHeader(value = "Authorization", required = false) String Authorization,
            @RequestParam(defaultValue = "0", value = "0", required = false) int page,
            @RequestParam(defaultValue = "status", required = false, value = "sortBy") String sortBy,
            @RequestParam(defaultValue = "ASC", required = false, value = "orderBy") String orderBy,
            PagedResourcesAssembler<PreBookingDTO> assembler) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(preBookingService.getListOfPreBookings(page, sortBy, orderBy)));
    }

    @GetMapping("/{preBookingId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")" +
            "&& hasAuthority('" + AuthoritiesConstants.ADMIN + "\')" +
            "&& hasAuthority('" + AuthoritiesConstants.SUPER_ADMIN + "\')" +
            "&& hasAuthority('" + AuthoritiesConstants.DELIVERY_BOY + "\')")
    public ResponseEntity<PreBookingDTO> getPreBookingById(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                           @PathVariable("preBookingId") Long preBookingId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(preBookingService.getPreBookingById(preBookingId));
    }

    @PostMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<String> savePreBooking(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                 @RequestBody PreBookingDTO preBookingDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(preBookingService.savePreBooking(preBookingDTO));
    }

    @PutMapping("/{preBookingId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")

    public ResponseEntity<PreBookingDTO> updatePreBookingById(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                              @PathVariable("preBookingId") Long preBookingId, @RequestBody PreBookingDTO preBookingDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(preBookingService.updatePreBookingById(preBookingId, preBookingDTO));
    }

    @DeleteMapping("/{preBookingId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")

    public ResponseEntity<String> deletePreBookingById(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                       @PathVariable Long preBookingId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(preBookingService.deletePreBookingById(preBookingId));
    }
}
