package com.shree.ecommerce_m_v.vendor.service.serviceLocation.resource;

import com.shree.ecommerce_m_v.vendor.service.serviceLocation.service.dto.ServiceLocationDTO;
import com.shree.ecommerce_m_v.vendor.service.serviceLocation.service.ServiceLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/serviceLocation")
public class ServiceLocationResource {

    @Autowired
    private ServiceLocationService serviceLocationService;

    @GetMapping
    public ResponseEntity<List<ServiceLocationDTO>> getListOfLocation(@RequestHeader(value="Authorization",required = false) String Authorization){
        return ResponseEntity.status(HttpStatus.OK)
                .body(serviceLocationService.getListOfServiceLocation());
    }

    @GetMapping("/{serviceProviderId}")
    public ResponseEntity<List<ServiceLocationDTO>> getServiceLocationsByServiceProviderId(@RequestHeader(value="Authorization",required = false) String Authorization,
                                                                                           @PathVariable("serviceProviderId") final Long serviceProviderId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(serviceLocationService.getListOfServiceLocationByServiceProviderById(serviceProviderId));
    }

    @PostMapping
    public ResponseEntity<String> saveServiceLocation(@RequestHeader(value="Authorization",required = false) String Authorization,
                                                      @RequestBody ServiceLocationDTO serviceLocationDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(serviceLocationService.saveServiceLocation(serviceLocationDTO));
    }

    @DeleteMapping("/{serviceLocationId}")
    public ResponseEntity<String> deleteServiceLocation(@RequestHeader(value="Authorization",required = false) String Authorization,
                                                        @PathVariable("serviceLocationId")final Long serviceLocationId){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(serviceLocationService.deleteServiceLocationById(serviceLocationId));
    }

}
