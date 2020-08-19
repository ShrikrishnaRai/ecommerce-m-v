package com.shree.ecommerce_m_v.vendor.service.service.resource;

import com.shree.ecommerce_m_v.vendor.service.service.service.dto.ServiceDTO;
import com.shree.ecommerce_m_v.vendor.service.service.service.ServiceEntityService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service")
@Api(tags = "Service", description = "Service provided by vendor or seller")
public class ServiceResource {

    @Autowired
    private ServiceEntityService serviceEntityService;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<ServiceDTO>>> getListOfService(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @RequestParam(value = "page",defaultValue = "0",required = false)int page,
            @RequestParam(defaultValue = "serviceName",required = false,value = "sortBy")String sortBy,
            @RequestParam(defaultValue = "ASC",required = false,value = "orderBy")String orderBy,
            PagedResourcesAssembler<ServiceDTO> assembler) {
        return new ResponseEntity<>(assembler.toModel(serviceEntityService.getListOfService(page,sortBy,orderBy)),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> getServiceById(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(serviceEntityService.getServiceById(id));
    }

    @PostMapping
    public ResponseEntity<String> saveService(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @RequestBody ServiceDTO serviceDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(serviceEntityService.saveService(serviceDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteService(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(serviceEntityService.deleteService(id));
    }

    @GetMapping("/search/{serviceName}")
    public ResponseEntity<List<ServiceDTO>> getServiceByName(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @PathVariable String serviceName) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(serviceEntityService.getServiceByName(serviceName));
    }

    @PutMapping("/{serviceId}")
    public ResponseEntity<ServiceDTO> updateService(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @PathVariable("serviceId") Long serviceId,
            @RequestBody ServiceDTO serviceDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(serviceEntityService.updateService(serviceId,serviceDTO));
    }

}
