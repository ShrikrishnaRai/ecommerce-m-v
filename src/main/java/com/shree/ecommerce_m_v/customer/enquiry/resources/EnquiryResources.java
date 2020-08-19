package com.shree.ecommerce_m_v.customer.enquiry.resources;

import com.shree.ecommerce_m_v.customer.enquiry.model.entity.EnquiryEntity;
import com.shree.ecommerce_m_v.customer.enquiry.service.EnquiryService;
import com.shree.ecommerce_m_v.utils.values.AuthoritiesConstants;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/enquiry")
public class EnquiryResources {

    private final EnquiryService enquiryService;

    public EnquiryResources(EnquiryService enquiryService) {
        this.enquiryService = enquiryService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<PagedModel<EntityModel<EnquiryEntity>>> getAllEnquiryLists(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                                                     @RequestParam(defaultValue = "0",value = "page",required = false) int page,
                                                                                     PagedResourcesAssembler<EnquiryEntity> assembler)  {
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(enquiryService.getAllEnquires(page)));
    }

    @PostMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<EnquiryEntity> saveEnquiries(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                       @ModelAttribute EnquiryEntity enquiryEntity) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(enquiryService.saveEnquiry(enquiryEntity));
    }

    @GetMapping("/{enquiryId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<Optional<EnquiryEntity>> findEnquiryById(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                                   @PathVariable("enquiryId") Long enquiryId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(enquiryService.findEnquiryById(enquiryId));
    }

    @DeleteMapping("/{enquiryId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")

    public ResponseEntity<String> deleteEnquiryById(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                    @PathVariable("enquiryId") Long enquiryId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(enquiryService.deleteEnquiryById(enquiryId));
    }

    @GetMapping("/search/{senderEmail}")
    public ResponseEntity<Optional<EnquiryEntity>> searchBySenderEmail(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                                       @PathVariable String senderEmail) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(enquiryService.findEnquiryBySenderEmail(senderEmail));
    }
}
