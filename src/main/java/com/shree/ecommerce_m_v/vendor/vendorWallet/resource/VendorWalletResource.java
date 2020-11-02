package com.shree.ecommerce_m_v.vendor.vendorWallet.resource;

import com.shree.ecommerce_m_v.utils.values.AuthoritiesConstants;
import com.shree.ecommerce_m_v.vendor.vendorWallet.service.VendorWalletService;
import com.shree.ecommerce_m_v.vendor.vendorWallet.service.dto.VendorWalletDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/vendorWallet")
public class VendorWalletResource {

    @Autowired
    private VendorWalletService vendorWalletService;

    @GetMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.SUPER_ADMIN + "\")")

    public ResponseEntity<PagedModel<EntityModel<VendorWalletDTO>>> getVendorWallets(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @RequestParam(defaultValue = "0",required = false,value="page") int page,
            PagedResourcesAssembler<VendorWalletDTO> assembler){
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(vendorWalletService.getListOfVendorWallet(page)));
    }

    @GetMapping("/{vendorId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")

    public ResponseEntity<VendorWalletDTO> getVendorWalletByVendorId(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                                     @PathVariable("vendorId") Long vendorId ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(vendorWalletService.getVendorWalletByVendorId(vendorId));
    }

    @PostMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")

    public ResponseEntity<String> saveVendorWallet(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                   @RequestBody VendorWalletDTO vendorWalletDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vendorWalletService.saveVendorWallet(vendorWalletDTO));
    }

    @DeleteMapping("{vendorWalletId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")

    public ResponseEntity<String> deleteVendorWallet(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                     @PathVariable("vendorWalletId") Long vendorWalletId){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(vendorWalletService.deleteVendorWallet(vendorWalletId));
    }

    @PutMapping("{vendorWalletId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")

    public ResponseEntity<VendorWalletDTO> updateVendorWallet(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                              @PathVariable("vendorWalletId" )Long vendorWalletId,
                                                              @RequestBody VendorWalletDTO vendorWalletDTO){
        return ResponseEntity.status(HttpStatus.OK)
                .body(vendorWalletService.updateVendorWallet(vendorWalletId,vendorWalletDTO));
    }

}
