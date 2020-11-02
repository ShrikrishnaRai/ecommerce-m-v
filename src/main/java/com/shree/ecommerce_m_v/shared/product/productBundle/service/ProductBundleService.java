package com.shree.ecommerce_m_v.shared.product.productBundle.service;

import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import com.shree.ecommerce_m_v.shared.product.product.repository.ProductRepository;
import com.shree.ecommerce_m_v.shared.product.productBundle.service.dto.ProductBundleDTO;
import com.shree.ecommerce_m_v.shared.product.productBundle.model.entity.ProductBundleEntity;
import com.shree.ecommerce_m_v.shared.product.productBundle.repository.ProductBundleRepository;
import com.shree.ecommerce_m_v.shared.product.productBundle.service.mapper.ProductBundleMapper;
import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductBundleService extends ProductBundleMapper  {

    @Autowired
    private ProductBundleRepository productBundleRepository;

    @Autowired
    private ProductRepository productRepository;

    public String saveProductBundle(ProductBundleDTO productBundleDTO) {

        ProductBundleEntity productBundleEntity=toEntity(productBundleDTO);
        for(ProductMergerDTO productMergerDTO : productBundleDTO.getProductMergerDTOList()){
            if(productRepository.existsById(productMergerDTO.getProductId())){
                productBundleEntity.addProduct(productRepository.getOne(productMergerDTO.getProductId()));
            }
        }
        productBundleRepository.saveAndFlush(productBundleEntity);
        return ResponseValue.SAVE_SUCCESS;
    }

    public Page<ProductBundleDTO> getProductBundleList(int page,String sortBy,String orderBy) {
        Pageable pageable = PageRequest.of(page,10, Sort.by(Sort.Direction.valueOf(orderBy),sortBy));
        Page<ProductBundleEntity> productBundles = productBundleRepository.findAll(pageable);
        return productBundles.map(productBundleEntity -> toDTO(productBundleEntity));
    }

    public ProductBundleDTO getProductBundleById(Long productBundleId) {
        return toDTO(productBundleRepository.getOne(productBundleId));
    }

    public String deleteProductBundleWithId(Long id) {
        ProductBundleEntity productBundleEntity= productBundleRepository.getOne(id);
        if(productBundleEntity.getProductEntityList().size()!=0){
            for(int i=productBundleEntity.getProductEntityList().size()-1;i>=0;--i){
                productBundleEntity.removeProduct(productBundleEntity.getProductEntityList().get(i));
            }
        }
        productBundleRepository.deleteById(id);
        return ResponseValue.DELETE_UN_SUCCESS;
    }

    public ProductBundleDTO updateProductBundle(long id, ProductBundleDTO productBundleDTO) {

        ProductBundleEntity productBundleEntity=productBundleRepository.getOne(id);
        if(productBundleDTO.getProductMergerDTOList().size()!=0){
            for(ProductEntity productEntity : super.getProductEntities()){
                if(productRepository.existsById(productEntity.getProductId())){
                    productBundleEntity.addProduct(productRepository.getOne(productEntity.getProductId()));
                }
            }
        }

        productBundleEntity.setProductBundleCode(productBundleDTO.getProductBundleCode());
        productBundleEntity.setProductBundleName(productBundleDTO.getProductBundleName());
        productBundleEntity.setProductBundlePrice(productBundleDTO.getProductBundlePrice());
        productBundleEntity.setStatus(productBundleDTO.getStatus());
        productBundleEntity.setTag(productBundleDTO.getTag());
//        productBundleEntity.isTaxable(productBundleDTO.isTaxable());
        productBundleEntity.setTaxAmount(productBundleDTO.getTaxAmount());
        productBundleEntity.setTotalQuantity(productBundleDTO.getTotalQuantity());
        productBundleEntity.setRemainingQuantity(productBundleDTO.getRemainingQuantity());
        productBundleRepository.saveAndFlush(productBundleEntity);
        return toDTO(productBundleEntity);

    }
}
