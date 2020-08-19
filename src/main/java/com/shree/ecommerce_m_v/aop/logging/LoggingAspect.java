package com.shree.ecommerce_m_v.aop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * Aspect for logging execution of service and repository Spring components.
 * <p>
 * By default, it only runs with the "dev" profile.
 */
@Aspect
@Component
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * PointCut that matches all repositories, services and Web REST endpoints.
     */
    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            "|| within(@org.springframework.stereotype.Service *)" +
            "|| within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {

    }

    @Pointcut("within(com.shree.ecommerce_m_v..*)" +
            "|| within(com.shree.ecommerce_m_v.customer.complain.service.*)" +
            "|| within(com.shree.ecommerce_m_v.customer.customer.service.*)" +
            "|| within(com.shree.ecommerce_m_v.customer.customerWallet.service.*)" +
            "|| within(com.shree.ecommerce_m_v.customer.deliveryAddress.service.*)" +
            "|| within(com.shree.ecommerce_m_v.customer.enquiry.service.*)" +
            "|| within(com.shree.ecommerce_m_v.customer.order.orderService.service.*)" +
            "|| within(com.shree.ecommerce_m_v.customer.order.cancelOrder.service.*)" +
            "|| within(com.shree.ecommerce_m_v.customer.order.invoice.service.*)" +
            "|| within(com.shree.ecommerce_m_v.customer.order.orderStatus.service.*)" +
            "|| within(com.shree.ecommerce_m_v.customer.preBooking.service.*)" +
            "|| within(com.shree.ecommerce_m_v.customer.shoppingCart.service.*)" +
            "|| within(com.shree.ecommerce_m_v.customer.subscriber.service.*)" +
            "|| within(com.shree.ecommerce_m_v.customer.wishlist.service.*)" +
            "|| within(com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.service.*)" +
            "|| within(com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyBankDetail.service.*)" +
            "|| within(com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.service.*)" +
            "|| within(com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.service.*)" +
            "|| within(com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.service.*)" +
            "|| within(com.shree.ecommerce_m_v.deliveryBoy.task.service.*)" +
            "|| within(com.shree.ecommerce_m_v.deliveryBoy.taskHistory.service.*)" +
            "|| within(com.shree.ecommerce_m_v.shared.product.brand.service.*)" +
            "|| within(com.shree.ecommerce_m_v.shared.product.category.service.*)" +
            "|| within(com.shree.ecommerce_m_v.shared.product.color.service.*)" +
            "|| within(com.shree.ecommerce_m_v.shared.product.offer.service.*)" +
            "|| within(com.shree.ecommerce_m_v.shared.product.product.service.*)" +
            "|| within(com.shree.ecommerce_m_v.shared.product.productBundle.service.*)" +
            "|| within(com.shree.ecommerce_m_v.shared.product.productImage.service.*)" +
            "|| within(com.shree.ecommerce_m_v.shared.product.productReview.service.*)" +
            "|| within(com.shree.ecommerce_m_v.shared.product.productStock.service.*)" +
            "|| within(com.shree.ecommerce_m_v.shared.product.size.service.*)" +
            "|| within(com.shree.ecommerce_m_v.vendor.coupon.service.*)" +
            "|| within(com.shree.ecommerce_m_v.vendor.service.service.*)" +
            "|| within(com.shree.ecommerce_m_v.vendor.vendor.service.*)" +
            "|| within(com.shree.ecommerce_m_v.vendor.vendorDocument.service.*)" +
            "|| within(com.shree.ecommerce_m_v.vendor.vendorReview.service.*)" +
            "|| within(com.shree.ecommerce_m_v.vendor.vendorWallet.service.*)" +
            "|| within(com.shree.ecommerce_m_v.customer.complain.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.customer.customer.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.customer.customerWallet.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.customer.deliveryAddress.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.customer.enquiry.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.customer.order.orderService.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.customer.order.cancelOrder.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.customer.order.invoice.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.customer.order.orderStatus.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.customer.preBooking.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.customer.shoppingCart.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.customer.subscriber.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.customer.wishlist.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyBankDetail.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.deliveryBoy.task.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.deliveryBoy.taskHistory.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.shared.product.brand.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.shared.product.category.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.shared.product.color.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.shared.product.offer.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.shared.product.product.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.shared.product.productBundle.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.shared.product.productImage.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.shared.product.productReview.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.shared.product.productStock.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.shared.product.size.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.vendor.coupon.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.vendor.vendor.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.vendor.vendor.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.vendor.vendorDocument.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.vendor.vendorReview.repository.*)" +
            "|| within(com.shree.ecommerce_m_v.vendor.vendorWallet.repository.*)")
    public void applicationPackagePointcut() {
    }

    /**
     * Advice that logs methods throwing exceptions
     *
     * @Param joinPoint join point for advice
     * @Param e exception
     */
    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{} () with cause ={} more ={}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL", e);
    }

    /**
     * Advice that logs when a method is entered and exited.
     *
     * @param joinPoint join point for advice
     * @return result
     * @throws Throwable throw IllegalArgumentException
     */
    @Around("applicationPackagePointcut() && springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("Enter:{}.{}() with arguments[s]={}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs())
            );
        }
        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
                log.debug("Exit: {}.{}() with result={}", joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(), result
                );
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument:{} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName()
            );
            throw e;
        }
    }
}
