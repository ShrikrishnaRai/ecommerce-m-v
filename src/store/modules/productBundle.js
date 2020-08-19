import { get, getList, update, remove, post } from '@/utils/api'

const state = {
    productBundleList: [],
    isLoading: true,
    dialog: false,
    headers: [
        { text: 'Id', value: 'productBundleId' },
        { text: 'Name', value: 'productBundleName' },
        { text: 'Price', value: 'productBundlePrice' },
        { text: 'Status', value: 'status' },
        { text: 'Taxable', value: 'taxable' },
        { text: 'Quantity', value: 'totalQuantity' },
    ],
    persistIndex: -1,
    productBundle: {},
    temp: {
        productBundleCode: '',
        productBundleId: 0,
        productBundleName: '',
        productBundlePrice: 0,
        productMergerDTOList: [
            {
                productId: 0,
                productName: '',
            },
        ],
        remainingQuantity: 0,
        status: '',
        tag: '',
        taxAmount: 0,
        taxable: false,
        totalQuantity: 0,
    },
    defaultItem: {
        productBundleCode: '',
        productBundleId: 0,
        productBundleName: '',
        productBundlePrice: 0,
        productMergerDTOList: [
            {
                productId: 0,
                productName: '',
            },
        ],
        remainingQuantity: 0,
        status: '',
        tag: '',
        taxAmount: 0,
        taxable: true,
        totalQuantity: 0,
    },
productBundleUrl: 'product_bundle',

}

const mutations = {
    FETCH_PRODUCT_BUNDLE_LIST (state, payload) {
        console.log(payload)
        state.productBundleList = payload
    },
    CREATE_PRODUCT_BUNDLE_SUCCESS (state, payload) {
        state.productBundleList.unshift(payload)
    },
    DELETE_PRODUCT_BUNDLE_SUCCESS (state, payload) {
        state.productBundleList.splice(state.productBundleList.indexOf(payload), 1)
    },
    UPDATE_PRODUCT_BUNDLE_SUCCESS (state, payload) {
        for (const v of state.productBundleList) {
            if (v.productBundleId === payload) {
                state.productBundleList.splice(state.productBundleList.indexOf(v), 1, state.temp)
            }
        }
    },
    FETCH_PRODUCT_BUNDLE_WITH_ID (state, payload) {
        state.productBundle = payload
    },
}

const actions = {
    doGetProductBundleWithId ({ commit }, payload) {
        get(state.productBundleUrl, payload.productBundleId).then(() => {
            commit('FETCH_PRODUCT_BUNDLE_WITH_ID', payload)
        })
    },
    doGetProductBundleLists ({ commit }) {
        getList(state.productBundleUrl).then(response => {
            commit('FETCH_PRODUCT_BUNDLE_LIST', response.data.content)
            console.log('created' + response.data.content)
        })
    },
    doCreateProductBundle ({ commit }, payload) {
        post(state.productBundleUrl, payload).then(() => {
            commit('CREATE_PRODUCT_BUNDLE_SUCCESS', payload)
        })
    },
    doDeleteProductBundle ({ commit }, payload) {
        remove(state.productBundleUrl, payload.productBundleId).then(() => {
            commit('DELETE_PRODUCT_BUNDLE_SUCCESS', payload)
        })
    },
    doUpdateProductBundle ({ commit }, payload) {
        update(state.productBundleUrl, payload.productBundleId).then(() => {
            commit('UPDATE_PRODUCT_BUNDLE_SUCCESS', payload)
        })
    },
}

const getters = {
    productBundleList: state => state.productBundleList,
    productBundleHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
