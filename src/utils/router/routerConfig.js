import store from '@/store/index'

// import homePage from '@/customerLayout/homePage'
import notFound from '@/views/error/NotFound'
import customerDashboardLayoutIndex from '@/views/customerDashboardLayout/customerDashboardLayoutIndex'
import adminLayout from '@/views/adminLayout/adminLayoutHomeIndex'
import brand from '@/components/brands/brandIndex'
import wishlist from '@/components/wishList/wishlistIndex'
import product from '@/components/products/productsIndex'
import category from '@/components/category/categoryIndex'
import customer from '@/components/customer/customerIndex'
import vendor from '@/components/vendor/vendorIndex'
import productBundle from '@/components/productsBundle/productsBundleIndex'
import offers from '@/components/offers/offersIndex'
import deliveryBoyLayout from '@/views/deliveryBoyIndex/deliveryBoyIndex'
import deliveryBoyBankDetails from '@/components/deliveryBoyBankDetails/deliveryBoyBankDetailsIndex'
import deliveryBoyDocumentDetails from '@/components/deliveryBoyDocumentDetails/deliveryBoyDocumentDetailsIndex'
import invoice from '@/components/invoice/invoiceIndex'
import task from '@/components/task/taskIndex'
import team from '@/components/team/teamIndex'
import service from '@/components/service/serviceIndex'
import enquiry from '@/components/enquiry/enquiryIndex'
import vendorReview from '@/components/vendorReview/vendorReviewIndex'
import order from '@/components/order/orderIndex'
import customerWallet from '@/components/customerWallet/customerWalletIndex'
import vendorWallet from '@/components/vendorWallet/vendorWalletIndex'
import preBooking from '@/components/preBooking/preBookingIndex'
import Shop from '@/views/customerLayout/Shop'
import Blog from '@/views/customerLayout/Blog'
import Cart from '@/views/customerLayout/Cart'
import Layout from '@/views/customerLayout/customerLayoutIndex'
import Product from '@/views/customerLayout/Product'
import Home from '@/views/customerLayout/Home'
import color from '@/components/color/colorIndex'
import complain from '@/components/complain/complainIndex'
import coupon from '@/components/coupon/couponIndex'
import deliveryAddress from '@/components/deliveryAddress/deliveryAddressIndex'
import vendorDocument from '@/components/vendorDocument/vendorDocumentIndex'
import productStock from '@/components/productStock/productStockIndex'
import serviceCategory from '@/components/serviceCategory/serviceCategoryIndex'
import serviceProvider from '@/components/serviceProvider/serviceProviderIndex'
import serviceReview from '@/components/serviceReview/serviceReviewIndex'
import deliveryBoyWallet from '@/components/deliveryBoyWallet/deliveryBoyWalletIndex'
import deliveryBoyPayment from '@/components/deliveryBoyPayment/deliveryBoyPaymentIndex'
import deliveryBoyWalletHistory from '@/components/deliveryBoyWalletHistory/deliveryBoyWalletHistoryIndex'
import taskHistory from '@/components/taskHistory/taskHistoryIndex'
import orderService from '@/components/orderService/orderServiceIndex'
import adminIndex from '@/components/adminIndex/adminIndex'
import superAdminLayout from '@/views/superAdminLayout/superAdminLayoutIndex'
import superAdminIndex from '@/components/superAdminIndex/superAdminIndex'
import Login from '@/views/login/Login'
import signUp from '@/views/signUp/SignUp'
import customerDashboardIndex from '@/components/customerDashboardIndex'
import cancelOrder from '@/components/cancelOrder/cancelOrderIndex'
import size from '@/components/size/sizeindex'
import shoppingCartIndex from '@/components/shoppingCart/shoppingCartIndex'
import userProfile from '@/views/customerProfile/customerProfile'
import GoogleMap from '@/components/googleMaps/GoogleMap'
import adminProfile from '@/views/adminProfile/adminProfile'

export function getAuthState () {
    return store.state.login.status
}

export const sharedRoute = [
    {
        path: '/login',
        name: Login,
        component: Login,
    }, {
        path: '/signUp',
        name: signUp,
        component: signUp,
    },
]

