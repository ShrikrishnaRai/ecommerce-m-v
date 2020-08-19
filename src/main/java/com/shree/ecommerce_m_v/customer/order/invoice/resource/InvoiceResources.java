package com.shree.ecommerce_m_v.customer.order.invoice.resource;

import com.shree.ecommerce_m_v.customer.order.invoice.model.DTO.InvoiceDTO;
import com.shree.ecommerce_m_v.customer.order.invoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoice")
public class InvoiceResources {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<InvoiceDTO>>> getAllInvoiceList(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @RequestParam(defaultValue = "0",value = "0",required = false) int page,
            PagedResourcesAssembler<InvoiceDTO> assembler) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(invoiceService.getAllInvoices(page)));
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<InvoiceDTO> getInvoiceById(
            @RequestHeader(value="Authorization", required = false) String Authorization,
            @PathVariable("invoiceId") final Long invoiceId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(invoiceService.getInvoiceById(invoiceId));
    }

    @PostMapping
    public ResponseEntity<String> saveInvoice(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                              @RequestBody InvoiceDTO invoiceDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(invoiceService.saveInvoice(invoiceDTO));
    }

    @DeleteMapping("/{invoiceId}")
    public ResponseEntity<String> deleteInvoice(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                @PathVariable Long invoiceId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(invoiceService.deleteInvoice(invoiceId));
    }

    @PutMapping("/{invoiceId}")
    public ResponseEntity<InvoiceDTO> updateInvoice(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                    @PathVariable Long invoiceId, @RequestBody InvoiceDTO invoiceDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(invoiceService.updateInvoice(invoiceDTO, invoiceId));

    }
}
