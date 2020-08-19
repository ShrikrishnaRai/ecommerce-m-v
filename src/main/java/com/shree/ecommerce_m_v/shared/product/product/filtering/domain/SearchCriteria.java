package com.shree.ecommerce_m_v.shared.product.product.filtering.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {

    private String key;
    private SearchOperation operation;
    private Object value;
    private boolean orPredicate;

    public SearchCriteria(
            String orPredicate, String key, SearchOperation operation, Object value) {
        super();

        this.orPredicate
                = orPredicate != null
                && orPredicate.equals(SearchOperation.OR_PREDICATE_FLAG);

        this.key = key;
        this.operation = operation;
        this.value = value;
    }
}
