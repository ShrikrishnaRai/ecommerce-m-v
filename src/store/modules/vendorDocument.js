import { post, remove, update, getList } from '@/utils/api'

const state = {
    vendorDocumentUrl: 'vendorDocument',
    vendorDocumentList: [],
    isLoading: true,
    dialog: false,
    headers: [
        { text: 'VendorDocument Id', value: 'vendorDocumentId', filterable: false },
        { text: 'Vendor', value: 'vendorMergerDTO' },
        { text: 'Actions', value: 'action', filterable: false },
    ],
    persistIndex: -1,
    temp: {
        panDocument: 'string',
        taxClearance: 'string',
        vatDocument: 'string',
        vendorDocumentId: 0,
        vendorMergerDTO: {
            vendorId: 0,
            vendorName: 'string',
        },
    },
    defaultItem: {
        panDocument: 'string',
        taxClearance: 'string',
        vatDocument: 'string',
        vendorDocumentId: 0,
        vendorMergerDTO: {
            vendorId: 0,
            vendorName: 'string',
        },
    },
}

const mutations = {
    FETCH_VENDOR_DOCUMENT_LIST_SUCCESS (state, payload) {
        state.vendorDocumentList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 500)
    },
    CREATE_VENDOR_DOCUMENT_SUCCESS: (state, payload) => state.vendorDocumentList.unshift(payload),
    DELETE_VENDOR_DOCUMENT_SUCCESS: (state, payload) => state.vendorDocumentList.splice(state.vendorDocumentList.indexOf(payload), 1),
    UPDATE_VENDOR_DOCUMENT_SUCCESS (state, payload) {
        const index = state.vendorDocumentList.findIndex(vendorDocument => vendorDocument.id === payload.vendorDocumentId)
        if (index !== -1) {
            state.vendorDocumentList.splice(state.vendorDocumentList.indexOf(state.temp), 1)
            state.vendorDocumentList.unshift(state.temp)
        }
    },
}

const actions = {
    async doGetVendorDocumentList ({ commit }) {
        await getList(state.vendorDocumentUrl)
            .then(response =>
                commit('FETCH_VENDOR_DOCUMENT_LIST_SUCCESS', response.data.content))
    },
    async doCreateVendorDocument ({ commit }, payload) {
        await post(state.vendorDocumentUrl, payload).then(() => commit('CREATE_VENDOR_DOCUMENT_SUCCESS', payload))
    },
    async doDeleteVendorDocument ({ commit }, payload) {
        await remove(state.vendorDocumentUrl, payload.vendorDocumentId).then(() => commit('DELETE_VENDOR_DOCUMENT_SUCCESS', payload))
    },
    async doUpdateVendorDocument ({ commit }, [payload, id]) {
        await update(state.vendorDocumentUrl, id, payload).then(() => commit('UPDATE_VENDOR_DOCUMENT_SUCCESS', payload))
    },
}

const getters = {
    vendorDocumentList: state => state.vendorDocumentList,
    vendorDocumentHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
