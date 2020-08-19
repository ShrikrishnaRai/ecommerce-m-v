import { get, post, remove } from '@/utils/api'

const state = {
    isLoading: true,
    headers: [
        { text: 'Shopping Cart Id', value: 'shoppingCartId', filterable: false },
        { text: 'Quantity', value: 'quantity' },
        { text: 'Grand Total', value: 'grandTotal' },
        { text: 'Product', value: 'productMergerDTOList' },
        ],
    shoppingCartUrl: 'shoppingCart',
    shoppingCartList: [],
    temp: {
        customerMergerDTO: {
            id: 0,
            username: '',
        },
        grandTotal: 0,
        productMergerDTO: {
            productId: 0,
            productName: '',
        },
        quantity: 0,
        shoppingCartId: 0,
    },
    defaultItem: {
        customerMergerDTO: {
            id: 0,
            username: '',
        },
        grandTotal: 0,
        productMergerDTO: {
            productId: 0,
            productName: '',
        },
        quantity: 0,
        shoppingCartId: 0,
    },
}

const mutations = {
    FETCH_SHOPPING_CART_LIST_SUCCESS (state, payload) {
        state.shoppingCartList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 500)
    },
    DELETE_CART_SUCCESS (state, cartId) {
        state.shoppingCartList.splice(state.shoppingCartList.indexOf(cartId), 1)
    },
}

const actions = {
    async doGetShoppingCartList ({ commit }, customerId) {
        await get(state.shoppingCartUrl + '/customer', customerId)
            .then(response => commit('FETCH_SHOPPING_CART_LIST_SUCCESS', response.data))
            .catch(error => alert(error))
    },
    async addToShoppingCart ({ commit }, payload) {
        await post(state.shoppingCartUrl, payload)
            .then(response => alert(response))
            .catch(error => error.status)
    },
    async doCheckCartAvailability ({ commit }, customerId, productId) {
        await get(state.shoppingCartUrl + '/availability')
    },
    async doDeleteProductFromCart ({ commit }, cartId) {
        await remove(state.shoppingCartUrl, cartId)
            .then(response => {
                commit('DELETE_CART_SUCCESS', cartId)
                alert(response.data)
            })
    },
}

const getters = {
    shoppingCartList: state => state.shoppingCartList,
    shoppingCartHeader: state => state.headers,
    shoppingCartTemp: state => state.temp,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
