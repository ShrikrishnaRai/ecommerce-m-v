import { get, post, getList, remove, update } from '@/utils/api'

const state = {
    customerWalletUrl: 'customerWallet',
    customerWalletList: [],
    isLoading: true,
    dialog: false,
    headers: [
        { text: 'Wallet Id', value: 'customerWalletId', filterable: false },
        { text: 'Amount', value: 'currentAmount' },
        { text: 'Customer name', value: 'companyName', filterable: false },
        { text: 'Actions', value: 'action', filterable: false },
    ],
    editedIndex: -1,
    editedItem: {
        currentAmount: 0,
        customerMergerDTO: {
            id: 0,
            username: '',
        },
        customerWalletId: 0,
    },
    defaultItem: {
        currentAmount: 0,
        customerMergerDTO: {
            id: 0,
            username: '',
        },
        customerWalletId: 0,
    },
}

const mutations = {
    FETCH_CUSTOMER_WALLET_LIST_SUCCESS (state, payload) {
        state.brandList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 1000)
    },
    CREATE_CUSTOMER_WALLET_SUCCESS (state, payload) {
        state.brandList.unshift(payload)
    },
    DELETE_CUSTOMER_SUCCESS (state, payload) {
        state.brandList.splice(state.brandList.indexOf(payload), 1)
    },
    UPDATE_CUSTOMER_SUCCESS (state, payload) {
        for (const v of state.brandList) {
            if (v.brandId === payload) {
                const index = state.brandList.indexOf(v)
                state.brandList.splice(index, 1, state.editedItem)
            }
        }
    },
}

const actions = {
    async doGetCustomerWalletWithId ({ commit }, id) {
        await get(state.customerWalletUrl, id)
            .then(response => {
                console.log(response)
            })
    },

    doGetCustomerWalletList ({ commit }) {
        getList(state.customerWalletUrl).then(response => {
            commit('FETCH_CUSTOMER_WALLET_LIST_SUCCESS', response.data.content)
        })
    },
    doCreateCustomerWallet ({ commit }, payload) {
        try {
            post(state.customerWalletUrl, payload).then(() => {
                commit('CREATE_CUSTOMER_WALLET_SUCCESS', payload)
            })
        } catch
            (e) {
            console.log(e)
        }
    },
    doDeleteCustomerWallet ({ commit }, payload) {
            remove(state.customerWalletUrl, payload.brandId).then(() => {
                commit('DELETE_CUSTOMER_SUCCESS', payload)
            })
        },
    doUpdateCustomerWallet ({ commit }, [payload, id]) {
            update(state.customerWalletUrl, payload, id).then(() => {
                commit('UPDATE_CUSTOMER_SUCCESS', payload)
            })
    },
}

const getters = {
    customerWalletList: state => state.brandList,
    dialog: state => state.dialog,
    headers: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
