package com.shree.ecommerce_m_v.customer.order.order.service.mapper;

import com.shree.ecommerce_m_v.customer.customer.model.DTO.CustomerMergerDTO;
import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.customer.deliveryAddress.model.DTO.DeliveryAddressMergerDTO;
import com.shree.ecommerce_m_v.customer.deliveryAddress.model.entity.DeliveryAddressEntity;
import com.shree.ecommerce_m_v.customer.order.deliveryRate.model.DTO.DeliveryRateMergerDTO;
import com.shree.ecommerce_m_v.customer.order.deliveryRate.model.entity.DeliveryRateEntity;
import com.shree.ecommerce_m_v.customer.order.invoice.model.DTO.InvoiceMergerDTO;
import com.shree.ecommerce_m_v.customer.order.invoice.model.entity.InvoiceEntity;
import com.shree.ecommerce_m_v.customer.order.order.model.DTO.OrderDetailDTO;
import com.shree.ecommerce_m_v.customer.order.order.model.DTO.OrderMergerDTO;
import com.shree.ecommerce_m_v.customer.order.order.model.DTO.OrderResponseDTO.OrderRequestDTO;
import com.shree.ecommerce_m_v.customer.order.order.model.DTO.OrderResponseDTO.OrderResponseDTO;
import com.shree.ecommerce_m_v.customer.order.order.model.entity.OrderDetailEntity;
import com.shree.ecommerce_m_v.customer.order.order.model.entity.OrderEntity;
import com.shree.ecommerce_m_v.shared.product.color.service.dto.ColorMergerDTO;
import com.shree.ecommerce_m_v.shared.product.color.model.entity.ColorEntity;
import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import com.shree.ecommerce_m_v.shared.product.size.service.dto.SizeMergerDTO;
import com.shree.ecommerce_m_v.shared.product.size.model.entity.SizeEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public abstract class OrderMapper {

    protected OrderEntity toEntity(OrderRequestDTO orderRequestDTO) {
        DeliveryAddressEntity deliveryAddressEntity = new DeliveryAddressEntity();
        if (orderRequestDTO.getDeliveryAddressMergerDTO() != null) {
            deliveryAddressEntity = DeliveryAddressEntity.builder()
                    .deliveryAddressId(orderRequestDTO.getDeliveryAddressMergerDTO().getDeliveryAddressId())
                    .district(orderRequestDTO.getDeliveryAddressMergerDTO().getDistrict())
                    .build();
        }

        DeliveryRateEntity deliveryRateEntity = new DeliveryRateEntity();
        if (orderRequestDTO.getDeliveryRateMergerDTO() != null) {
            deliveryRateEntity = DeliveryRateEntity.builder()
                    .deliveryRateId(orderRequestDTO.getDeliveryRateMergerDTO().getDeliveryRateId())
                    .city(orderRequestDTO.getDeliveryRateMergerDTO().getCity())
                    .build();
        }

        InvoiceEntity invoiceEntity = new InvoiceEntity();
        if (orderRequestDTO.getInvoiceMergerDTO() != null) {
            invoiceEntity = InvoiceEntity.builder()
                    .invoiceId(orderRequestDTO.getInvoiceMergerDTO().getInvoiceId())
                    .invoiceNumber(orderRequestDTO.getInvoiceMergerDTO().getInvoiceNumber())
                    .build();
        }

        CustomerEntity customerEntity = new CustomerEntity();
        if (orderRequestDTO.getCustomerMergerDTO() != null) {
            customerEntity = CustomerEntity.builder()
                    .customerId(orderRequestDTO.getCustomerMergerDTO().getId())
                    .username(orderRequestDTO.getCustomerMergerDTO().getUsername())
                    .build();
        }

        OrderEntity orderEntity = OrderEntity.builder()
                .deliveryInstruction(orderRequestDTO.getDeliveryInstruction())
                .couponAmount(orderRequestDTO.getCouponAmount())
                .discountAmount(orderRequestDTO.getDiscountAmount())
                .discountPercentage(orderRequestDTO.getDiscountPercentage())
                .totalAmount(orderRequestDTO.getTotalAmount())
                .subTotal(orderRequestDTO.getSubTotal())
                .taxType(orderRequestDTO.getTaxType())
                .deliveryDate(orderRequestDTO.getDeliveryDate())
                .taxValue(orderRequestDTO.getTaxValue())
                .extraCharge(orderRequestDTO.getExtraCharge())
                .orderStatus(orderRequestDTO.getOrderStatus())
                .deliveryAddressEntity(orderRequestDTO.getDeliveryAddressMergerDTO() != null ? deliveryAddressEntity : null)
                .deliveryRateEntity(orderRequestDTO.getDeliveryRateMergerDTO() != null ? deliveryRateEntity : null)
                .invoiceEntity(orderRequestDTO.getInvoiceMergerDTO() != null ? invoiceEntity : null)
                .customerEntity(orderRequestDTO.getCustomerMergerDTO() != null ? customerEntity : null)
                .build();


        List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();
        if (orderRequestDTO.getOrderDetailDTO().size() != 0) {
            for (OrderDetailDTO orderDetailDTO : orderRequestDTO.getOrderDetailDTO()) {
                orderDetailEntities.add(OrderDetailEntity.builder()
                        .orderDetailId(orderDetailDTO.getOrderDetailId())
                        .productEntity(ProductEntity.builder()
                                .productId(orderDetailDTO.getProductMergerDTO().getProductId())
                                .productName(orderDetailDTO.getProductMergerDTO().getProductName())
                                .build())
                        .sizeEntity(orderDetailDTO.getSizeMergerDTO() != null ? (SizeEntity.builder()
                                .sizeId(orderDetailDTO.getSizeMergerDTO().getSizeId())
                                .size(orderDetailDTO.getSizeMergerDTO().getSize())
                                .build()) : null)
                        .colorEntity(orderDetailDTO.getColorMergerDTO() != null ? (ColorEntity.builder()
                                .colorId(orderDetailDTO.getColorMergerDTO().getColorId())
                                .colorName(orderDetailDTO.getColorMergerDTO().getColorName())
                                .colorCode(orderDetailDTO.getColorMergerDTO().getColorCode())
                                .build()) : null)
                        .orderEntity(orderEntity)
                        .discountValue(orderDetailDTO.getDiscountValue())
                        .totalQuantity(orderDetailDTO.getTotalQuantity())
                        .salePrice(orderDetailDTO.getSalePrice())
                        .subTotal(orderDetailDTO.getSubTotal())
                        .build());
            }
        }

        orderEntity.setOrderDetailEntity(orderRequestDTO.getOrderDetailDTO() != null ? orderDetailEntities : null);
        return orderEntity;
    }

    protected List<OrderResponseDTO> toDTOList(List<OrderEntity> orderEntityList) {
        return orderEntityList
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    protected OrderResponseDTO toDTO(OrderEntity orderEntity) {
        DeliveryAddressMergerDTO deliveryAddressMergerDTO = new DeliveryAddressMergerDTO();
        if (orderEntity.getDeliveryAddressEntity() != null) {
            deliveryAddressMergerDTO = DeliveryAddressMergerDTO.builder()
                    .deliveryAddressId(orderEntity.getDeliveryAddressEntity().getDeliveryAddressId())
                    .district(orderEntity.getDeliveryAddressEntity().getDistrict())
                    .build();
        }

        DeliveryRateMergerDTO deliveryRateMergerDTO = new DeliveryRateMergerDTO();
        if (orderEntity.getDeliveryRateEntity() != null) {
            deliveryRateMergerDTO = DeliveryRateMergerDTO.builder()
                    .deliveryRateId(orderEntity.getDeliveryRateEntity().getDeliveryRateId())
                    .city(orderEntity.getDeliveryRateEntity().getCity())
                    .build();
        }

        InvoiceMergerDTO invoiceMergerDTO = new InvoiceMergerDTO();
        if (orderEntity.getInvoiceEntity() != null) {
            invoiceMergerDTO = InvoiceMergerDTO.builder()
                    .invoiceId(orderEntity.getInvoiceEntity().getInvoiceId())
                    .invoiceNumber(orderEntity.getInvoiceEntity().getInvoiceNumber())
                    .build();
        }

        CustomerMergerDTO customerMergerDTO = new CustomerMergerDTO();
        if (orderEntity.getCustomerEntity() != null) {
            customerMergerDTO = CustomerMergerDTO.builder()
                    .id(orderEntity.getCustomerEntity().getCustomerId())
                    .username(orderEntity.getCustomerEntity().getUsername())
                    .build();
        }

        List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
        if (orderEntity.getOrderDetailEntity().size() != 0) {
            for (OrderDetailEntity orderDetailEntity : orderEntity.getOrderDetailEntity()) {
                orderDetailDTOList.add(OrderDetailDTO.builder()
                        .orderDetailId(orderDetailEntity.getOrderDetailId())
                        .productMergerDTO(ProductMergerDTO.builder()
                                .productId(orderDetailEntity.getProductEntity().getProductId())
                                .productName(orderDetailEntity.getProductEntity().getProductName())
                                .build())
                        .sizeMergerDTO(SizeMergerDTO.builder()
                                .sizeId(orderDetailEntity.getSizeEntity().getSizeId())
                                .size(orderDetailEntity.getSizeEntity().getSize())
                                .build())
                        .colorMergerDTO(ColorMergerDTO.builder()
                                .colorId(orderDetailEntity.getColorEntity().getColorId())
                                .colorName(orderDetailEntity.getColorEntity().getColorName())
                                .colorCode(orderDetailEntity.getColorEntity().getColorCode())
                                .build())
                        .orderMergerDTO(OrderMergerDTO.builder()
                                .id(orderDetailEntity.getOrderEntity().getOrderId())
                                .totalAmount(orderDetailEntity.getOrderEntity().getTotalAmount())
                                .build())
                        .discountValue(orderDetailEntity.getDiscountValue())
                        .totalQuantity(orderDetailEntity.getTotalQuantity())
                        .salePrice(orderDetailEntity.getSalePrice())
                        .subTotal(orderDetailEntity.getSubTotal())
                        .build());
            }
        }

        return OrderResponseDTO.builder()
                .orderId(orderEntity.getOrderId())
                .deliveryInstruction(orderEntity.getDeliveryInstruction())
                .couponAmount(orderEntity.getCouponAmount())
                .discountAmount(orderEntity.getDiscountAmount())
                .discountPercentage(orderEntity.getDiscountPercentage())
                .totalAmount(orderEntity.getTotalAmount())
                .subTotal(orderEntity.getSubTotal())
                .taxType(orderEntity.getTaxType())
                .deliveryDate(orderEntity.getDeliveryDate())
                .taxValue(orderEntity.getTaxValue())
                .extraCharge(orderEntity.getExtraCharge())
                .orderStatus(orderEntity.getOrderStatus())
                .deliveryAddressMergerDTO(deliveryAddressMergerDTO)
                .deliveryRateMergerDTO(deliveryRateMergerDTO)
                .invoiceMergerDTO(invoiceMergerDTO)
                .orderDetailDTOList(orderDetailDTOList)
                .customerMergerDTO(customerMergerDTO)
                .build();
    }
}
