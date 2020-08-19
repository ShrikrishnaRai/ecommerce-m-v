package com.shree.ecommerce_m_v.utils.notification.firebase;


public enum NotificationParameter {
    SOUND("default"),
    COLOR("#FFFF00");

    private String value;

    NotificationParameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