export const publicRoute = [
    { path: '*', component: notFound },
    {
        path: '/bazzar',
        component: Layout,
        children: [
            {
                path: '/',
                name: 'Home',
                component: Home,
            }, {
            path: '/blog',
            name: 'blog',
            component: Blog,
            }, {
            path: '/cart',
            name: 'cart',
            component: Cart,
            }, {
            path: '/layout',
            name: 'layout',
            component: Layout,
            }, {
            path: '/product/:id',
            name: 'product',
            component: Product,
                props: true,
            }, {
            path: '/shop/:id',
            name: 'shop',
            component: Shop,
            props: true,
            },
    ],
    },
]

export const protectedRouteSuperAdmin = [
    {
        path: '/superAdminLayout/',
        name: 'superAdminLayout',
        component: superAdminLayout,
        beforeEnter: (to, from, next) => {
            if (store.state.login.status === 'super_admin') {
                next()
            } else {
                next('/login')
            }
        },
        redirect: '/superAdminLayout/home',
        children: [
         {
                path: '/superAdminLayout/vendor',
                name: 'vendor',
                component: vendor,
            }, {
                path: '/superAdminLayout/offers',
                name: 'offers',
                component: offers,
            }, {
                path: '/superAdminLayout/category',
                name: 'category',
                component: category,
            }, {
                path: '/superAdminLayout/customer',
                name: 'customer',
                component: customer,
            }, {
                path: '/superAdminLayout/deliveryBoyIndex',
                name: 'deliveryBoyLayout',
                component: deliveryBoyLayout,
            },
            {
                path: '/superAdminLayout/deliveryBoyBankDetails',
                name: 'deliveryBoyBankDetails',
                component: deliveryBoyBankDetails,
            }, {
                path: '/superAdminLayout/deliveryBoyDocumentDetails',
                name: 'deliveryBoyDocumentDetails',
                component: deliveryBoyDocumentDetails,
            }, {
                path: '/superAdminLayout/invoice',
                name: 'invoice',
                component: invoice,
            }, {
                path: '/superAdminLayout/task',
                name: 'task',
                component: task,
            }, {
                path: '/superAdminLayout/team',
                name: 'team',
                component: team,
            }, {
                path: '/superAdminLayout/service',
                name: 'service',
                component: service,
            }, {
                path: '/superAdminLayout/enquiry',
                name: 'enquiry',
                component: enquiry,
            }, {
                path: '/superAdminLayout/vendorReview',
                name: 'vendorReview',
                component: vendorReview,
            },
            {
                path: '/superAdminLayout/customerWallet',
                name: 'customerWallet',
                component: customerWallet,
            }, {
                path: '/superAdminLayout/vendorWallet',
                name: 'vendorWallet',
                component: vendorWallet,
            }, {
                path: '/superAdminLayout/preBooking',
                name: 'preBooking',
                component: preBooking,
            }, {
                path: '/superAdminLayout/complain',
                name: 'complain',
                component: complain,
            }, {
                path: '/superAdminLayout/coupon',
                name: 'coupon',
                component: coupon,
            }, {
                path: '/superAdminLayout/deliveryAddress',
                name: 'deliveryAddress',
                component: deliveryAddress,
            }, {
                path: '/superAdminLayout/vendorDocument',
                name: 'vendorDocument',
                component: vendorDocument,
            }, {
                path: '/superAdminLayout/serviceCategory',
                name: 'serviceCategory',
                component: serviceCategory,
            }, {
                path: '/superAdminLayout/serviceProvider',
                name: 'serviceProvider',
                component: serviceProvider,
            }, {
                path: '/superAdminLayout/serviceReview',
                name: 'serviceReview',
                component: serviceReview,
            }, {
                path: '/superAdminLayout/deliveryBoyWallet',
                name: 'deliveryBoyWallet',
                component: deliveryBoyWallet,
            }, {
                path: '/superAdminLayout/deliveryBoyPayment',
                name: 'deliveryBoyPayment',
                component: deliveryBoyPayment,
            }, {
                path: '/superAdminLayout/deliveryBoyWalletHistory',
                name: 'deliveryBoyWalletHistory',
                component: deliveryBoyWalletHistory,
            }, {
                path: '/superAdminLayout/taskHistory',
                name: 'taskHistory',
                component: taskHistory,
            },
            {
                path: '/superAdminLayout/orderService',
                name: 'orderService',
                component: orderService,
            }, {
                path: '/superAdminLayout/home',
                name: 'superAdminIndex',
                component: superAdminIndex,
            },
        ],

    },
]

