import { getList, remove, update, post } from '@/utils/api'

const state = {
    invoiceUrl: 'invoice',
    invoiceList: [],
    dialog: false,
    headers: [
        { text: 'Invoice Id', value: 'invoiceId' },
        { text: 'Invoice Number', value: 'invoiceNumber' },
        { text: 'Status', value: 'status' },
        { text: 'pdf', value: 'pdf' },
        { text: 'Actions', value: 'action' },
    ],
    editedIndex: -1,
    editedItem: {
        invoiceId: 0,
        invoiceNumber: '',
        status: '',
        pdf: '',
    },
    defaultItem: {
        invoiceId: 0,
        invoiceNumber: '',
        status: '',
        pdf: '',
    },
}

const mutations = {
    LIST_SUCCESS (state, payload) {
        console.log(payload)
        state.invoiceList = payload
    },
    CREATE_LIST_SUCCESS (state, payload) {
        state.invoiceList.unshift(payload)
        console.log('this is', payload)
    },
    DELETE_LIST_SUCCESS (state, payload) {
        const index = state.invoiceList.indexOf(payload)
        state.invoiceList.splice(index, 1)
    },
    UPDATE_LIST_SUCCESS (state, payload) {
        for (const v of state.invoiceList) {
            if (v.invoiceId === payload) {
                const index = state.invoiceList.indexOf(v)
                state.invoiceList.splice(index, 1, state.editedItem)
            }
        }
    },

}

const actions = {
    doGetInvoiceList ({ commit }) {
        getList(state.invoiceUrl).then(response => {
            console.log(response)
            commit('LIST_SUCCESS', response.data.content)
        })
    },
    doCreateInvoice ({ commit }, payload) {
        try {
            post(state.invoiceUrl, payload).then(() => {
                commit('CREATE_LIST_SUCCESS', payload)
            })
        } catch (e) {
            console.log(e)
        }
    },
    doDeleteInvoice ({ commit }, payload) {
        try {
            remove(state.invoiceUrl, payload.invoiceId).then(() => {
                commit('DELETE_LIST_SUCCESS', payload)
                console.log(payload)
            })
        } catch (e) {
            console.log(e)
        }
    },
    doUpdateInvoice ({ commit }, payload) {
        try {
            update(state.invoiceUrl, payload.invoiceId).then(() => {
                commit('UPDATE_LIST_SUCCESS', payload)
                console.log(payload)
            })
        } catch (e) {
            console.log(e)
        }
    },
}

const getters = {
    invoiceList: state => state.invoiceList,
    invoiceHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
