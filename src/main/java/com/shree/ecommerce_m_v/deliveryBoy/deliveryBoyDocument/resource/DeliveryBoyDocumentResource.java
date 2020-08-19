package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyDocument.resource;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyDocument.model.DTO.DeliveryBoyDocumentDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyDocument.service.DeliveryBoyDocumentService;
import com.shree.ecommerce_m_v.utils.imageUploader.controller.MultipartS3Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deliveryBoyDocument")
public class DeliveryBoyDocumentResource extends MultipartS3Controller {

    @Autowired
    private DeliveryBoyDocumentService deliveryBoyDocumentService;

//    @PostMapping
//    public ResponseEntity<String> saveDeliveryBoyDocument(
//            @RequestHeader(value = "Authorization",required = false) String Authorization,
//            @RequestPart("licenseImage") final MultipartFile licenseImage,
//            @RequestPart("citizenshipImage") final MultipartFile citizenshipImage,
//            @RequestPart("bluebookImage") final MultipartFile bluebookImage,
//            @RequestPart("vehicleImage") final MultipartFile vehicleImage,
//            @RequestPart("deliveryBoyDocumentDTO") final String deliveryBoyDocumentDTO) throws Exception {
//
//        ObjectMapper objectMapper= new ObjectMapper();
//        DeliveryBoyDocumentDTO deliveryBoyDocumentDTO1= objectMapper.readValue(deliveryBoyDocumentDTO,DeliveryBoyDocumentDTO.class);
//        if(licenseImage!=null){
//            deliveryBoyDocumentDTO1.setLicenseImage(uploadImage(licenseImage,
//                    S3ImagePath.deliveryBoyPath+deliveryBoyDocumentDTO1.getDeliveryBoyMergerDTO().getName()+".jpg").getImageUrl());
//        }
//        if(citizenshipImage!=null){
//            deliveryBoyDocumentDTO1.setCitizenshipImage(uploadImage(citizenshipImage,
//                    S3ImagePath.deliveryBoyPath+deliveryBoyDocumentDTO1.getDeliveryBoyMergerDTO().getName()+".jpg").getImageUrl());
//        }
//        if(bluebookImage!=null){
//            deliveryBoyDocumentDTO1.setBluebookImage(uploadImage(bluebookImage,
//                    S3ImagePath.deliveryBoyPath+deliveryBoyDocumentDTO1.getDeliveryBoyMergerDTO().getName()+".jpg").getImageUrl());
//        }
//        if(vehicleImage!=null){
//            deliveryBoyDocumentDTO1.setVehicleImage(uploadImage(vehicleImage,
//                    S3ImagePath.deliveryBoyPath+deliveryBoyDocumentDTO1.getDeliveryBoyMergerDTO().getName()+".jpg").getImageUrl());
//        }
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(deliveryBoyDocumentService.saveDeliveryBoyDocument(deliveryBoyDocumentDTO1));
//    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<DeliveryBoyDocumentDTO>>> getAllDeliveryBoyDocuments(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                                                                      @RequestParam(defaultValue = "0",required = false,value = "page") int page,
                                                                                                      PagedResourcesAssembler<DeliveryBoyDocumentDTO> assembler) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(deliveryBoyDocumentService.getAllDeliveryBoyDocuments(page)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryBoyDocumentDTO> findDeliveryBoyDocumentById(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                                              @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(deliveryBoyDocumentService.findDeliveryBoyDocumentById(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryBoyDocumentDTO> updateDeliveryBoyDocument(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                                            @PathVariable long id, @RequestBody DeliveryBoyDocumentDTO deliveryBoyDocumentDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(deliveryBoyDocumentService.updateDeliveryBoyDocument(id, deliveryBoyDocumentDTO));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDeliveryBoyDocumentById(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                                @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(deliveryBoyDocumentService.deleteDeliveryBoyDocument(id));
    }


}