export const protectedRouteAdmin = [
    {
        path: '/adminLayout/',
        name: 'Admin',
        component: adminLayout,
        beforeEnter: (to, from, next) => {
            if (localStorage.getItem('user-role') === 'admin') {
                next()
            } else {
                alert('Not Verified Admin redirecting to Login...')
                next('/login')
            }
        },
        redirect: 'adminLayout/home',
        children: [
            {
                path: '/adminLayout/brand',
                name: 'brand',
                component: brand,
            }, {
            path: '/adminLayout/wishlist',
                name: 'wishlist',
                component: wishlist,
            }, {
            path: '/adminLayout/product',
                name: 'product',
                component: product,
            }, {
            path: '/adminLayout/productBundle',
                name: 'productBundle',
                component: productBundle,
            }, {
            path: '/adminLayout/offers',
                name: 'offers',
                component: offers,
            }, {
            path: '/adminLayout/invoice',
                name: 'invoice',
                component: invoice,
            }, {
            path: '/adminLayout/enquiry',
                name: 'enquiry',
                component: enquiry,
            }, {
                path: '/adminLayout/order',
                name: 'order',
                component: order,
            }, {
            path: '/adminLayout/vendorWallet',
                name: 'vendorWallet',
                component: vendorWallet,
            }, {
            path: '/adminLayout/preBooking',
                name: 'preBooking',
                component: preBooking,
            }, {
                path: '/adminLayout/color',
                name: 'color',
                component: color,
            }, {
                path: '/adminLayout/complain',
                name: 'complain',
                component: complain,
            }, {
                path: '/adminLayout/coupon',
                name: 'coupon',
                component: coupon,
            }, {
                path: '/adminLayout/productStock',
                name: 'productStock',
                component: productStock,
            }, {
                path: '/adminLayout/serviceCategory',
                name: 'serviceCategory',
                component: serviceCategory,
            }, {
                path: '/adminLayout/serviceReview',
                name: 'serviceReview',
                component: serviceReview,
            }, {
                path: '/adminLayout/home',
                name: 'adminIndex',
                component: adminIndex,
            }, {
                path: '/adminLayout/cancelOrder',
                name: 'cancelOrder',
                component: cancelOrder,
            }, {
                path: '/adminLayout/size',
                name: 'size',
                component: size,
            }, {
                path: '/adminLayout/maps',
                name: 'maps',
                component: GoogleMap,
            }, {
                path: '/adminLayout/profile',
                name: 'profile',
                component: adminProfile,
            },
        ],
    },
    {
        path: '/404',
        name: '404',
        meta: { title: 'Not Found' },
        component: () => import(/* webpackChunkName: "errors-404" */ '@/views/error/NotFound.vue'),
    },

    {
        path: '/500',
        name: '500',
        meta: { title: 'Server Error' },
        component: () => import(/* webpackChunkName: "errors-500" */ '@/views/error/Error.vue'),
    },
]

export const protectedRouteClientDashboard = [
    {
        path: '/customerDashboard/',
        name: 'customerDashboard',
        component: customerDashboardLayoutIndex,
        beforeEnter: (to, from, next) => {
            if (localStorage.getItem('user-role') === 'user') {
                next()
            } else {
                alert('Not verified User redirecting to login page ...')
                next('/login')
            }
        },
        redirect: 'customerDashboard/home',
        children: [
            {
                path: '/customerDashboard/home',
                name: 'home',
                component: customerDashboardIndex,
            }, {
                path: '/customerDashboard/order',
                name: 'order',
                component: order,
            }, {
                path: '/customerDashboard/wishlist',
                name: 'wishlist',
                component: wishlist,
            }, {
                path: '/customerDashboard/vouchers',
                name: 'vouchers',
                component: coupon,
            }, {
                path: '/customerDashboard/cart',
                name: 'cart',
                component: shoppingCartIndex,
            }, {
                path: '/customerDashboard/profile',
                name: 'profile',
                component: userProfile,
            },
        ],
    },
]
