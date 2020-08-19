import { post, remove, update, getList } from '@/utils/api'

const state = {
    deliveryBoyWalletHistoryUrl: 'deliveryBoyWalletTransactionHistory',
    deliveryBoyWalletHistoryList: [],
    isLoading: true,
    dialog: false,
    headers: [
        { text: 'DeliveryBoyWalletHistory Id', value: 'deliveryBoyWalletHistoryId', filterable: false },
        { text: 'Wallet', value: 'deliveryBoyWalletMergerDTO' },
        { text: 'Timestamp', value: 'timeStamp', filterable: false },
        { text: 'Current Amount', value: 'currentAmount', filterable: false },
        { text: 'Date', value: 'dateCreated', filterable: false },
        { text: 'Status', value: 'status', filterable: false },
        { text: 'Timestamp', value: 'timeStamp', filterable: false },
        { text: 'Actions', value: 'action', filterable: false },
    ],
    persistIndex: -1,
    temp: {
        deliveryBoyMergerDTO: {
            deliveryBoyId: 0,
            name: '',
        },
        deliveryBoyWalletHistoryHistoryId: 0,
        status: '',
        totalAmount: 0,
    },
    defaultItem: {
        deliveryBoyMergerDTO: {
            deliveryBoyId: 0,
            name: '',
        },
        deliveryBoyWalletHistoryHistoryId: 0,
        status: '',
        totalAmount: 0,
    },
}

const mutations = {
    FETCH_DELIVERY_BOY_WALLET_HISTORY_LIST_SUCCESS (state, payload) {
        state.deliveryBoyWalletHistoryList = payload
        console.log(payload)
        setTimeout(() => {
            state.isLoading = false
        }, 500)
    },
    CREATE_DELIVERY_BOY_WALLET_HISTORY_SUCCESS: (state, payload) => state.deliveryBoyWalletHistoryList.unshift(payload),
    DELETE_DELIVERY_BOY_WALLET_HISTORY_SUCCESS: (state, payload) => state.deliveryBoyWalletHistoryList.splice(state.deliveryBoyWalletHistoryList.indexOf(payload), 1),
    UPDATE_DELIVERY_BOY_WALLET_HISTORY_SUCCESS (state, payload) {
        const index = state.deliveryBoyWalletHistoryList.findIndex(deliveryBoyWalletHistory => deliveryBoyWalletHistory.id === payload.deliveryBoyWalletHistoryId)
        if (index !== -1) {
            state.deliveryBoyWalletHistoryList.splice(state.deliveryBoyWalletHistoryList.indexOf(state.temp), 1)
            state.deliveryBoyWalletHistoryList.unshift(state.temp)
        }
    },
}

const actions = {
    async doGetDeliveryBoyWalletHistoryList ({ commit }) {
        await getList(state.deliveryBoyWalletHistoryUrl)
            .then(response =>
                commit('FETCH_DELIVERY_BOY_WALLET_HISTORY_LIST_SUCCESS', response.data.content))
    },
    async doCreateDeliveryBoyWalletHistory ({ commit }, payload) {
        await post(state.deliveryBoyWalletHistoryUrl, payload).then(() => commit('CREATE_DELIVERY_BOY_WALLET_HISTORY_SUCCESS', payload))
    },
    async doDeleteDeliveryBoyWalletHistory ({ commit }, payload) {
        await remove(state.deliveryBoyWalletHistoryUrl, payload.deliveryBoyWalletHistoryId).then(() => commit('DELETE_DELIVERY_BOY_WALLET_HISTORY_SUCCESS', payload))
    },
    async doUpdateDeliveryBoyWalletHistory ({ commit }, [payload, id]) {
        await update(state.deliveryBoyWalletHistoryUrl, id, payload).then(() => commit('UPDATE_DELIVERY_BOY_WALLET_HISTORY_SUCCESS', payload))
    },
}

const getters = {
    deliveryBoyWalletHistoryList: state => state.deliveryBoyWalletHistoryList,
    deliveryBoyWalletHistoryHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
