import { getList, post, update, remove, getListWithId } from '@/utils/api'

const state = {
    orderList: [],
    isLoading: true,
    dialog: false,
    headers: [
        { text: 'Order Id', value: 'orderId' },
        { text: 'Status', value: 'orderStatus' },
        { text: 'Rate', value: 'deliveryRateMergerDTO' },
        { text: 'Tax', value: 'taxValue' },
        { text: 'Invoice', value: 'invoiceMergerDTO' },
        { text: 'Discount', value: 'discountAmount' },
        { text: 'Date', value: 'deliveryDate' },
        { text: 'Total', value: 'subTotal' },
        { text: 'Actions', value: 'action', filterable: false },
    ],
    persistIndex: -1,
    temp: {
        couponAmount: 0,
        deliveryAddressMergerDTO: {
            deliveryAddressId: 0,
            district: '',
        },
        deliveryDate: '',
        deliveryInstruction: '',
        deliveryRateMergerDTO: {
            city: '',
            deliveryRateId: 0,
        },
        discountAmount: 0,
        discountPercentage: 0,
        extraCharge: 0,
        invoiceMergerDTO: {
            invoiceId: 0,
            invoiceNumber: '',
        },
        orderId: 0,
        orderStatus: 'PENDING',
        subTotal: 0,
        taxType: 'PERCENT',
        taxValue: 0,
        totalAmount: 0,
},
    defaultItem: {
   couponAmount: 0,
        deliveryAddressMergerDTO: {
            deliveryAddressId: 0,
            district: '',
        },
        deliveryDate: '',
        deliveryInstruction: '',
        deliveryRateMergerDTO: {
            city: '',
            deliveryRateId: 0,
        },
        discountAmount: 0,
        discountPercentage: 0,
        extraCharge: 0,
        invoiceMergerDTO: {
            invoiceId: 0,
            invoiceNumber: '',
        },
        links: {
            empty: true,
        },
        orderId: 0,
        orderStatus: 'PENDING',
        subTotal: 0,
        taxType: 'PERCENT',
        taxValue: 0,
        totalAmount: 0,
},
    orderUrl: 'orders',

}
const mutations = {
    ORDER_LIST_FETCH_SUCCESS (state, payload) {
        state.orderList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 1000)
    },
    CREATE_ORDER_SUCCESS (state, payload) {
        state.orderList.unshift(payload)
    },
    DELETE_ORDER_SUCCESS (state, payload) {
        state.orderList.splice(state.orderList.indexOf(payload), 1)
    },
    UPDATE_ORDER_SUCCESS (state, payload) {
        for (const v of state.orderList) {
            if (v.orderId === payload) {
                const index = state.orderList.indexOf(v)
                state.orderList.splice(index, 1, state.editedItem)
            }
        }
    },
}

const actions = {

    async doGetOrderListByCustomerId ({ commit }, customerId) {
        await getListWithId(state.orderUrl + '/customer', customerId)
            // .then(response => alert(response.data.size))
            .then(response => commit('ORDER_LIST_FETCH_SUCCESS', response.data))
            .catch(error => alert(error))
    },
    async doGetOrderList ({ commit }) {
        await getList(state.orderUrl).then(response => {
            commit('ORDER_LIST_FETCH_SUCCESS', response.data.content)
            console.log(response.data.content)
        })
    },
    async doCreateOrder ({ commit }, payload) {
        await post(state.orderUrl, payload).then(() => {
            commit('', payload)
        })
    },
    async doDeleteOrder ({ commit }, payload) {
        await remove(state.orderUrl, payload.orderId).then(() => {
            commit('', payload)
        })
    },
    async doUpdateOrder ({ commit }, [payload, id]) {
        await update(state.orderUrl, payload, id).then(() => {
            commit('', payload)
        })
    },
}

const getters = {
    orderList: state => state.orderList,
    orderHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
