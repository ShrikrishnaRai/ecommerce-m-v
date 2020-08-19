import { post, remove, update, getList } from '@/utils/api'

const state = {
    serviceLocationUrl: 'serviceLocation',
    serviceLocationList: [],
    isLoading: true,
    dialog: false,
    persistIndex: -1,
    temp: {
        city: '',
        serviceLocationId: 0,
        serviceMergerDTO: {
            serviceId: 0,
        },
    },
    defaultItem: {
        city: '',
        serviceLocationId: 0,
        serviceMergerDTO: {
            serviceId: 0,
        },
    },
}

const mutations = {
    FETCH_SERVICE_LOCATION_LIST_SUCCESS (state, payload) {
        state.serviceLocationList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 500)
    },
    CREATE_SERVICE_LOCATION_SUCCESS: (state, payload) => state.serviceLocationList.unshift(payload),
    DELETE_SERVICE_LOCATION_SUCCESS: (state, payload) => state.serviceLocationList.splice(state.serviceLocationList.indexOf(payload), 1),
    UPDATE_SERVICE_LOCATION_SUCCESS (state, payload) {
        const index = state.serviceLocationList.findIndex(serviceLocation => serviceLocation.id === payload.serviceLocationId)
        if (index !== -1) {
            state.serviceLocationList.splice(state.serviceLocationList.indexOf(state.temp), 1)
            state.serviceLocationList.unshift(state.temp)
        }
    },
}

const actions = {
    async doGetServiceProviderLocationList ({ commit }) {
        await getList(state.serviceLocationUrl)
            .then(response =>
                commit('FETCH_SERVICE_LOCATION_LIST_SUCCESS', response.data.content))
    },
    async doCreateServiceProviderLocation ({ commit }, payload) {
        await post(state.serviceLocationUrl, payload).then(() => commit('CREATE_SERVICE_LOCATION_SUCCESS', payload))
    },
    async doDeleteServiceProviderLocation ({ commit }, payload) {
        await remove(state.serviceLocationUrl, payload.serviceLocationId).then(() => commit('DELETE_SERVICE_LOCATION_SUCCESS', payload))
    },
    async doUpdateServiceProviderLocation ({ commit }, [payload, id]) {
        await update(state.serviceLocationUrl, id, payload).then(() => commit('UPDATE_SERVICE_LOCATION_SUCCESS', payload))
    },
}

const getters = {
    serviceLocationList: state => state.serviceLocationList,
    serviceLocationHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
