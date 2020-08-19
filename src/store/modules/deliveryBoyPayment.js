import { post, remove, update, getList } from '@/utils/api'

const state = {
    deliveryBoyPaymentUrl: 'deliveryBoyPaymentHistoryTransaction',
    deliveryBoyPaymentList: [],
    isLoading: true,
    dialog: false,
    headers: [
        { text: 'DeliveryBoyPayment Id', value: 'deliveryBoyPaymentHistoryId', filterable: false },
        { text: 'DeliveryBoyPayment', value: 'deliveryBoyMergerDTO' },
        { text: 'Timestamp', value: 'timeStamp', filterable: false },
        { text: 'Date', value: 'dateCreated', filterable: false },
        { text: 'status', value: 'status', filterable: false },
        { text: 'Actions', value: 'action', filterable: false },
    ],
    persistIndex: -1,
    temp: {
        deliveryBoyMergerDTO: {
            deliveryBoyId: 0,
            name: '',
        },
        deliveryBoyPaymentHistoryId: 0,
        status: '',
        totalAmount: 0,
    },
    defaultItem: {
        deliveryBoyMergerDTO: {
            deliveryBoyId: 0,
            name: '',
        },
        deliveryBoyPaymentHistoryId: 0,
        status: '',
        totalAmount: 0,
    },
}

const mutations = {
    FETCH_DELIVERY_BOY_LIST_SUCCESS (state, payload) {
        state.deliveryBoyPaymentList = payload
        console.log(payload)
        setTimeout(() => {
            state.isLoading = false
        }, 500)
    },
    CREATE_DELIVERY_BOY_SUCCESS: (state, payload) => state.deliveryBoyPaymentList.unshift(payload),
    DELETE_DELIVERY_BOY_SUCCESS: (state, payload) => state.deliveryBoyPaymentList.splice(state.deliveryBoyPaymentList.indexOf(payload), 1),
    UPDATE_DELIVERY_BOY_SUCCESS (state, payload) {
        const index = state.deliveryBoyPaymentList.findIndex(deliveryBoyPayment => deliveryBoyPayment.id === payload.deliveryBoyPaymentId)
        if (index !== -1) {
            state.deliveryBoyPaymentList.splice(state.deliveryBoyPaymentList.indexOf(state.temp), 1)
            state.deliveryBoyPaymentList.unshift(state.temp)
        }
    },
}

const actions = {
    async doGetDeliveryBoyPaymentList ({ commit }) {
        await getList(state.deliveryBoyPaymentUrl)
            .then(response =>
                commit('FETCH_DELIVERY_BOY_LIST_SUCCESS', response.data.content))
    },
    async doCreateDeliveryBoyPayment ({ commit }, payload) {
        await post(state.deliveryBoyPaymentUrl, payload).then(() => commit('CREATE_DELIVERY_BOY_SUCCESS', payload))
    },
    async doDeleteDeliveryBoyPayment ({ commit }, payload) {
        await remove(state.deliveryBoyPaymentUrl, payload.deliveryBoyPaymentId).then(() => commit('DELETE_DELIVERY_BOY_SUCCESS', payload))
    },
    async doUpdateDeliveryBoyPayment ({ commit }, [payload, id]) {
        await update(state.deliveryBoyPaymentUrl, id, payload).then(() => commit('UPDATE_DELIVERY_BOY_SUCCESS', payload))
    },
}

const getters = {
    deliveryBoyPaymentList: state => state.deliveryBoyPaymentList,
    deliveryBoyPaymentHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
