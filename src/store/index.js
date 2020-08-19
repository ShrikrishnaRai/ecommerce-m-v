import Vue from 'vue'
import Vuex from 'vuex'
import product from './modules/product'
import brands from './modules/brands'
import category from './modules/category'
import customer from '@/store/modules/customer'
import vendor from '@/store/modules/vendor'
import productBundle from '@/store/modules/productBundle'
import offers from '@/store/modules/offers'
import deliveryBoy from '@/store/modules/deliveryBoy'
import deliveryBoyBankDetails from '@/store/modules/deliveryBoyBankDetails'
import deliveryBoyDocumentDetails from '@/store/modules/deliveryBoyDocumentDetails'
import invoice from '@/store/modules/invoice'
import task from '@/store/modules/task'
import team from '@/store/modules/team'
import merchantService from '@/store/modules/merchantService'
import enquiry from '@/store/modules/enquiry'
import vendorReview from '@/store/modules/vendorReview'
import order from '@/store/modules/order'
import customerWallet from '@/store/modules/customerWallet'
import wishlist from '@/store/modules/wishlist'
import vendorWallet from '@/store/modules/vendorWallet'
import preBooking from '@/store/modules/preBooking'
import subscriber from '@/store/modules/subscriber'
import color from '@/store/modules/color'
import complain from '@/store/modules/complain'
import coupon from '@/store/modules/coupon'
import deliveryAddress from '@/store/modules/deliveryAddress'
import vendorDocument from '@/store/modules/vendorDocument'
import productReview from '@/store/modules/productReview'
import productStock from '@/store/modules/productStock'
import serviceCategory from '@/store/modules/serviceCategory'
import serviceProvider from '@/store/modules/serviceProvider'
import serviceReview from '@/store/modules/serviceReview'
import deliveryBoyWallet from '@/store/modules/deliveryBoyWallet'
import deliveryBoyPayment from '@/store/modules/deliveryBoyPayment'
import deliveryBoyWalletHistory from '@/store/modules/deliveryBoyWalletHistory'
import taskHistory from '@/store/modules/taskHistory'
import orderService from '@/store/modules/orderService'
import cancelOrder from '@/store/modules/cancelOrder'
import size from '@/store/modules/size'
import login from '@/store/modules/login'
import shoppingCart from '@/store/modules/shoppingCart'

Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        product,
        brands,
        category,
        customer,
        vendor,
        productBundle,
        offers,
        deliveryBoy,
        deliveryBoyBankDetails,
        deliveryBoyDocumentDetails,
        invoice,
        task,
        team,
        merchantService,
        enquiry,
        vendorReview,
        order,
        customerWallet,
        wishlist,
        vendorWallet,
        preBooking,
        subscriber,
        color,
        complain,
        coupon,
        deliveryAddress,
        vendorDocument,
        productReview,
        productStock,
        serviceCategory,
        serviceProvider,
        serviceReview,
        deliveryBoyWallet,
        deliveryBoyPayment,
        deliveryBoyWalletHistory,
        taskHistory,
        orderService,
        cancelOrder,
        size,
        login,
        shoppingCart,
    },
})
