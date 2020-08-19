import { getList, remove, update, post } from '@/utils/api'

const state = {
    preBookingList: [],
    isLoading: true,
    dialog: false,
    headers: [
        { text: 'Id', value: 'preBookingId', filterable: false },
        { text: 'Price', value: 'totalPrice' },
        { text: 'Status', value: 'status' },
        { text: 'Quantity', value: 'quantity' },
        { text: 'Actions', value: 'action', filterable: false },
    ],
    persistIndex: -1,
    temp: {
        customerMergerDTO: {
            id: 0,
            username: '',
        },
        preBookingId: 0,
        price: 0,
        productMergerDTOList: [
            {
                productId: 0,
                productName: '',
            },
        ],
        quantity: 0,
        status: '',
        totalPrice: 0,
    },
    defaultItem: {
        customerMergerDTO: {
            id: 0,
            username: '',
        },
        preBookingId: 0,
        price: 0,
        productMergerDTOList: [
            {
                productId: 0,
                productName: '',
            },
        ],
        quantity: 0,
        status: '',
        totalPrice: 0,
    },
preBookingUrl: 'preBooking',

}

const mutations = {
    FETCH_PRE_BOOKING_LIST_SUCCESS (state, payload) {
        state.preBookingList = payload
        setTimeout(function () {
            state.isLoading = false
        }, 1000)
    },
    CREATE_PRE_BOOKING_SUCCESS (state, payload) {
        state.preBookingList.unshift(payload)
    },
    DELETE_PRE_BOOKING_SUCCESS (state, payload) {
        state.preBookingList.splice(state.preBookingList.indexOf(payload), 1)
    },
    UPDATE_PRE_BOOKING_SUCCESS (state, payload) {
        const index = state.preBookingList.findIndex(preBooking => preBooking.preBookingId === payload.preBookingId)
        if (index !== -1) {
            state.preBookingList.splice(index, 1, state.editedItem)
        }
    },
}

const actions = {
    async doGetPreBookingList ({ commit }) {
        await getList(state.preBookingUrl).then(response => {
            commit('FETCH_PRE_BOOKING_LIST_SUCCESS', response.data.content)
        })
    },
    async doCreatePreBooking ({ commit }, payload) {
        await post(state.preBookingUrl, payload).then(() => {
            commit('CREATE_PRE_BOOKING_SUCCESS', payload)
        })
    },
    async doDeletePreBooking ({ commit }, payload) {
        await remove(state.preBookingUrl, payload.preBookingId).then(() => {
            commit('DELETE_PRE_BOOKING_SUCCESS', payload)
        })
    },
    async doUpdatePreBooking ({ commit }, payload) {
        await update(state.preBookingUrl, payload, payload.preBookingId).then(() => {
            commit('UPDATE_PRE_BOOKING_SUCCESS', payload)
        })
    },
}

const getters = {
    preBookingList: state => state.preBookingList,
    preBookingHeaders: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters
    ,
}
