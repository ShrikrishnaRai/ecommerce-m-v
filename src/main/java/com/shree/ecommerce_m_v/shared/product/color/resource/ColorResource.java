package com.shree.ecommerce_m_v.shared.product.color.resource;

import com.shree.ecommerce_m_v.shared.product.color.service.ColorService;
import com.shree.ecommerce_m_v.shared.product.color.service.dto.ColorDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/color")
@Api(tags = "Color" ,description = "Color for products")
public class ColorResource {

    @Autowired
    private ColorService colorService;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<ColorDTO>>> getColors(
            @RequestHeader(value="Authorization",required = false) String Authorization,
            @RequestParam(value = "page",defaultValue = "0",required = false) int page,
            PagedResourcesAssembler<ColorDTO> assembler){
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(colorService.getColors(page)));
    }

    @GetMapping("/{colorId}")
    public ResponseEntity<ColorDTO> getColorById(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                 @PathVariable("colorId") Long colorId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(colorService.getColorById(colorId));
    }

    @PostMapping
    public ResponseEntity<String> saveColors(
            @RequestHeader(value="Authorization",required = false) String Authorization,
            @RequestBody ColorDTO colorDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(colorService.saveColors(colorDTO));
    }

    @DeleteMapping("/{colorId}")
    public ResponseEntity<String> deleteColors( @RequestHeader(value="Authorization",required = false) String Authorization,
                                                @PathVariable("colorId") Long colorId){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(colorService.deleteColors(colorId));
    }

    @PutMapping("/{colorId}")
    public ResponseEntity<ColorDTO> updateColor(
            @RequestHeader(value="Authorization",required = false) String Authorization,
            @PathVariable("colorId")Long colorId,
            @RequestBody ColorDTO colorDTO){
        return ResponseEntity.status(HttpStatus.OK)
                .body(colorService.updateColor(colorId,colorDTO));
    }
}
