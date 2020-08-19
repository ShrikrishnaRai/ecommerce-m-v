package com.shree.ecommerce_m_v.shared.product.category.resource;

import com.shree.ecommerce_m_v.shared.product.category.service.CategoryService;
import com.shree.ecommerce_m_v.shared.product.category.service.dto.CategoryDTO;
import com.shree.ecommerce_m_v.shared.product.category.service.dto.CategoryIdAndName;
import com.shree.ecommerce_m_v.utils.imageUploader.controller.MultipartS3Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryResource extends MultipartS3Controller {

    private final CategoryService categoryService;

    @Autowired
    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //This method is used to display category in the homepage
    @GetMapping("/namesList")
    public ResponseEntity<List<CategoryIdAndName>> getListOfCategoriesNameAndId() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.getCategoryNameAndId());
    }

    @GetMapping
    public ResponseEntity<PagedModel<CategoryDTO>> getCategoryList(
            @RequestHeader(value = "Authorization", required = false) final String Authorization,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page, PagedResourcesAssembler assembler) {
        return new ResponseEntity<>(assembler.toModel(categoryService.getCategoryList(page)), HttpStatus.OK);
    }

    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
    public ResponseEntity<CategoryDTO> getCategoryWithId(@RequestHeader(value = "Authorization", required = false) final String Authorization,
                                                         @PathVariable("categoryId") @Min(1) final Long categoryId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.getCategoryWithID(categoryId));
    }

//    @PostMapping
//    public ResponseEntity<String> saveCategory(@RequestHeader(value = "Authorization", required = false) final String Authorization,
//                                               @RequestPart(value = "photos",required = false)MultipartFile multipartFile,
//                                               @RequestPart("categoryDTO") final String categoryDTO) throws Exception {
//        ObjectMapper objectMapper= new ObjectMapper();
//        CategoryDTO categoryDTO1= objectMapper.readValue(categoryDTO,CategoryDTO.class);
//        if(multipartFile!=null){
//            categoryDTO1.setCategoryImage(uploadImage(multipartFile, S3ImagePath.categoryPath).getImageUrl());
//        }
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(categoryService.saveCategory(categoryDTO1));
//    }

    @DeleteMapping(value = "/{categoryId}")
    public ResponseEntity<String> deleteCategoryWithId(@RequestHeader(value = "Authorization", required = false) final String Authorization,
                                                       @PathVariable("categoryId") final Long categoryId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(categoryService.deleteCategoryWithId(categoryId));
    }

    @RequestMapping(value = "/search/{categoryName}", method = RequestMethod.GET)
    public ResponseEntity<PagedModel<CategoryDTO>> getCategoryWithName(@RequestHeader(value = "Authorization", required = false) final String Authorization,
                                                                       @PathVariable("categoryName") final String categoryName, PagedResourcesAssembler assembler) {
        return new ResponseEntity<>(assembler.toModel(categoryService.getCategoryWithName(categoryName)), HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateBrand(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                   @RequestBody final CategoryDTO categoryDTO,
                                                   @PathVariable final Long categoryId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.updateCategory(categoryId, categoryDTO));
    }
}
