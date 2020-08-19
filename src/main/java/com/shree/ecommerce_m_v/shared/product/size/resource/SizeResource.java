package com.shree.ecommerce_m_v.shared.product.size.resource;

import com.shree.ecommerce_m_v.shared.product.size.service.dto.SizeMergerDTO;
import com.shree.ecommerce_m_v.shared.product.size.service.SizeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/size")
@Api(tags = "Size" ,description = "Size for products")
public class SizeResource {

    @Autowired
    private SizeService sizeService;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<SizeMergerDTO>>> getSizes(
            @RequestHeader(value="Authorization",required = false) String Authorization,
            @RequestParam(value = "page",defaultValue = "0",required = false) int page,
            PagedResourcesAssembler<SizeMergerDTO> assembler){
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(sizeService.getSize(page)));
    }

    @GetMapping("/{sizeId}")
    public ResponseEntity<SizeMergerDTO> getSizeById(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                     @PathVariable("sizeId") final Long sizeId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(sizeService.getSizeById(sizeId));
    }

    @PostMapping
    public ResponseEntity<String> saveSize( @RequestHeader(value="Authorization",required = false) String Authorization,
                                            @RequestBody SizeMergerDTO sizeDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sizeService.saveSize(sizeDTO));
    }

    @DeleteMapping("/{sizeId}")
    public ResponseEntity<String> deleteSize( @RequestHeader(value="Authorization",required = false) String Authorization,
                                              @PathVariable("sizeId") Long sizeId){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(sizeService.deleteSize(sizeId));
    }

    @PutMapping("/{sizeId}")
    public ResponseEntity<SizeMergerDTO> updateSize(
            @RequestHeader(value="Authorization",required = false) String Authorization,
            @PathVariable("sizeId")Long sizeId,
            @RequestBody SizeMergerDTO sizeDTO){
        return ResponseEntity.status(HttpStatus.OK)
                .body(sizeService.updateSize(sizeId,sizeDTO));
    }
}
