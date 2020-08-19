package com.shree.ecommerce_m_v.customer.order.order.service;

import com.shree.ecommerce_m_v.customer.deliveryAddress.model.entity.DeliveryAddressEntity;
import com.shree.ecommerce_m_v.customer.order.deliveryRate.model.entity.DeliveryRateEntity;
import com.shree.ecommerce_m_v.customer.order.invoice.model.entity.InvoiceEntity;
import com.shree.ecommerce_m_v.customer.order.order.model.DTO.OrderDetailDTO;
import com.shree.ecommerce_m_v.customer.order.order.model.DTO.OrderResponseDTO.OrderRequestDTO;
import com.shree.ecommerce_m_v.customer.order.order.model.DTO.OrderResponseDTO.OrderResponseDTO;
import com.shree.ecommerce_m_v.customer.order.order.model.entity.OrderDetailEntity;
import com.shree.ecommerce_m_v.customer.order.order.model.entity.OrderEntity;
import com.shree.ecommerce_m_v.customer.order.order.repository.OrderRepository;
import com.shree.ecommerce_m_v.customer.order.order.service.mapper.OrderMapper;
import com.shree.ecommerce_m_v.shared.product.color.model.entity.ColorEntity;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import com.shree.ecommerce_m_v.shared.product.size.model.entity.SizeEntity;
import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl extends OrderMapper implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Page<OrderResponseDTO> getOrders(int page, String sortBy, String orderBy) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by(Sort.Direction.valueOf(orderBy), sortBy));
        Page<OrderEntity> orders = orderRepository.findAll(pageable);
        return orders.map(orderEntity -> toDTO(orderEntity));
    }

    @Override
    public String saveOrder(OrderRequestDTO orderRequestDTO) {
        orderRepository.save(toEntity(orderRequestDTO));
        return ResponseValue.SAVE_SUCCESS;
    }

    @Override
    public OrderResponseDTO getOrderById(Long orderId) {
        return toDTO(orderRepository.getOne(orderId));
    }

    @Override
    public OrderResponseDTO updateOrder(Long orderId, OrderRequestDTO orderRequestDTO) {

        OrderEntity orderEntity = orderRepository.getOne(orderId);

        orderEntity.setDeliveryInstruction(orderRequestDTO.getDeliveryInstruction());
        orderEntity.setCouponAmount(orderRequestDTO.getCouponAmount());
        orderEntity.setDiscountAmount(orderRequestDTO.getDiscountAmount());
        orderEntity.setDiscountPercentage(orderRequestDTO.getDiscountPercentage());
        orderEntity.setTotalAmount(orderRequestDTO.getTotalAmount());
        orderEntity.setSubTotal(orderRequestDTO.getSubTotal());
        orderEntity.setTaxType(orderRequestDTO.getTaxType());
        orderEntity.setDeliveryDate(orderRequestDTO.getDeliveryDate());
        orderEntity.setTaxValue(orderRequestDTO.getTaxValue());
        orderEntity.setExtraCharge(orderRequestDTO.getExtraCharge());
        orderEntity.setOrderStatus(orderRequestDTO.getOrderStatus());
        if (orderRequestDTO.getDeliveryAddressMergerDTO() != null) {
            orderEntity.setDeliveryAddressEntity(DeliveryAddressEntity.builder()
                    .deliveryAddressId(orderRequestDTO.getDeliveryAddressMergerDTO().getDeliveryAddressId())
                    .district(orderRequestDTO.getDeliveryAddressMergerDTO().getDistrict())
                    .build());
        }

        if (orderRequestDTO.getDeliveryRateMergerDTO() != null) {
            orderEntity.setDeliveryRateEntity(DeliveryRateEntity.builder()
                    .deliveryRateId(orderRequestDTO.getDeliveryRateMergerDTO().getDeliveryRateId())
                    .city(orderRequestDTO.getDeliveryRateMergerDTO().getCity())
                    .build());
        }

        if (orderRequestDTO.getInvoiceMergerDTO() != null) {
            orderEntity.setInvoiceEntity(InvoiceEntity.builder()
                    .invoiceId(orderRequestDTO.getInvoiceMergerDTO().getInvoiceId())
                    .invoiceNumber(orderRequestDTO.getInvoiceMergerDTO().getInvoiceNumber())
                    .build());
        }

        if (orderRequestDTO.getOrderDetailDTO().size() != 0) {
            for (OrderDetailDTO orderDetailDTO : orderRequestDTO.getOrderDetailDTO()) {
                List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();

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
                orderEntity.setOrderDetailEntity(orderDetailEntities);
            }
        }

        orderRepository.save(orderEntity);
        return toDTO(orderEntity);
    }

    @Override
    public List<OrderResponseDTO> getOrderByCustomerId(Long customerId) {
        return toDTOList(orderRepository.getOrdersByCustomerId(customerId));
    }
}
