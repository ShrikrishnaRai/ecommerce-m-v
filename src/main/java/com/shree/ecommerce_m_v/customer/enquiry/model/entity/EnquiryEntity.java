package com.shree.ecommerce_m_v.customer.enquiry.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "enquiry")
public class EnquiryEntity extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enquiry_id")
    private Long enquiryId;
    private String subject;
    private LocalDate replyDate;
    @JsonIgnore
    private LocalTime replyTimeStamp = LocalTime.now();
    @Email(message = "Please, provide a valid Email")
    @Column(name = "email")
    private String senderEmail;
}
