package com.shree.ecommerce_m_v.vendor.service.serviceProvider.resource;

import com.shree.ecommerce_m_v.vendor.service.serviceProvider.service.dto.ServiceProviderDTO;
import com.shree.ecommerce_m_v.vendor.service.serviceProvider.service.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/serviceProvider")
public class ServiceProviderResource {

    @Autowired
    private ServiceProviderService serviceProviderService;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<ServiceProviderDTO>>>  getListOfServiceProvider(
            @RequestHeader(value="Authorization" ,required = false) String Authorization,
            @RequestParam(value="page" ,required = false,defaultValue = "0") int page,
            PagedResourcesAssembler<ServiceProviderDTO> assembler){
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(serviceProviderService.getListOfServiceProvider(page)));
    }

    @GetMapping("/{serviceProviderId}")
    public ResponseEntity<ServiceProviderDTO> getServiceProviderById(
            @RequestHeader(value="Authorization" ,required = false) String Authorization,
            @PathVariable("serviceProviderId") final Long serviceProviderId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(serviceProviderService.getServiceProviderById(serviceProviderId));
    }

    @PostMapping
    public  ResponseEntity<String> saveServiceProvider(
            @RequestHeader(value="Authorization" ,required = false) String Authorization,
            @RequestBody ServiceProviderDTO serviceProviderDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(serviceProviderService.saveServiceProvider(serviceProviderDTO));
    }

    @DeleteMapping("/{serviceProviderId}")
    public ResponseEntity<String> deleteServiceProvider(
            @RequestHeader(value="Authorization" ,required = false) String Authorization,
            @PathVariable("serviceProviderId") final Long serviceProviderId){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(serviceProviderService.deleteServiceProviderById(serviceProviderId));
    }

    @PutMapping("/{serviceProviderId}")
    public ResponseEntity<ServiceProviderDTO> updateServiceProvider(
            @RequestHeader(value="Authorization" ,required = false) String Authorization,
            @PathVariable("serviceProviderId") final Long serviceProviderId,
            @RequestBody ServiceProviderDTO serviceProviderDTO){
        return ResponseEntity.status(HttpStatus.OK)
                .body(serviceProviderService.updateServiceProvider(serviceProviderId,serviceProviderDTO));
    }
}
