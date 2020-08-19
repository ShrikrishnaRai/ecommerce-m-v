import { getList, update, post, remove, get } from '@/utils/api'

const state = {
    productHeader: [
        { text: 'Id', value: 'product_id', filterable: false },
        { text: 'Name', value: 'productName' },
        { text: 'Status', value: 'status' },
        { text: 'Taxable', value: 'isTaxable' },
        { text: 'Discount Value', value: 'discountValue' },
        { text: 'Type', value: 'productType' },
        { text: 'Unit', value: 'unit' },
        { text: 'Quality', value: 'quality' },
        { text: 'Price', value: 'purchasePrice' },
        { text: 'Packaging fee', value: 'packagingFee' },
        { text: 'Actions', value: 'action', filterable: false },
    ],
    temp: {
        brandMergerDTO: {
            brandId: 0,
            brandName: '',
        },
        categoryMergerDTO: {
            categoryName: '',
            id: 0,
        },
        colorMergerDTOList: [
            {
                colorCode: '',
                colorId: 0,
                colorName: '',
            },
        ],
        description: '',
        discountType: 'PERCENT',
        discountValue: 0,
        links: {
            empty: true,
        },
        offerMergerDTO: {
            offerId: 0,
            offerName: '',
        },
        packagingFee: 0,
        preBookingMergerDTOS: [
            {
                preBookingId: 0,
                quantity: 0,
            },
        ],
        prebooking: false,
        productBundleMergerDTOS: [
            {
                productBundleCode: '',
                productBundleId: 0,
            },
        ],
        productCode: '',
        productImageMergerDTOS: [
            {
                productImage: '',
                productImageId: 0,
            },
        ],
        productName: '',
        productReviewMergerDTOS: [
            {
                productReviewId: 0,
                rating: 0,
            },
        ],
        productStockMergerDTO: {
            productStockId: 0,
            remainingQuantity: 0,
            totalQuantity: 0,
        },
        productType: 'DIGITAL_PRODUCT',
        product_id: 0,
        purchasePrice: 0,
        quality: '',
        salePrice: 0,
        sizeMergerDTOList: [
            {
                size: '',
                sizeId: 0,
            },
        ],
        slug: '',
        status: 'AVAILABLE',
        taxAmount: 0,
        taxType: 'PERCENT',
        taxable: false,
        unit: '',
        productImageUrl: '',
    },
    isLoading: true,
    dialog: false,
    productList: [],
    defaultItem: {
       brandMergerDTO: {
        brandId: 0,
        brandName: '',
        },
        categoryMergerDTO: {
        categoryName: '',
            id: 0,
        },
    colorMergerDTOList: [
    {
        colorCode: '',
        colorId: 0,
        colorName: '',
    },
    ],
        description: '',
        discountType: 'PERCENT',
        discountValue: 0,

        offerMergerDTO: {
        offerId: 0,
        offerName: '',
    },
    packagingFee: 0,
    preBookingMergerDTOS: [
    {
        preBookingId: 0,
        quantity: 0,
    },
    ],
    prebooking: false,
    productBundleMergerDTOS: [
    {
        productBundleCode: '',
        productBundleId: 0,
    },
    ],
    productCode: '',
    productImageMergerDTOS: [
    {
        productImage: '',
        productImageId: 0,
    },
    ],
    productName: '',
    productReviewMergerDTOS: [
    {
        productReviewId: 0,
        rating: 0,
    },
    ],
    productStockMergerDTO: {
    productStockId: 0,
        remainingQuantity: 0,
        totalQuantity: 0,
    },
    productType: 'DIGITAL_PRODUCT',
    product_id: 0,
    purchasePrice: 0,
    quality: '',
    salePrice: 0,
    sizeMergerDTOList: [
    {
        size: '',
        sizeId: 0,
    },
    ],
    slug: '',
    status: 'AVAILABLE',
    taxAmount: 0,
    taxType: 'PERCENT',
    taxable: false,
    unit: '',
    },
    productMergerList: [],
    productUrl: 'product',
    persistIndex: -1,
    product: {},
}

const mutations = {
    FETCH_PRODUCT_SUCCESS (state, payload) {
        state.product = payload
    },
    FETCH_PRODUCT_LIST_SUCCESS (state, payload) {
        state.productList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 500)
    },
    CREATE_PRODUCT_SUCCESS (state, payload) {
        state.productList.unshift(payload)
        console.log('this is', payload)
    },
    DELETE_PRODUCT_SUCCESS (state, payload) {
        state.productList.splice(state.productList.indexOf(payload), 1)
    },
    UPDATE_PRODUCT_SUCCESS (state, payload) {
        for (const v of state.productList) {
            if (v.productId === payload) {
                state.productList.splice(state.productList.indexOf(v), 1, state.productEditedItem)
            }
        }
    },
    LIST_MERGER_SUCCESS (state, payload) {
        state.productMergerList = payload
    },
}

const actions = {
    async doUpdateProduct ({ commit }, payload) {
          await update(state.productUrl, payload.productId).then(() =>
                commit('UPDATE_PRODUCT_SUCCESS', payload))
    },
    async doDeleteProduct ({ commit }, payload) {
         await remove(state.productUrl, payload.productId).then(() =>
                commit('DELETE_PRODUCT_SUCCESS', payload))
    },
    async doCreateProduct ({ commit }, payload) {
          await post(state.productUrl, payload).then(() =>
                commit('CREATE_PRODUCT_SUCCESS', payload))
    },
    async doGetProductMergerList ({ commit }) {
        await getList(state.productUrl + '/productMergerList').then(response => {
            commit('LIST_MERGER_SUCCESS', response.data.content)
        })
    },
    async doGetProductsLists ({ commit }) {
        await getList(state.productUrl).then(response => {
            commit('FETCH_PRODUCT_LIST_SUCCESS', response.data.content)
        })
    },
    async doGetProductListByCategory ({ commit }, categoryId) {
        await get(state.productUrl + '/category', categoryId)
            .then(response => {
                commit('FETCH_PRODUCT_LIST_SUCCESS', response.data)
            })
            .catch((error) => {
                alert(error)
            })
    },
    async doGetProductById ({ commit }, productId) {
        await get(state.productUrl, productId)
            .then(response => {
                commit('FETCH_PRODUCT_SUCCESS', response.data)
            })
    },
}

const getters = {
    productList: state => state.productList,
    productHeader: state => state.productHeader,
    productMergerList: state => state.productMergerList,
    product: state => state.product,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
