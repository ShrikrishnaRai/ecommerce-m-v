import { post, remove, update, getList } from '@/utils/api'

const state = {
    deliveryBoyWalletUrl: 'deliveryBoyWallet',
    deliveryBoyWalletList: [],
    isLoading: true,
    dialog: false,
    headers: [
        { text: 'DeliveryBoyWallet Id', value: 'deliveryBoyWalletId', filterable: false },
        { text: 'Current Amount', value: 'currentAmount' },
        { text: 'Delivery Boy', value: 'deliveryBoyMergerDTO', filterable: false },
        { text: 'History', value: 'deliveryBoyWalletHistoryMergerDTOS', filterable: false },
        { text: 'Actions', value: 'action', filterable: false },
    ],
    persistIndex: -1,
    temp: {
        currentAmount: 0,
        deliveryBoyMergerDTO: {
            deliveryBoyId: 0,
            name: '',
        },
        deliveryBoyWalletHistoryMergerDTOS: [
            {
                currentAmount: 0,
                deliveryBoyWalletHistoryId: 0,
            },
        ],
        deliveryBoyWalletId: 0,
    },
    defaultItem: {
        currentAmount: 0,
        deliveryBoyMergerDTO: {
            deliveryBoyId: 0,
            name: '',
        },
        deliveryBoyWalletHistoryMergerDTOS: [
            {
                currentAmount: 0,
                deliveryBoyWalletHistoryId: 0,
            },
        ],
        deliveryBoyWalletId: 0,
    },
}

const mutations = {
    FETCH_DELIVERY_BOY_WALLET_LIST_SUCCESS (state, payload) {
        state.deliveryBoyWalletList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 500)
    },
    CREATE_DELIVERY_BOY_WALLET_SUCCESS: (state, payload) => state.deliveryBoyWalletList.unshift(payload),
    DELETE_DELIVERY_BOY_WALLET_SUCCESS: (state, payload) => state.deliveryBoyWalletList.splice(state.deliveryBoyWalletList.indexOf(payload), 1),
    UPDATE_DELIVERY_BOY_WALLET_SUCCESS (state, payload) {
        const index = state.deliveryBoyWalletList.findIndex(deliveryBoyWallet => deliveryBoyWallet.id === payload.deliveryBoyWalletId)
        if (index !== -1) {
            state.deliveryBoyWalletList.splice(state.deliveryBoyWalletList.indexOf(state.temp), 1)
            state.deliveryBoyWalletList.unshift(state.temp)
        }
    },
}

const actions = {
    async doGetDeliveryBoyWalletList ({ commit }) {
        await getList(state.deliveryBoyWalletUrl)
            .then(response =>
                commit('FETCH_DELIVERY_BOY_WALLET_LIST_SUCCESS', response.data.content))
    },
    async doCreateDeliveryBoyWallet ({ commit }, payload) {
        await post(state.deliveryBoyWalletUrl, payload).then(() => commit('CREATE_DELIVERY_BOY_WALLET_SUCCESS', payload))
    },
    async doDeleteDeliveryBoyWallet ({ commit }, payload) {
        await remove(state.deliveryBoyWalletUrl, payload.deliveryBoyWalletId).then(() => commit('DELETE_DELIVERY_BOY_WALLET_SUCCESS', payload))
    },
    async doUpdateDeliveryBoyWallet ({ commit }, [payload, id]) {
        await update(state.deliveryBoyWalletUrl, id, payload).then(() => commit('UPDATE_DELIVERY_BOY_WALLET_SUCCESS', payload))
    },
}

const getters = {
    deliveryBoyWalletList: state => state.deliveryBoyWalletList,
    deliveryBoyWalletHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
