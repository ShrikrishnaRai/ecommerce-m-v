package com.shree.ecommerce_m_v.shared.blog.blogCategory.resource;

import com.shree.ecommerce_m_v.shared.blog.blogCategory.model.DTO.BlogCategoryDTO;
import com.shree.ecommerce_m_v.shared.blog.blogCategory.service.BlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogCategory")
public class BlogCategoryResource {

    @Autowired
    private BlogCategoryService blogCategoryService;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<BlogCategoryDTO>>> getAllBlogCategory(
            @RequestHeader(value="Authorization",required = false) String Authorization,
            @RequestParam(value="page",defaultValue = "0",required = false) int page,
            @RequestParam(value = "sortBy" ,defaultValue = "blogCategory",required = false)String sortBy,
            @RequestParam(value = "orderBy",defaultValue = "ASC",required = false)String orderBy,
            PagedResourcesAssembler<BlogCategoryDTO> assembler){
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(blogCategoryService.getAllBlogCategory(page, sortBy, orderBy)));
    }

    @PostMapping
    public ResponseEntity<String> saveBlogCategory(
            @RequestHeader(value="Authorization",required = false) String Authorization,
           @RequestBody final BlogCategoryDTO blogCategoryDTO){
        return ResponseEntity.status(HttpStatus.OK)
                .body(blogCategoryService.saveBlogCategory(blogCategoryDTO));
    }

    @GetMapping("/{blogCategoryId}")
    public ResponseEntity<BlogCategoryDTO> getBlogCategoryById(
            @RequestHeader(value="Authorization",required = false) String Authorization,
           @PathVariable("blogCategoryId") final Long blogCategoryId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(blogCategoryService.getBlogCategoryById(blogCategoryId));
    }

    @PutMapping("/{blogCategoryId}")
    public ResponseEntity<BlogCategoryDTO> updateBlogCategory(
            @RequestHeader(value="Authorization",required = false) String Authorization,
            @PathVariable("blogCategoryId") final Long blogCategoryId,
            @RequestBody BlogCategoryDTO blogCategoryDTO){
        return ResponseEntity.status(HttpStatus.OK)
                .body(blogCategoryService.updateBlogCategory(blogCategoryId,blogCategoryDTO));
    }

    @DeleteMapping("/{blogCategoryId}")
    public ResponseEntity<String> deleteBlogCategory(
            @RequestHeader(value="Authorization",required = false) String Authorization,
            @PathVariable("blogCategoryId")final Long blogCategoryId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(blogCategoryService.deleteBlogCategory(blogCategoryId));
    }

}
