package com.shree.ecommerce_m_v.utils.imageUploader.resource;

import com.shree.ecommerce_m_v.utils.imageUploader.controller.MultipartS3Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uploadImage")
public class ImageUploadResource extends MultipartS3Controller {

//    @PostMapping
//    public ResponseEntity<ImageUrlResponse> uploadPicture(
//            @RequestParam("imageFolderPath") String imagePath,
//            @RequestParam("photo") final MultipartFile multipartFile) throws Exception {
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(uploadImage(multipartFile, imagePath));
//    }

    public String getString() {
        return "Hello World";
    }

}
