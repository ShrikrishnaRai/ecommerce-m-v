package com.shree.ecommerce_m_v.customer.order.invoice.service;

import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.customer.order.invoice.model.DTO.InvoiceDTO;
import com.shree.ecommerce_m_v.customer.order.invoice.model.entity.InvoiceEntity;
import com.shree.ecommerce_m_v.customer.order.invoice.repository.InvoiceRepository;
import com.shree.ecommerce_m_v.customer.order.invoice.service.mapper.InvoiceMapper;
import com.shree.ecommerce_m_v.customer.order.order.model.entity.OrderEntity;
import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceIMPL extends InvoiceMapper implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public Page<InvoiceDTO> getAllInvoices(int page) {

        Pageable pageable = PageRequest.of(page,10);
        Page<InvoiceEntity> result = invoiceRepository.findAll(pageable);
        return result.map(invoiceEntity -> toDTO(invoiceEntity));
    }

    @Override
    public String saveInvoice(InvoiceDTO invoiceDTO) {
        return invoiceRepository.save(toEntity(invoiceDTO)).getInvoiceNumber();
    }

    @Override
    public InvoiceDTO getInvoiceById(Long invoiceId) {
        return toDTO(invoiceRepository.getOne(invoiceId));
    }

    @Override
    public String deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
        return ResponseValue.DELETE_SUCCESS;
    }

    @Override
    public InvoiceDTO updateInvoice(InvoiceDTO invoiceDTO, Long invoiceId) {

        InvoiceEntity invoiceEntity=invoiceRepository.getOne(invoiceId);
        if (invoiceDTO.getCustomerMergerDTO() != null) {
           invoiceEntity.setCustomerEntity(CustomerEntity.builder()
                    .customerId(invoiceDTO.getCustomerMergerDTO().getId())
                    .username(invoiceDTO.getCustomerMergerDTO().getUsername())
                    .build());
        }
        if (invoiceDTO.getVendorMergerDTO() != null) {
            invoiceEntity.setVendorEntity(VendorEntity.builder()
                    .vendorId(invoiceDTO.getVendorMergerDTO().getVendorId())
                    .vendorName(invoiceDTO.getVendorMergerDTO().getVendorName())
                    .build());
        }
        if (invoiceDTO.getOrderMergerDTO()!= null) {
            invoiceEntity.setOrderEntity(OrderEntity.builder()
                    .orderId(invoiceDTO.getOrderMergerDTO().getId())
                    .totalAmount(invoiceDTO.getOrderMergerDTO().getTotalAmount())
                    .build());
        }

        invoiceEntity.setInvoiceNumber(invoiceDTO.getInvoiceNumber());
        invoiceEntity.setPdf(invoiceDTO.getPdf());
        invoiceEntity.setStatus(invoiceDTO.getStatus());
        invoiceRepository.saveAndFlush(invoiceEntity);
        return toDTO(invoiceEntity);
    }
}
