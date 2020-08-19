import { post, remove, update, getList } from '@/utils/api'

const state = {
    deliveryBoyBankDetailsUrl: 'deliveryBoyBankDetail',
    deliveryBoyBankDetailsList: [],
    headers: [
        { text: 'Acc Id', value: 'bankId', filterable: false },
        { text: 'Name Id', value: 'bankName', filterable: false },
        { text: 'Acc No', value: 'accountNumber', filterable: false },
        { text: 'Acc Holder', value: 'accountHolder', filterable: false },
        { text: 'Delivery Boy', value: 'deliveryBoyMergerDTO', filterable: false },
        { text: 'Actions', value: 'action', filterable: false },

    ],
    isLoading: true,
    dialog: false,
    editedIndex: -1,
    defaultItem: {
        accountHolder: '',
        accountNumber: '',
        bankId: 0,
        bankName: '',
        deliveryBoyMergerDTO: {
            deliveryBoyId: 0,
            name: '',
        },
    },
    temp: {
        accountHolder: '',
        accountNumber: '',
        bankId: 0,
        bankName: '',
        deliveryBoyMergerDTO: {
            deliveryBoyId: 0,
            name: '',
        },
    },
}

const mutations = {
    LIST_SUCCESS (state, payload) {
        state.deliveryBoyBankDetailsList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 500)
    },
    CREATE_DELIVERY_BOY_BANK_DETAILS_SUCCESS (state, payload) {
        state.deliveryBoyBankDetailsList.unshift(payload)
    },
    DELETE_DELIVERY_BOY_BANK_DETAILS_SUCCESS (state, payload) {
        state.deliveryBoyBankDetailsList.splice(state.deliveryBoyBankDetailsList.indexOf(payload), 1)
    },
    UPDATE_DELIVERY_BOY_BANK_DETAILS_SUCCESS (state, payload) {
        for (const v of state.deliveryBoyBankDetailsList) {
            if (v.deliveryBoyBankDetailsId === payload) {
                const index = state.deliveryBoyBankDetailsList.indexOf(v)
                state.deliveryBoyBankDetailsList.splice(index, 1, state.editedItem)
            }
        }
    },
}

const actions = {
    async doGetDeliveryBoysDetailsLists ({ commit }) {
        await getList(state.deliveryBoyBankDetailsUrl)
            .then(response =>
                commit('LIST_SUCCESS', response.data.content))
    },
    async doCreateDeliveryBoyBankDetails ({ commit }, payload) {
        await post(state.deliveryBoyBankDetailsUrl, payload).then(() => {
            commit('CREATE_DELIVERY_BOY_BANK_DETAILS_SUCCESS', payload)
        })
    },
    async doDeleteDeliveryBoyBankDetails ({ commit }, payload) {
        await remove(state.deliveryBoyBankDetailsUrl, payload.deliveryBoyBankDetailsId).then(() => {
            commit('DELETE_DELIVERY_BOY_BANK_DETAILS_SUCCESS', payload)
        })
    },
    async doUdpateDeliveryBoyBankDetails ({ commit }, payload) {
        await update(state.deliveryBoyBankDetailsUrl, payload.deliveryBoyBankDetailsId, payload).then(() => {
            commit('UPDATE_DELIVERY_BOY_BANK_DETAILS_SUCCESS', payload)
        })
    },
}

const getters = {
    deliveryBoyBankDetailsList: state => state.deliveryBoyBankDetailsList,
    deliveryBoyBankDetailsHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
