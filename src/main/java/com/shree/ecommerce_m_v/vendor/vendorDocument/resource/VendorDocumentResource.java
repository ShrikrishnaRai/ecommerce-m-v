package com.shree.ecommerce_m_v.vendor.vendorDocument.resource;

import com.shree.ecommerce_m_v.utils.values.AuthoritiesConstants;
import com.shree.ecommerce_m_v.vendor.vendorDocument.service.VendorDocumentService;
import com.shree.ecommerce_m_v.vendor.vendorDocument.service.dto.VendorDocumentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendorDocument")
public class VendorDocumentResource {

    @Autowired
    private VendorDocumentService vendorDocumentService;

    @GetMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.SUPER_ADMIN + "\")")
    public ResponseEntity<PagedModel<EntityModel<VendorDocumentDTO>>> getListOfVendorDocuments(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @RequestParam(defaultValue = "0",required = false,value = "page")int page,
            PagedResourcesAssembler<VendorDocumentDTO> assembler){
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(vendorDocumentService.getListOfVendorDocuments(page)));
    }

    @GetMapping("/{vendorDocumentId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")

    public ResponseEntity<VendorDocumentDTO> getVendorDocumentById(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                                   @PathVariable Long vendorDocumentId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(vendorDocumentService.getVendorDocumentById(vendorDocumentId));
    }

    @PostMapping()
    public ResponseEntity<String> saveVendorDocument(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                     @RequestBody VendorDocumentDTO vendorDocumentDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vendorDocumentService.saveVendorDocument(vendorDocumentDTO));
    }

    @PutMapping("/{vendorDocumentId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")

    public ResponseEntity<VendorDocumentDTO> updateVendorDocument(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                                  @PathVariable Long vendorDocumentId,
                                                                  @RequestBody VendorDocumentDTO vendorDocumentDTO){
        return ResponseEntity.status(HttpStatus.OK)
                .body(vendorDocumentService.updateVendorDocumentDTO(vendorDocumentId,vendorDocumentDTO));
    }
}
