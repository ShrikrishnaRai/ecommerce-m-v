import { post, remove, update, getList } from '@/utils/api'

const state = {
    cancelOrderUrl: 'cancelOrder',
    cancelOrderList: [],
    isLoading: true,
    dialog: false,
    headers: [
        { text: 'CancelOrder Id', value: 'cancelOrderId', filterable: false },
        { text: 'Reason', value: 'reason' },
    ],
    persistIndex: -1,
    temp: {
        cancelOrderId: 0,
        reason: '',
    },
    defaultItem: {
        cancelOrderId: 0,
        reason: '',
    },
}

const mutations = {
    FETCH_CANCEL_ORDER_LIST_SUCCESS (state, payload) {
        state.cancelOrderList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 500)
    },
    CREATE_CANCEL_ORDER_SUCCESS: (state, payload) => state.cancelOrderList.unshift(payload),
    DELETE_CANCEL_ORDER_SUCCESS: (state, payload) => state.cancelOrderList.splice(state.cancelOrderList.indexOf(payload), 1),
    UPDATE_CANCEL_ORDER_SUCCESS (state, payload) {
        const index = state.cancelOrderList.findIndex(cancelOrder => cancelOrder.id === payload.cancelOrderId)
        if (index !== -1) {
            state.cancelOrderList.splice(state.cancelOrderList.indexOf(state.temp), 1)
            state.cancelOrderList.unshift(state.temp)
        }
    },
}

const actions = {
    async doGetCancelOrderList ({ commit }) {
        await getList(state.cancelOrderUrl)
            .then(response =>
                commit('FETCH_CANCEL_ORDER_LIST_SUCCESS', response.data))
    },
    async doCreateCancelOrder ({ commit }, payload) {
        await post(state.cancelOrderUrl, payload).then(() => commit('CREATE_CANCEL_ORDER_SUCCESS', payload))
    },
    async doDeleteCancelOrder ({ commit }, payload) {
        await remove(state.cancelOrderUrl, payload.cancelOrderId).then(() => commit('DELETE_CANCEL_ORDER_SUCCESS', payload))
    },
    async doUpdateCancelOrder ({ commit }, [payload, id]) {
        await update(state.cancelOrderUrl, id, payload).then(() => commit('UPDATE_CANCEL_ORDER_SUCCESS', payload))
    },
}

const getters = {
    cancelOrderList: state => state.cancelOrderList,
    cancelOrderHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
