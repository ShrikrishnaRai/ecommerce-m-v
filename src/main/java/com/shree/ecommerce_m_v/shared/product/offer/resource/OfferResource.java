package com.shree.ecommerce_m_v.shared.product.offer.resource;

import com.shree.ecommerce_m_v.shared.product.offer.service.dto.OfferDTO;
import com.shree.ecommerce_m_v.shared.product.offer.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/offer")
public class OfferResource {
    private final OfferService offerService;

    @Autowired
    public OfferResource(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<OfferDTO>>> getAllOffers(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @RequestParam(defaultValue = "0",required = false,value = "page")int page,
            @RequestParam(defaultValue = "discountValue",required = false,value = "sortBy") String sortBy,
            @RequestParam(defaultValue = "ASC",required = false,value = "orderBy")String orderBy,
            PagedResourcesAssembler<OfferDTO> assembler) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(offerService.getAllOffers(page,sortBy,orderBy)));
    }

    @GetMapping("/{offerId}")
    public ResponseEntity<OfferDTO> getOfferById(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                 @PathVariable("offerId") final Long offerId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(offerService.getOfferById(offerId));
    }

    @PostMapping
    public ResponseEntity<String> saveOffer(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                            @RequestBody OfferDTO offerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(offerService.saveOffer(offerDTO));
    }

    @DeleteMapping("/{offerId}")
    public ResponseEntity<String> deleteOfferWithId(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                    @PathVariable Long offerId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(offerService.deleteOfferWithId(offerId));
    }

    @PutMapping("/{offerId}")
    public ResponseEntity<OfferDTO> updateOffer(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                @PathVariable Long offerId,
                                                @RequestBody OfferDTO offerDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(offerService.updateOffer(offerId, offerDTO));
    }
}
