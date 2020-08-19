package com.shree.ecommerce_m_v.customer.order.invoice.service.mapper;

import com.shree.ecommerce_m_v.customer.customer.model.DTO.CustomerMergerDTO;
import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.customer.order.invoice.model.DTO.InvoiceDTO;
import com.shree.ecommerce_m_v.customer.order.invoice.model.entity.InvoiceEntity;
import com.shree.ecommerce_m_v.customer.order.order.model.DTO.OrderMergerDTO;
import com.shree.ecommerce_m_v.customer.order.order.model.entity.OrderEntity;
import com.shree.ecommerce_m_v.vendor.vendor.service.dto.VendorMergerDTO;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;

import java.util.List;
import java.util.stream.Collectors;

public abstract class InvoiceMapper {

    protected List<InvoiceDTO> toDtoList(List<InvoiceEntity> invoiceEntityList) {
        return invoiceEntityList.stream()
                .map(invoiceEntity -> toDTO(invoiceEntity))
                .collect(Collectors.toList());
    }

    protected InvoiceEntity toEntity(InvoiceDTO invoiceDTO) {
        CustomerEntity customerEntity= new CustomerEntity();
        if (invoiceDTO.getCustomerMergerDTO() != null) {
            customerEntity = CustomerEntity.builder()
                    .customerId(invoiceDTO.getCustomerMergerDTO().getId())
                    .username(invoiceDTO.getCustomerMergerDTO().getUsername())
                    .build();
        }
        VendorEntity vendorEntity = new VendorEntity();
        if (invoiceDTO.getVendorMergerDTO() != null) {
          vendorEntity = VendorEntity.builder()
                  .vendorId(invoiceDTO.getVendorMergerDTO().getVendorId())
                  .vendorName(invoiceDTO.getVendorMergerDTO().getVendorName())
                  .build();
        }
        OrderEntity orderEntity = new OrderEntity();
        if (invoiceDTO.getOrderMergerDTO()!= null) {
            orderEntity=OrderEntity.builder()
                    .orderId(invoiceDTO.getOrderMergerDTO().getId())
                    .totalAmount(invoiceDTO.getOrderMergerDTO().getTotalAmount())
                    .build();
        }
        return InvoiceEntity.builder()
                .invoiceId(invoiceDTO.getInvoiceId())
                .invoiceNumber(invoiceDTO.getInvoiceNumber())
                .pdf(invoiceDTO.getPdf())
                .status(invoiceDTO.getStatus())
                .orderEntity(invoiceDTO.getOrderMergerDTO()!=null? orderEntity: null)
                .customerEntity(invoiceDTO.getCustomerMergerDTO()!=null? customerEntity: null)
                .vendorEntity(invoiceDTO.getVendorMergerDTO()!=null? vendorEntity: null)
                .build();
    }

    protected InvoiceDTO toDTO(InvoiceEntity invoiceEntity) {
        CustomerMergerDTO customerMergerDTO= new CustomerMergerDTO();
        if (invoiceEntity.getCustomerEntity() != null) {
            customerMergerDTO = CustomerMergerDTO.builder()
                    .id(invoiceEntity.getCustomerEntity().getCustomerId())
                    .username(invoiceEntity.getCustomerEntity().getUsername())
                    .build();
        }
        VendorMergerDTO vendorMergerDTO = new VendorMergerDTO();
        if (invoiceEntity.getVendorEntity() != null) {
            vendorMergerDTO = VendorMergerDTO.builder()
                    .vendorId(invoiceEntity.getVendorEntity().getVendorId())
                    .vendorName(invoiceEntity.getVendorEntity().getVendorName())
                    .build();
        }
        OrderMergerDTO orderMergerDTO = new OrderMergerDTO();
        if (invoiceEntity.getOrderEntity()!= null) {
            orderMergerDTO=OrderMergerDTO.builder()
                    .id(invoiceEntity.getOrderEntity().getOrderId())
                    .totalAmount(invoiceEntity.getOrderEntity().getTotalAmount())
                    .build();
        }
        return InvoiceDTO.builder()
                .invoiceId(invoiceEntity.getInvoiceId())
                .invoiceNumber(invoiceEntity.getInvoiceNumber())
                .pdf(invoiceEntity.getPdf())
                .status(invoiceEntity.getStatus())
                .customerMergerDTO(customerMergerDTO)
                .vendorMergerDTO(vendorMergerDTO)
                .orderMergerDTO(orderMergerDTO)
                .build();
    }
}
