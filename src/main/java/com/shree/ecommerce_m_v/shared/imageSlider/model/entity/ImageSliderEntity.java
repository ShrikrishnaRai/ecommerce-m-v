package com.shree.ecommerce_m_v.shared.imageSlider.model.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "slider_images")
public class ImageSliderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageSliderId;
    private String sliderUrl;


}
