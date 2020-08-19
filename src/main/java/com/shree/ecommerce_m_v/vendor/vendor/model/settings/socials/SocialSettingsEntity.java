package com.shree.ecommerce_m_v.vendor.vendor.model.settings.socials;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class SocialSettingsEntity {
    private String facebookUrl;
    private String websiteUrl;
    private String phoneNumber;
    private String socialEmails;

}
