import { post, remove, update, getList } from '@/utils/api'

const state = {
    deliveryAddressUrl: 'deliveryAddress',
    deliveryAddressList: [],
    isLoading: true,
    dialog: false,
    headers: [
        { text: 'DeliveryAddress Id', value: 'deliveryAddressId', filterable: false },
        { text: 'City', value: 'city' },
        { text: 'Street', value: 'street', filterable: false },
        { text: 'state', value: 'state', filterable: false },
        { text: 'district', value: 'district', filterable: false },
        { text: 'Actions', value: 'action', filterable: false },
    ],
    persistIndex: -1,
    temp: {
        city: 'string',
        contactNo: 'string',
        deliveryAddressId: 0,
        district: 'string',
        orderMergerDTO: {
            id: 0,
            orderTotalAmount: 0,
        },
        state: 'string',
        street: 'string',
    },
    defaultItem: {
        city: 'string',
        contactNo: 'string',
        deliveryAddressId: 0,
        district: 'string',
        orderMergerDTO: {
            id: 0,
            orderTotalAmount: 0,
        },
        state: 'string',
        street: 'string',
    },
}

const mutations = {
    FETCH_DELIVERY_ADDRESS_LIST_SUCCESS (state, payload) {
        state.deliveryAddressList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 500)
    },
    CREATE_DELIVERY_ADDRESS_SUCCESS: (state, payload) => state.deliveryAddressList.unshift(payload),
    DELETE_DELIVERY_ADDRESS_SUCCESS: (state, payload) => state.deliveryAddressList.splice(state.deliveryAddressList.indexOf(payload), 1),
    UPDATE_DELIVERY_ADDRESS_SUCCESS (state, payload) {
        const index = state.deliveryAddressList.findIndex(deliveryAddress => deliveryAddress.id === payload.deliveryAddressId)
        if (index !== -1) {
            state.deliveryAddressList.splice(state.deliveryAddressList.indexOf(state.temp), 1)
            state.deliveryAddressList.unshift(state.temp)
        }
    },
}

const actions = {
    async doGetDeliveryAddressList ({ commit }) {
        await getList(state.deliveryAddressUrl)
            .then(response =>
                commit('FETCH_DELIVERY_ADDRESS_LIST_SUCCESS', response.data))
    },
    async doCreateDeliveryAddress ({ commit }, payload) {
        await post(state.deliveryAddressUrl, payload).then(() => commit('CREATE_DELIVERY_ADDRESS_SUCCESS', payload))
    },
    async doDeleteDeliveryAddress ({ commit }, payload) {
        await remove(state.deliveryAddressUrl, payload.deliveryAddressId).then(() => commit('DELETE_DELIVERY_ADDRESS_SUCCESS', payload))
    },
    async doUpdateDeliveryAddress ({ commit }, [payload, id]) {
        await update(state.deliveryAddressUrl, id, payload).then(() => commit('UPDATE_DELIVERY_ADDRESS_SUCCESS', payload))
    },
}

const getters = {
    deliveryAddressList: state => state.deliveryAddressList,
    deliveryAddressHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
