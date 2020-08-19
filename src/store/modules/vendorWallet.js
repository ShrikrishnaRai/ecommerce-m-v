import { getList, update, remove, post } from '@/utils/api'

const state = {
    vendorWalletList: [],
    isLoading: true,
    dialog: false,
    headers: [],
    editedIndex: -1,
    editedItem: {

    },
    defaultItem: {},
vendorWalletUrl: 'vendorWallet',

}

const mutations = {
    FETCH_VENDOR_WALLET_LIST_SUCCESS (state, payload) {
        state.vendorWalletList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 1000)
    },
    CREATE_VENDOR_WALLET_SUCCESS (state, payload) {
        state.vendorWalletList.unshift(payload)
    },
    DELETE_VENDOR_WALLET_SUCCESS (state, payload) {
        state.vendorWalletList.splice(state.vendorWalletList.indexOf(payload), 1)
    },
    UPDATE_VENDOR_WALLET_SUCCESS (state, payload) {
        const index = state.vendorWalletList.findIndex(vendorWallet => vendorWallet.vendorWalletId === payload.vendorWalletId)
        if (index !== -1) {
            state.vendorWalletList.splice(index, 1, state.editedItem)
        }
    },
}

const actions = {
    async doGetVendorWalletList ({ commit }) {
        await getList(state.vendorWalletUrl).then(response => {
            commit('FETCH_VENDOR_WALLET_LIST_SUCCESS', response.data.content)
        })
    },
    async doCreateVendorWallet ({ commit }) {
        await post(state.vendorWalletUrl).then(response => {
            commit('CREATE_VENDOR_WALLET_SUCCESS', response.data.content)
        })
    },
    async doDeleteVendorWallet ({ commit }, payload) {
        await remove(state.vendorWalletUrl, payload.vendorWalletId).then(() => {
            commit('DELETE_VENDOR_WALLET_SUCCESS', payload)
        })
    },
    async doUpdateVendorWallet ({ commit }, payload) {
        await update(state.vendorWalletUrl, payload, payload.vendorWalletId).then(() => {
            commit('UPDATE_VENDOR_WALLET_SUCCESS', payload)
        })
    },
}

const getters = {
    vendorWalletList: state => state.vendorWalletList,
    vendorWalletHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
