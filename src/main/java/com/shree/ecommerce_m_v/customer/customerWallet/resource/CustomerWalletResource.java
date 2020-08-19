package com.shree.ecommerce_m_v.customer.customerWallet.resource;

import com.shree.ecommerce_m_v.customer.customerWallet.model.dto.CustomerWalletDTO;
import com.shree.ecommerce_m_v.customer.customerWallet.service.CustomerWalletService;
import com.shree.ecommerce_m_v.utils.values.AuthoritiesConstants;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customerWalletDetails")
@Api(tags = "customer Wallet", description = "Respective customer wallet")
public class CustomerWalletResource {

    @Autowired
    private CustomerWalletService customerWalletService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<CustomerWalletDTO> getCustomerWaller(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                               @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerWalletService.getCustomerWalletById(id));
    }

    @PostMapping
    public ResponseEntity<String> saveCustomerWallet(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                     @RequestBody CustomerWalletDTO customerWalletDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerWalletService.saveCustomerWallet(customerWalletDTO));

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")

    public ResponseEntity<CustomerWalletDTO> updateCustomerWallet(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                                  @PathVariable long id, @RequestBody CustomerWalletDTO customerWalletDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerWalletService.updateCustomerWallet(id, customerWalletDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")

    public ResponseEntity<String> deleteCustomerWallet(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                       @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(customerWalletService.deleteCustomerWalletById(id));
    }
}
