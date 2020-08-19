package com.shree.ecommerce_m_v.shared.blog.blog.resource;

import com.shree.ecommerce_m_v.shared.blog.blog.model.DTO.BlogDTO;
import com.shree.ecommerce_m_v.shared.blog.blog.service.BlogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
@Api(value = "Blog",description = "")
public class BlogResource {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<BlogDTO>>> getAllBlogs(
            @RequestHeader(value="Authorization",required = false)String Authorization,
            @RequestParam(value="page",required = false,defaultValue ="0")int page,
            @RequestParam(value="sortBy",required = false,defaultValue = "blogId")String sortBy,
            @RequestParam(value="orderBy",required = false,defaultValue = "ASC")String orderBy,
            PagedResourcesAssembler<BlogDTO> assembler){

            return ResponseEntity.status(HttpStatus.OK)
            .body(assembler.toModel(blogService.getAllBlogs(page, sortBy, orderBy)));
    }

    @PostMapping
    public ResponseEntity<String> saveBlog(
            @RequestHeader(value="Authorization",required = false)String Authorization,
            @RequestBody final BlogDTO blogDTO){
        return ResponseEntity.status(HttpStatus.OK)
                .body(blogService.saveBlog(blogDTO));
    }

    @GetMapping("/{blogId}")
    public ResponseEntity<BlogDTO> getBlogById(
            @RequestHeader(value="Authorization",required = false)String Authorization,
            @PathVariable("blogId")final Long blogId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(blogService.getBlogById(blogId));
    }

    @PutMapping("/{blogId}")
    public ResponseEntity<BlogDTO> updateBlog(
            @RequestHeader(value="Authorization",required = false)String Authorization,
            @PathVariable("blogId")final Long blogId,
            @RequestBody final BlogDTO blogDTO){
        return ResponseEntity.status(HttpStatus.OK)
                .body(blogService.updateBlog(blogId, blogDTO));
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<String> deleteBlog(
            @RequestHeader(value="Authorization",required = false)String Authorization,
            @PathVariable("blogId")final Long blogId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(blogService.deleteBlog(blogId));
    }
}
