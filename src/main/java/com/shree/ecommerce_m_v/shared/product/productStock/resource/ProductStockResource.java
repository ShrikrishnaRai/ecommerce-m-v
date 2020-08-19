package com.shree.ecommerce_m_v.shared.product.productStock.resource;

import com.shree.ecommerce_m_v.shared.product.productStock.service.dto.ProductStockDTO;
import com.shree.ecommerce_m_v.shared.product.productStock.service.ProductStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productStock")
public class ProductStockResource {

    @Autowired
    private ProductStockService productStockService;

    @GetMapping
    public ResponseEntity<List<ProductStockDTO>> getProductStocks(
            @RequestHeader(value = "Authorization",required = false) String Authorization){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productStockService.getProductsStockLists());
    }

    @GetMapping("/{productStockId}")
    public ResponseEntity<ProductStockDTO> getProductStockById(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                               @PathVariable("productStockId") Long productStockId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productStockService.getProductStockById(productStockId));
    }

    @PostMapping
    public ResponseEntity<String> saveProductStock(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @RequestBody ProductStockDTO productStockDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productStockService.saveProduct(productStockDTO));
    }

    @DeleteMapping("/{productStockId}")
    public ResponseEntity<String> deleteProductStock(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @PathVariable("productStockId") Long productStockId){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(productStockService.deleteProductStockWithId(productStockId));
    }

    @PutMapping("/{productStockId}")
    public ResponseEntity<ProductStockDTO> updateProductStock(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @PathVariable("productStockId") Long productStockId,
            @RequestBody ProductStockDTO productStockDTO){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productStockService.updateProduct(productStockId,productStockDTO));
    }
}
