package com.shree.ecommerce_m_v.customer.complain.resource;

import com.shree.ecommerce_m_v.customer.complain.service.ComplainService;
import com.shree.ecommerce_m_v.customer.complain.service.dto.ComplainDTO;
import com.shree.ecommerce_m_v.utils.values.AuthoritiesConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/complain")
public class ComplainResource {

    @Autowired
    private ComplainService complainService;


    @GetMapping
    public ResponseEntity getComplains(
            @RequestHeader(value = "Authorization", required = false) String Authorization,
            @RequestParam(defaultValue = "0", value = "p", required = false) int p,
            @RequestParam(value = "sortBy", required = false, defaultValue = "complainId") String sortBy,
            @RequestParam(value = "orderBy", required = false, defaultValue = "ASC") String orderBy,
            PagedResourcesAssembler assembler) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(complainService.getAllComplains(p, sortBy, orderBy)));
    }

    @GetMapping("/{id}")

    public ResponseEntity<ComplainDTO> getComplainById(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                       @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(complainService.getComplainById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")

    public ResponseEntity<String> saveComplain(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                               @RequestBody ComplainDTO complainDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(complainService.saveComplain(complainDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")

    public ResponseEntity<String> deleteComplainById(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                     @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(complainService.deleteComplainById(id));
    }

}
