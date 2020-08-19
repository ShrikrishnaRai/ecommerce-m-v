package com.shree.ecommerce_m_v.shared.product.productReview.resource;

import com.shree.ecommerce_m_v.shared.product.productReview.service.dto.ProductReviewDTO;
import com.shree.ecommerce_m_v.shared.product.productReview.service.ProductReviewService;
import com.shree.ecommerce_m_v.utils.values.AuthoritiesConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * @author Shree Rai
 * @Date 2/7/20
 */
@RestController
@RequestMapping("/productReview")
public class ProductReviewResource {
    @Autowired
    private ProductReviewService productReviewService;

    @PostMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<String> postProductReview(
            @RequestHeader(value = "Authorization", required = false) String Authorization,
            @RequestBody ProductReviewDTO productReviewDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productReviewService.saveProductReview(productReviewDTO));
    }

    @GetMapping
    public ResponseEntity<List<ProductReviewDTO>> getProductReviews(
            @RequestHeader(value = "Authorization", required = false) String Authorization) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productReviewService.getProductReviews());
    }

    @RequestMapping(value = "/{reviewId}", method = RequestMethod.GET)
    public ResponseEntity<ProductReviewDTO> getProductReviewsWithId(
            @RequestHeader(value = "Authorization", required = false) String Authorization,
            @PathVariable("reviewId") @Min(1) final Long reviewId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productReviewService.getProductReviewById(reviewId));
    }

    @RequestMapping(value = "product/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<ProductReviewDTO>> getProductReviewsWithProductId(
            @RequestHeader(value = "Authorization", required = false) String Authorization,
            @PathVariable("productId") @Min(1) final Long productId) {
        Logger logger = LoggerFactory.getLogger(ProductReviewResource.class);
        logger.info("product id::" + productId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(productReviewService.getReviewOfProductWithProductId(productId));
    }

    @RequestMapping(value = "customer/{customerId}", method = RequestMethod.GET)
    public ResponseEntity<List<ProductReviewDTO>> getProductReviewsWithCustomerId(
            @RequestHeader(value = "Authorization", required = false) String Authorization,
            @PathVariable("customerId") @Min(1) final Long customerId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productReviewService.getReviewOfProductByCustomerId(customerId));
    }

    @RequestMapping(value = "customer/{customerId}/product/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<ProductReviewDTO>> getProductReviewsWithCustomerAndProductId(
            @RequestHeader(value = "Authorization", required = false) String Authorization,
            @PathVariable("customerId") @Min(1) final Long customerId,
            @PathVariable("productId") @Min(1) final Long productId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productReviewService.getReviewOfProductByCustomerIdAndProductId(customerId, productId));
    }

    @PutMapping("/{reviewId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<ProductReviewDTO> updateProductReview(
            @RequestHeader(value = "Authorization", required = false) String Authorization,
            @RequestParam("rating") float rating,
            @RequestParam("review") String review,
            @RequestParam("reply") String reply,
            @PathVariable Long reviewId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productReviewService.updateProductReview(reviewId, new ProductReviewDTO(rating, review, reply)));
    }

//

}
