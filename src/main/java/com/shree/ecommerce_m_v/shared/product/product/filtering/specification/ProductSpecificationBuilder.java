package com.shree.ecommerce_m_v.shared.product.product.filtering.specification;

import com.shree.ecommerce_m_v.shared.product.product.filtering.domain.SearchCriteria;
import com.shree.ecommerce_m_v.shared.product.product.filtering.domain.SearchOperation;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecificationBuilder {

    private final List<SearchCriteria> params;


    public ProductSpecificationBuilder() {
        params = new ArrayList<>();
    }

    public final ProductSpecificationBuilder with(final String key, final String operation, final Object value) {
        return with(null, key, operation, value);
    }

    public ProductSpecificationBuilder with(
            final String orPredicate, final String key, final String operation, final Object value) {

        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) {
               op=SearchOperation.CONTAINS;
            }
            params.add(new SearchCriteria(orPredicate,key, op, value));
        }
        return this;
    }

    public Specification<ProductEntity> build() {
        if (params.size() == 0)
            return null;

        Specification<ProductEntity> result = new ProductSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(new ProductSpecification(params.get(i)))
                    : Specification.where(result).and(new ProductSpecification(params.get(i)));
        }

        return result;
    }

    public final ProductSpecificationBuilder with(ProductSpecification spec) {
        params.add(spec.getCriteria());
        return this;
    }

    public final ProductSpecificationBuilder with(SearchCriteria criteria) {
        params.add(criteria);
        return this;
    }

}
