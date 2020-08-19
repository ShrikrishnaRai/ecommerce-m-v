package com.shree.ecommerce_m_v.shared.product.product.filtering.domain;

public enum SearchOperation {

    EQUALITY, GREATER_THAN_OR_EQUAL_TO, LESS_THAN_OR_EQUAL_TO, LIKE, CONTAINS;

    public static final String[] SIMPLE_OPERATION_SET = { ":", ">", "<", "~" };

    public static final String OR_PREDICATE_FLAG = "-";


    public static SearchOperation getSimpleOperation(final char input) {
        switch (input) {
            case ':':
                return EQUALITY;
            case '>':
                return GREATER_THAN_OR_EQUAL_TO;
            case '<':
                return LESS_THAN_OR_EQUAL_TO;
            case '~':
                return LIKE;

            default:
                return null;
        }
    }
}
