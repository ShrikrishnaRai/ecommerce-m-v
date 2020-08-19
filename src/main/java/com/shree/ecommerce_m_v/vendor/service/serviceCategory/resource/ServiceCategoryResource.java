package com.shree.ecommerce_m_v.vendor.service.serviceCategory.resource;

import com.shree.ecommerce_m_v.vendor.service.serviceCategory.service.dto.ServiceCategoryDTO;
import com.shree.ecommerce_m_v.vendor.service.serviceCategory.service.ServiceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/serviceCategory")
public class ServiceCategoryResource {

    @Autowired
    private ServiceCategoryService serviceCategoryService;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<ServiceCategoryDTO>>> getListOfServiceCategory(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @RequestParam(defaultValue = "0",required = false,value = "page")int page,
            @RequestParam(defaultValue = "serviceCategoryName",required = false,value = "sortBy")String sortBy,
            @RequestParam(defaultValue = "ASC" ,required = false,value = "orderBy")String orderBy,
            PagedResourcesAssembler<ServiceCategoryDTO> assembler) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(serviceCategoryService.getListOfServiceCategory(page,sortBy,orderBy)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceCategoryDTO> getServiceCategoryById(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(serviceCategoryService.getServiceCategoryById(id));
    }

    @PostMapping
    public ResponseEntity<String> saveServiceCategory(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @RequestBody ServiceCategoryDTO serviceCategoryDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(serviceCategoryService.saveServiceCategory(serviceCategoryDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceCategoryDTO> updateServiceCategory(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                                    @PathVariable Long id, @RequestBody ServiceCategoryDTO serviceCategoryDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(serviceCategoryService.updateServiceCategory(id, serviceCategoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteServiceCategory(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(serviceCategoryService.deleteServiceCategory(id));
    }

    @GetMapping("/search/{serviceCategoryName}")
    public ResponseEntity<PagedModel<EntityModel<ServiceCategoryDTO>>> getServiceCategoryByName(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @PathVariable("serviceCategoryName") final String serviceCategoryName,
            PagedResourcesAssembler<ServiceCategoryDTO> assembler) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(serviceCategoryService.getServiceCategoryByName(serviceCategoryName)));
    }
}
