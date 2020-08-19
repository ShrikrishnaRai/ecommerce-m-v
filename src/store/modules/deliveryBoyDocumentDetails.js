import { post, remove, update, getList } from '@/utils/api'

const state = {
    deliveryBoyDocumentDetailsUrl: 'deliveryBoyDocumentDetails',
    deliveryBoyDocumentDetailsList: [],
    isLoading: true,
    dialog: false,
    headers: [
        { text: 'Document Id', value: 'deliveryBoyDocumentDetailsId', filterable: false },
        { text: 'Delivery Boy', value: 'deliveryBoyMergerDTO', filterable: false },
        { text: 'Actions', value: 'action', filterable: false },
    ],
    persistIndex: -1,
    temp: {
        bluebookImage: '',
        citizenshipImage: '',
        deliveryBoyDocumentId: 0,
        deliveryBoyMergerDTO: {
            deliveryBoyId: 0,
            name: '',
        },
        licenseImage: '',
        links: {
            empty: true,
        },
        vehicleImage: '',
    },
defaultItem: {
        bluebookImage: '',
        citizenshipImage: '',
        deliveryBoyDocumentId: 0,
        deliveryBoyMergerDTO: {
            deliveryBoyId: 0,
            name: '',
        },
        licenseImage: '',
        links: {
            empty: true,
        },
        vehicleImage: '',
    },
}

const mutations = {
    FETCH_DELIVERY_BOY_DOCUMENT_DETAILS_LIST_SUCCESS (state, payload) {
        state.deliveryBoyDocumentDetailsList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 500)
    },
    CREATE_DELIVERY_BOY_DOCUMENT_DETAILS_SUCCESS: (state, payload) => state.deliveryBoyDocumentDetailsList.unshift(payload),
    DELETE_DELIVERY_BOY_DOCUMENT_DETAILS_SUCCESS: (state, payload) => state.deliveryBoyDocumentDetailsList.splice(state.deliveryBoyDocumentDetailsList.indexOf(payload), 1),
    UPDATE_DELIVERY_BOY_DOCUMENT_DETAILS_SUCCESS (state, payload) {
        const index = state.deliveryBoyDocumentDetailsList.findIndex(deliveryBoyDocumentDetails => deliveryBoyDocumentDetails.id === payload.deliveryBoyDocumentDetailsId)
        if (index !== -1) {
            state.deliveryBoyDocumentDetailsList.splice(state.deliveryBoyDocumentDetailsList.indexOf(state.temp), 1)
            state.deliveryBoyDocumentDetailsList.unshift(state.temp)
        }
    },
}

const actions = {
    async doGetDeliveryBoyDocumentDetailsList ({ commit }) {
        await getList(state.deliveryBoyDocumentDetailsUrl)
            .then(response =>
                commit('FETCH_DELIVERY_BOY_DOCUMENT_DETAILS_LIST_SUCCESS', response.data.content))
    },
    async doCreateDeliveryBoyDocumentDetails ({ commit }, payload) {
        await post(state.deliveryBoyDocumentDetailsUrl, payload).then(() => commit('CREATE_DELIVERY_BOY_DOCUMENT_DETAILS_SUCCESS', payload))
    },
    async doDeleteDeliveryBoyDocumentDetails ({ commit }, payload) {
        await remove(state.deliveryBoyDocumentDetailsUrl, payload.deliveryBoyDocumentDetailsId).then(() => commit('DELETE_DELIVERY_BOY_DOCUMENT_DETAILS_SUCCESS', payload))
    },
    async doUpdateDeliveryBoyDocumentDetails ({ commit }, [payload, id]) {
        await update(state.deliveryBoyDocumentDetailsUrl, id, payload).then(() => commit('UPDATE_DELIVERY_BOY_DOCUMENT_DETAILS_SUCCESS', payload))
    },
}

const getters = {
    deliveryBoyDocumentDetailsList: state => state.deliveryBoyDocumentDetailsList,
    deliveryBoyDocumentDetailsHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
