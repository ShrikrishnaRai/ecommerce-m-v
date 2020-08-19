package com.shree.ecommerce_m_v.customer.order.invoice.service;

import com.shree.ecommerce_m_v.customer.order.invoice.model.DTO.InvoiceDTO;
import org.springframework.data.domain.Page;

public interface InvoiceService {
    Page<InvoiceDTO> getAllInvoices(int page);

    String saveInvoice(InvoiceDTO invoiceDTO);

    InvoiceDTO getInvoiceById(final Long invoiceId);

    String deleteInvoice(Long id);

    InvoiceDTO updateInvoice(InvoiceDTO invoiceDTO, Long invoiceId);

}
