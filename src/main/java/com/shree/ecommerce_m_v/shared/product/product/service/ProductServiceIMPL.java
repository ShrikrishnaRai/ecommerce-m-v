package com.shree.ecommerce_m_v.shared.product.product.service;

import com.google.common.base.Joiner;
import com.shree.ecommerce_m_v.shared.product.brand.model.entity.BrandEntity;
import com.shree.ecommerce_m_v.shared.product.category.model.entity.CategoryEntity;
import com.shree.ecommerce_m_v.shared.product.color.model.entity.ColorEntity;
import com.shree.ecommerce_m_v.shared.product.offer.model.entity.OfferEntity;
import com.shree.ecommerce_m_v.shared.product.product.filtering.domain.SearchOperation;
import com.shree.ecommerce_m_v.shared.product.product.filtering.specification.ProductSpecificationBuilder;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductDetailEntity;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import com.shree.ecommerce_m_v.shared.product.product.repository.ProductRepository;
import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductDto;
import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import com.shree.ecommerce_m_v.shared.product.product.service.mapper.ProductMapper;
import com.shree.ecommerce_m_v.shared.product.productBundle.model.entity.ProductBundleEntity;
import com.shree.ecommerce_m_v.shared.product.productBundle.service.dto.ProductBundleMergerDTO;
import com.shree.ecommerce_m_v.shared.product.size.model.entity.SizeEntity;
import com.shree.ecommerce_m_v.shared.product.size.service.dto.SizeMergerDTO;
import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProductServiceIMPL extends ProductMapper implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Page<ProductDto> getProductList(int page, String sortBy, String orderBy, String filter) {
        Specification<ProductEntity> spec = resolveSpecification(filter);
        Pageable pageable = PageRequest.of(page, 20, Sort.by(Sort.Direction.valueOf(orderBy), sortBy));
        Page<ProductEntity> result = productRepository.findAll(spec, pageable);
        return result.map(this::toDTO);
    }

    @Override
    public ProductDto getProductWithId(Long id) {
        return toDTO(productRepository.getOne(id));
    }

    @Override
    public String saveProduct(ProductDto productDTO) {
        productRepository.save(toEntity(productDTO));
        return ResponseValue.SAVE_SUCCESS;
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDTO) {
        ProductEntity productEntity = toEntity(productDTO);

        if (productDTO.getBrandMergerDTO() != null) {
            productEntity.setBrandEntity(BrandEntity.builder()
                    .brandId(productDTO.getBrandMergerDTO().getBrandId())
                    .brandName(productDTO.getBrandMergerDTO().getBrandName())
                    .build());
        }
        if (productDTO.getCategoryMergerDTO() != null) {
            productEntity.setCategoryEntity(CategoryEntity.builder()
                    .categoryId(productDTO.getCategoryMergerDTO().getId())
                    .categoryName(productDTO.getCategoryMergerDTO().getCategoryName())
                    .build());
        }


        if (productDTO.getOfferMergerDTO() != null) {
            productEntity.setOfferEntity(OfferEntity.builder()
                    .offerId(productDTO.getOfferMergerDTO().getOfferId())
                    .offerName(productDTO.getOfferMergerDTO().getOfferName())
                    .build());
        }

        if (productDTO.getProductBundleMergerDTOS().size() != 0) {
            for (ProductBundleMergerDTO productBundleMergerDTO : productDTO.getProductBundleMergerDTOS()) {
                List<ProductBundleEntity> productBundleEntities = new ArrayList<>();
                productBundleEntities.add(ProductBundleEntity.builder()
                        .productBundleId(productBundleMergerDTO.getProductBundleId())
                        .productBundleCode(productBundleMergerDTO.getProductBundleCode())
                        .build());
                productEntity.setProductBundleEntityList(productBundleEntities);
            }
        }
        List<SizeEntity> sizeEntityList = new ArrayList<>();
        for (SizeMergerDTO sizeMergerDTO : productDTO.getProductDetailDTOS().getSizeEntity()) {
            sizeEntityList.add(SizeEntity.builder()
                    .sizeId(sizeMergerDTO.getSizeId())
                    .size(sizeMergerDTO.getSize())
                    .build());
        }
        if (productDTO.getProductDetailDTOS() != null) {
            productEntity.setProductDetailEntities(productDTO.getProductDetailDTOS() != null ?
                    ProductDetailEntity.builder()
                            .sizeEntityList(sizeEntityList)
                            .colorEntity(productEntity.getProductDetailEntities().getColorEntity() != null ? (ColorEntity.builder()
                                    .colorId(productEntity.getProductDetailEntities().getColorEntity().getColorId())
                                    .colorName(productEntity.getProductDetailEntities().getColorEntity().getColorName())
                                    .colorCode(productEntity.getProductDetailEntities().getColorEntity().getColorCode())
                                    .build()) : null)
                            .productEntity(productEntity)
                            .totalQuantity(productDTO.getProductDetailDTOS().getTotalQuantity())
                            .remainingQuantity(productDTO.getProductDetailDTOS().getRemainingQuantity())
                            .isAvailable(productDTO.getProductDetailDTOS().isAvailable())
                            .build() : null);
        }

        productEntity.setProductCode(productDTO.getProductCode());
        productEntity.setProductName(productDTO.getProductName());
        productEntity.setSlug(productDTO.getSlug());
        productEntity.setQuality(productDTO.getQuality());
        productEntity.setSalePrice(productDTO.getSalePrice());
        productEntity.setPurchasePrice(productDTO.getPurchasePrice());
        productEntity.setDiscountType(productDTO.getDiscountType());
        productEntity.setDiscountValue(productDTO.getDiscountValue());
        productEntity.isTaxable();
        productEntity.setTaxAmount(productDTO.getTaxAmount());
        productEntity.setTaxType(productDTO.getTaxType());
        productEntity.isPrebooking();
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setStatus(productDTO.getStatus());
        productEntity.setPackagingFee(productDTO.getPackagingFee());
        productEntity.setUnit(productDTO.getUnit());
        productEntity.setProductType(productDTO.getProductType());

        productRepository.saveAndFlush(productEntity);
        return toDTO(productEntity);

    }

    @Override
    public Page<ProductDto> searchProductWithName(String productName, String sortBy, String orderBy) {


        Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Direction.valueOf(orderBy), sortBy));
        Page<ProductEntity> products = productRepository.getProductWithName(productName, pageable);
        return products.map(this::toDTO);
    }

    @Override
    public String deleteProductWithId(Long id) {
        productRepository.deleteById(id);
        return ResponseValue.DELETE_SUCCESS;
    }


    @Override
    public Page<ProductMergerDTO> getProductMergerDTOList(int page) {
        Pageable pageable = PageRequest.of(page, 20);
        Page<ProductEntity> result = productRepository.findAll(pageable);
        return result.map(this::toMergerDTO);
    }

    @Override
    public List<ProductDto> getProductListByCategories(int categoryId) {
        return toDTOList(productRepository.getProductListByCategories(categoryId));
    }

    protected Specification<ProductEntity> resolveSpecification(String filter) {
        ProductSpecificationBuilder builder = new ProductSpecificationBuilder();
        String operationSetExper = Joiner.on("|")
                .join(SearchOperation.SIMPLE_OPERATION_SET);
        Pattern pattern = Pattern.compile(
                "(\\p{Punct}?)(\\w+?)("
                        + operationSetExper
                        + ")(\\w+?),");
        Matcher matcher = pattern.matcher(filter + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1),
                    matcher.group(2),
                    matcher.group(3),
                    matcher.group(4));
        }
        return builder.build();
    }


}
