package com.shree.ecommerce_m_v.shared.product.product.filtering.specification;

import com.shree.ecommerce_m_v.shared.product.product.filtering.domain.SearchCriteria;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class ProductSpecification implements Specification<ProductEntity> {


    private SearchCriteria criteria;


    public ProductSpecification(final SearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    public SearchCriteria getCriteria() {
        return criteria;
    }

    @Override
    public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        switch (criteria.getOperation()) {
            case EQUALITY:
                return builder.equal(root.get(
                        criteria.getKey()), criteria.getValue());

            case GREATER_THAN_OR_EQUAL_TO:
                return builder.greaterThanOrEqualTo(root.<String>get(
                        criteria.getKey()), criteria.getValue().toString());

            case LESS_THAN_OR_EQUAL_TO:
                return builder.lessThanOrEqualTo(root.<String>get(
                        criteria.getKey()), criteria.getValue().toString());

            case LIKE:
                return builder.like(root.<String>get(
                        criteria.getKey()), criteria.getValue().toString());

//            case BETWEEN:
//                return builder.between(root.get(
//                        criteria.getKey()),criteria.getValue().toString(),criteria.getValue().toString());

            case CONTAINS:
                return builder.like(root.<String>get(
                        criteria.getKey()), "%" + criteria.getValue() + "%");

            default:
                return null;
        }
    }
}
