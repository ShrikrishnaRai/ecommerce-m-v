package com.shree.ecommerce_m_v.shared.product.product.resource;

import com.shree.ecommerce_m_v.shared.product.product.service.ProductService;
import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductDto;
import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import com.shree.ecommerce_m_v.shared.product.productImage.service.ProductImageService;
import com.shree.ecommerce_m_v.utils.imageUploader.controller.MultipartS3Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductResource extends MultipartS3Controller {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductImageService productImageService;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<ProductDto>>> getProductList(
            @RequestHeader(value = "Authorization", required = false) String Authorization,
            @RequestParam(defaultValue = "0", required = false, value = "page") int page,
            @RequestParam(defaultValue = "productId", required = false, value = "sortBY") String sortBy,
            @RequestParam(defaultValue = "ASC", required = false, value = "orderBy") String orderBy,
            @RequestParam(value = "filter", required = false) String filter,
            PagedResourcesAssembler<ProductDto> assembler) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(productService.getProductList(page, sortBy, orderBy, filter)));
    }

    @GetMapping("/productMergerList")
    public ResponseEntity<PagedModel<EntityModel<ProductMergerDTO>>> getProductMergerDTOList(
            @RequestHeader(value = "Authorization", required = false) String Authorization,
            @RequestParam(defaultValue = "0", required = false, value = "page") int page,
            PagedResourcesAssembler<ProductMergerDTO> assembler) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(productService.getProductMergerDTOList(page)));
    }

//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<String> saveProduct(@RequestHeader(value = "Authorization", required = false) final String Authorization,
//                                              @ApiParam(name = "photos", value = "Select images to Upload", required = false, allowMultiple = true) @RequestParam(value = "photos", required = false) final MultipartFile[] photos,
//                                              @RequestPart("productDTO") final String productDTO) throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper();
//        ProductDTO productDTO1 = objectMapper.readValue(productDTO, ProductDTO.class);
//        if (photos != null) {
//            List<ProductImageMergerDTO> productImageMergerDTOS = new ArrayList<>();
//            for (MultipartFile photo : photos) {
//                ProductImageMergerDTO productImageMergerDTO = ProductImageMergerDTO.builder()
//                        .productImage(uploadImage(photo, S3ImagePath.productPath).getImageUrl())
//                        .build();
//                productImageMergerDTOS.add(productImageMergerDTO);
//            }
//            productDTO1.setProductImageMergerDTOS(productImageMergerDTOS);
//            productDTO1.setProductImageUrl(productImageMergerDTOS.get(0).getProductImage());
//        }
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(productService.saveProduct(productDTO1));
//    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<String> deleteProductWithId(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                      @PathVariable("productId") Long productId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(productService.deleteProductWithId(productId));
    }


    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public ResponseEntity<ProductDto> getProductById(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                     @PathVariable("productId") @Min(1) final Long productId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getProductWithId(productId));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                    @RequestBody final ProductDto productDTO, @PathVariable final Long productId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.updateProduct(productId, productDTO));
    }

    @RequestMapping(value = "/search/{productName}", method = RequestMethod.GET)
    public ResponseEntity<PagedModel<EntityModel<ProductDto>>> getProductWithName(
            @RequestHeader(value = "Authorization", required = false) String Authorization,
            @PathVariable("productName") final String productName,
            @RequestParam(defaultValue = "product_id", value = "sortBy", required = false) String sortBY,
            @RequestParam(defaultValue = "ASC", value = "orderBy", required = false) String orderBy,
            PagedResourcesAssembler<ProductDto> assembler) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(productService.searchProductWithName(productName, sortBY, orderBy)));
    }

    @GetMapping(value = "/category/{categoryId}")
    public ResponseEntity<List<ProductDto>> getProductListByCategory(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                                     @PathVariable("categoryId") int categoryId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getProductListByCategories(categoryId));
    }
}
