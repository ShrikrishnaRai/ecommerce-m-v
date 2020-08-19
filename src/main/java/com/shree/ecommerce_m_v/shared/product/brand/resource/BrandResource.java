package com.shree.ecommerce_m_v.shared.product.brand.resource;

import com.shree.ecommerce_m_v.shared.product.brand.service.BrandService;
import com.shree.ecommerce_m_v.shared.product.brand.service.dto.BrandDTO;
import com.shree.ecommerce_m_v.shared.product.brand.service.dto.BrandRequestDTO;
import com.shree.ecommerce_m_v.utils.imageUploader.ImageEncoder.ImageDecode;
import com.shree.ecommerce_m_v.utils.imageUploader.controller.MultipartS3Controller;
import com.shree.ecommerce_m_v.utils.values.AuthoritiesConstants;
import com.shree.ecommerce_m_v.utils.values.S3ImagePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/brand")
public class BrandResource extends MultipartS3Controller {

    private final BrandService brandService;

    private final ImageDecode imageDecode;

    @Autowired
    public BrandResource(BrandService brandService, ImageDecode imageDecode) {
        this.brandService = brandService;
        this.imageDecode = imageDecode;
    }

    @GetMapping
    public ResponseEntity<PagedModel<BrandDTO>> getBrandsList(
            @RequestHeader(value = "Authorization", required = false) final String Authorization,
            @RequestParam(defaultValue = "0", required = false, value = "page") final int page,
            @RequestParam(defaultValue = "brandId", required = false, value = "sortBy") final String sortBy,
            @RequestParam(defaultValue = "ASC", required = false, value = "orderBy") final String orderBy,
            PagedResourcesAssembler assembler) {
        return new ResponseEntity<>(assembler.toModel(brandService.getBrandList(page, sortBy, orderBy)), HttpStatus.OK);
    }


    @PostMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<String> saveBrand(@RequestHeader(value = "Authorization", required = false) final String Authorization,
                                            @RequestBody BrandRequestDTO brandRequestDTO) throws Exception {
        String unwantedText = "data:image/jpeg;base64,";
        brandRequestDTO.setBrandImage(
                uploadImage(imageDecode.decodeImage(brandRequestDTO.getFileDTO().getBase64().replace(unwantedText, "")
                        , brandRequestDTO.getFileDTO().getName() + "." + brandRequestDTO.getFileDTO().getType()),
                        S3ImagePath.brandPath).getImageUrl());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(brandService.saveBrand(brandRequestDTO));
    }

    @GetMapping("/{brandId}")
    public ResponseEntity<BrandDTO> getBrandById(
            @RequestHeader(value = "Authorization", required = false) final String Authorization,
            @PathVariable("brandId") final Long brandId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(brandService.getBrandWithId(brandId));
    }

    @RequestMapping(value = "/search/{brandName}", method = RequestMethod.GET)
    public ResponseEntity<List<BrandDTO>> getBrandWithName(@RequestHeader(value = "Authorization", required = false) final String Authorization,
                                                           @PathVariable("brandName") final String brandName) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(brandService.searchBrandWithName(brandName));
    }

    @PutMapping("/{brandId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<BrandDTO> updateBrand(@RequestHeader(value = "Authorization", required = false) final String Authorization,
                                                @RequestBody final BrandDTO brandDTO, @PathVariable final Long brandId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(brandService.updateBrand(brandId, brandDTO));
    }

    @DeleteMapping("/{brandId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<String> deleteBrand(@RequestHeader(value = "Authorization", required = false) final String Authorization,
                                              @PathVariable final Long brandId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(brandService.deleteBrandWithId(brandId));
    }
}
