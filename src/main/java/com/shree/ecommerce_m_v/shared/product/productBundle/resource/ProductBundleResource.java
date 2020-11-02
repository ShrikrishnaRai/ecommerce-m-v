package com.shree.ecommerce_m_v.shared.product.productBundle.resource;

import com.shree.ecommerce_m_v.shared.product.productBundle.service.ProductBundleService;
import com.shree.ecommerce_m_v.shared.product.productBundle.service.dto.ProductBundleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product_bundle")
public class ProductBundleResource {

    private final ProductBundleService productBundleService;

    @Autowired
    public ProductBundleResource(ProductBundleService productBundleService) {
        this.productBundleService = productBundleService;
    }

    @PostMapping
    public ResponseEntity<String> saveProductBundle(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                    @RequestBody ProductBundleDTO productBundleDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productBundleService.saveProductBundle(productBundleDTO));
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<ProductBundleDTO>>> getProductBundleLists(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @RequestParam(defaultValue = "0",value = "page",required = false)int page,
            @RequestParam(defaultValue = "productBundleCode",required = false,value = "sortBy")String sortBy,
            @RequestParam(defaultValue = "ASC",required = false,value = "orderBy")String orderBy,
            PagedResourcesAssembler<ProductBundleDTO> assembler) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(productBundleService.getProductBundleList(page,sortBy,orderBy)));
    }

    @GetMapping("/{productBundleId}")
    public ResponseEntity<ProductBundleDTO> getProductBundleById(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                                 @PathVariable("productBundleId") Long productBundleId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productBundleService.getProductBundleById(productBundleId));
    }

    @PutMapping("/{productBundleId}")
    public ResponseEntity<ProductBundleDTO> updateProductBundle(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                      @PathVariable long productBundleId,
                                                      @RequestBody ProductBundleDTO productBundleDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productBundleService.updateProductBundle(productBundleId,productBundleDTO));
    }

    @DeleteMapping("/{productBundleId}")
    public ResponseEntity<String> deleteProductBundleWithId(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                            @PathVariable long productBundleId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(productBundleService.deleteProductBundleWithId(productBundleId));
    }
}
