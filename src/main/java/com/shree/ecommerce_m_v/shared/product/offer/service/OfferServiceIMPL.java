package com.shree.ecommerce_m_v.shared.product.offer.service;

import com.shree.ecommerce_m_v.shared.product.offer.service.dto.OfferDTO;
import com.shree.ecommerce_m_v.shared.product.offer.model.entity.OfferEntity;
import com.shree.ecommerce_m_v.shared.product.offer.repository.OfferRepository;
import com.shree.ecommerce_m_v.shared.product.offer.service.mapper.OfferMapper;
import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import com.shree.ecommerce_m_v.shared.product.product.repository.ProductRepository;
import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OfferServiceIMPL extends OfferMapper implements OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<OfferDTO> getAllOffers(int page,String sortBy,String orderBy) {
        Pageable pageable= PageRequest.of(page,20, Sort.by(Sort.Direction.valueOf(orderBy),sortBy));
        Page<OfferEntity> offers = offerRepository.findAll(pageable);
        return offers.map(offerEntity -> toDTO(offerEntity));
    }

    @Override
    public OfferDTO getOfferById(Long offerId) {
        return toDTO(offerRepository.getOne(offerId));
    }

    @Override
    public String deleteOfferWithId(Long id) {

        OfferEntity offerEntity=offerRepository.getOne(id);
        if(offerEntity.getProductEntityList().size()!=0){
            for(int i=offerEntity.getProductEntityList().size()-1;i>=0;--i){
                offerEntity.removeOfferFromProduct(offerEntity.getProductEntityList().get(i));
            }
        }
        offerRepository.deleteById(id);
        return ResponseValue.DELETE_SUCCESS;
    }

    @Override
    public String saveOffer(OfferDTO offerDTO) {

        OfferEntity offerEntity=toEntity(offerDTO);
        for(ProductMergerDTO productMergerDTO:offerDTO.getProductMergerDTOList()){
            if(productRepository.existsById(productMergerDTO.getProductId())){
                offerEntity.addOfferToProduct(productRepository.getOne(productMergerDTO.getProductId()));
            }
        }
        offerRepository.saveAndFlush(offerEntity);
        return ResponseValue.SAVE_SUCCESS;
    }

    @Override
    public OfferDTO updateOffer(Long id, OfferDTO offerDTO) {
        OfferEntity offerEntity=offerRepository.getOne(id);
        offerEntity.setOfferName(offerDTO.getOfferName());
        offerEntity.setDescription(offerDTO.getDescription());
        offerEntity.setValidFrom(offerDTO.getValidFrom());
        offerEntity.setValidTo(offerDTO.getValidTo());
        offerEntity.setStatus(offerDTO.getStatus());
        offerEntity.setDiscountValue(offerDTO.getDiscountValue());
        offerEntity.setDiscountType(offerDTO.getDiscountType());
        offerEntity.setMinimumOrder(offerDTO.getMinimumOrder());
        if(offerDTO.getProductMergerDTOList().size()!=0){
            for(ProductMergerDTO productMergerDTO:offerDTO.getProductMergerDTOList()){
                if(productRepository.existsById(productMergerDTO.getProductId())){
                    offerEntity.addOfferToProduct(productRepository.getOne(productMergerDTO.getProductId()));
                }
            }
        }
        if(offerEntity.getVendorEntity()!=null){
            offerEntity.setVendorEntity(VendorEntity.builder()
                    .vendorId(offerDTO.getVendorMergerDTO().getVendorId())
                    .vendorName(offerDTO.getVendorMergerDTO().getVendorName())
                    .build());
        }

        offerRepository.saveAndFlush(offerEntity);

        return toDTO(offerEntity);
    }
}
