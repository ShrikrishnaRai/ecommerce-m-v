import { post, remove, update, getList } from '@/utils/api'

const state = {
    productStockUrl: 'productStock',
    productStockList: [],
    isLoading: true,
    dialog: false,
    headers: [
        { text: 'ProductStock Id', value: 'productStockId', filterable: false },
        { text: 'Product', value: 'productMergerDTO' },
        { text: 'Remaining Quantity', value: 'remainingQuantity', filterable: false },
        { text: 'Total Quantity', value: 'totalQuantity', filterable: false },
        { text: 'Actions', value: 'action', filterable: false },
    ],
    persistIndex: -1,
    temp: {
        productMergerDTO: {
            productId: '',
            productName: '',
        },
        productStockId: '',
        remainingQuantity: '',
        totalQuantity: '',
    },
    defaultItem: {
        productMergerDTO: {
            productId: '',
            productName: '',
        },
        productStockId: '',
        remainingQuantity: '',
        totalQuantity: '',
    },
}

const mutations = {
    FETCH_PRODUCT_STOCK_LIST_SUCCESS (state, payload) {
        state.productStockList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 500)
    },
    CREATE_PRODUCT_STOCK_SUCCESS: (state, payload) => state.productStockList.unshift(payload),
    DELETE_PRODUCT_STOCK_SUCCESS: (state, payload) => state.productStockList.splice(state.productStockList.indexOf(payload), 1),
    UPDATE_PRODUCT_STOCK_SUCCESS (state, payload) {
        const index = state.productStockList.findIndex(productStock => productStock.id === payload.productStockId)
        if (index !== -1) {
            state.productStockList.splice(state.productStockList.indexOf(state.temp), 1)
            state.productStockList.unshift(state.temp)
        }
    },
}

const actions = {
    async doGetProductStockList ({ commit }) {
        await getList(state.productStockUrl)
            .then(response =>
                commit('FETCH_PRODUCT_STOCK_LIST_SUCCESS', response.data))
    },
    async doCreateProductStock ({ commit }, payload) {
        await post(state.productStockUrl, payload).then(() => commit('CREATE_PRODUCT_STOCK_SUCCESS', payload))
    },
    async doDeleteProductStock ({ commit }, payload) {
        await remove(state.productStockUrl, payload.productStockId).then(() => commit('DELETE_PRODUCT_STOCK_SUCCESS', payload))
    },
    async doUpdateProductStock ({ commit }, [payload, id]) {
        await update(state.productStockUrl, id, payload).then(() => commit('UPDATE_PRODUCT_STOCK_SUCCESS', payload))
    },
}

const getters = {
    productStockList: state => state.productStockList,
    productStockHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
