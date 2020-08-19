import { post, remove, update, getList } from '@/utils/api'

const state = {
    orderServiceUrl: 'orderServiceDetails',
    orderServiceList: [],
    isLoading: true,
    dialog: false,
    headers: [
        { text: 'OrderService Id', value: 'orderServiceId', filterable: false },
        { text: 'District', value: 'district' },
        { text: 'Date', value: 'serviceDate', filterable: false },
        { text: 'Time', value: 'serviceTime', filterable: false },
        { text: 'State', value: 'state', filterable: false },
        { text: 'Street', value: 'street', filterable: false },
        { text: 'Actions', value: 'action', filterable: false },
    ],
    persistIndex: -1,
    temp: {
        city: '',
        customerMergerDTO: {
            id: 0,
            username: '',
        },
        district: '',
        orderServiceId: 0,
        serviceDate: '',
        serviceTime: '',
        state: '',
        street: '',
    },
    defaultItem: {
        city: '',
        customerMergerDTO: {
            id: 0,
            username: '',
        },
        district: '',
        orderServiceId: 0,
        serviceDate: '',
        serviceTime: '',
        state: '',
        street: '',
    },
}

const mutations = {
    FETCH_BRAND_LIST_SUCCESS (state, payload) {
        state.orderServiceList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 500)
    },
    CREATE_BRAND_SUCCESS: (state, payload) => state.orderServiceList.unshift(payload),
    DELETE_BRAND_SUCCESS: (state, payload) => state.orderServiceList.splice(state.orderServiceList.indexOf(payload), 1),
    UPDATE_BRAND_SUCCESS (state, payload) {
        const index = state.orderServiceList.findIndex(orderService => orderService.id === payload.orderServiceId)
        if (index !== -1) {
            state.orderServiceList.splice(state.orderServiceList.indexOf(state.temp), 1)
            state.orderServiceList.unshift(state.temp)
        }
    },
}

const actions = {
    async doGetOrderServiceList ({ commit }) {
        await getList(state.orderServiceUrl)
            .then(response =>
                commit('FETCH_BRAND_LIST_SUCCESS', response.data.content))
    },
    async doCreateOrderService ({ commit }, payload) {
        await post(state.orderServiceUrl, payload).then(() => commit('CREATE_BRAND_SUCCESS', payload))
    },
    async doDeleteOrderService ({ commit }, payload) {
        await remove(state.orderServiceUrl, payload.orderServiceId).then(() => commit('DELETE_BRAND_SUCCESS', payload))
    },
    async doUpdateOrderService ({ commit }, [payload, id]) {
        await update(state.orderServiceUrl, id, payload).then(() => commit('UPDATE_BRAND_SUCCESS', payload))
    },
}

const getters = {
    orderServiceList: state => state.orderServiceList,
    orderServiceHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
