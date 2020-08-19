package com.shree.ecommerce_m_v.shared.imageSlider.repository;

import com.shree.ecommerce_m_v.shared.imageSlider.model.entity.ImageSliderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ImageSliderRepository extends JpaRepository<ImageSliderEntity, Long> {
}
