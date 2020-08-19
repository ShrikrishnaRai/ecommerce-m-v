package com.shree.ecommerce_m_v.shared.imageSlider.resource;

import com.shree.ecommerce_m_v.shared.imageSlider.model.entity.ImageSliderEntity;
import com.shree.ecommerce_m_v.shared.imageSlider.repository.ImageSliderRepository;
import com.shree.ecommerce_m_v.utils.imageUploader.controller.MultipartS3Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/imageSliders")
public class ImageSliderResource extends MultipartS3Controller {

    private final ImageSliderRepository imageSliderRepository;

    @Autowired
    public ImageSliderResource(ImageSliderRepository imageSliderRepository) {
        this.imageSliderRepository = imageSliderRepository;
    }

//    @PostMapping
//    public String saveImageForImageSlider(@RequestParam("photo") final MultipartFile multipartFile) throws Exception {
//        imageSliderRepository.save(ImageSliderEntity.builder()
//                .sliderUrl(uploadImageSlider(multipartFile).getImageUrl())
//                .build());
//        return "image saved";
//    }

    @GetMapping
    public List<ImageSliderEntity> getImageSlider() {
        return imageSliderRepository.findAll();
    }
}
