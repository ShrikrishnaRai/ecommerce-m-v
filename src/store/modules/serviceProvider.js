import { post, remove, update, getList } from '@/utils/api'

const state = {
    serviceProviderUrl: 'serviceProvider',
    serviceProviderList: [],
    isLoading: true,
    dialog: false,
    headers: [
        { text: 'ServiceProvider Id', value: 'serviceProviderId', filterable: false },
        { text: 'Service Category', value: 'serviceCategoryMergerDTO' },
        { text: 'Location', value: 'serviceLocationMergerDTOS', filterable: false },
        { text: 'Service', value: 'serviceMergerDTOS', filterable: false },
        { text: 'Vendor', value: 'vendorMergerDTO', filterable: false },
        { text: 'Actions', value: 'action', filterable: false },
    ],
    persistIndex: -1,
    temp: {
        serviceCategoryMergerDTO: {
            serviceCategoryId: 0,
            serviceCategoryName: '',
        },
        serviceLocationMergerDTOS: [
            {
                city: '',
                serviceLocationId: 0,
            },
        ],
        serviceMergerDTOS: [
            {
                serviceId: 0,
                serviceName: '',
            },
        ],
        serviceProviderId: 0,
        vendorMergerDTO: {
            vendorId: 0,
            vendorName: '',
        },
    },
    defaultItem: {
        serviceCategoryMergerDTO: {
            serviceCategoryId: 0,
            serviceCategoryName: '',
        },
        serviceLocationMergerDTOS: [
            {
                city: '',
                serviceLocationId: 0,
            },
        ],
        serviceMergerDTOS: [
            {
                serviceId: 0,
                serviceName: '',
            },
        ],
        serviceProviderId: 0,
        vendorMergerDTO: {
            vendorId: 0,
            vendorName: '',
        },
    },
}

const mutations = {
    FETCH_SERVICE_PROVIDER_LIST_SUCCESS (state, payload) {
        state.serviceProviderList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 500)
    },
    CREATE_SERVICE_PROVIDER_SUCCESS: (state, payload) => state.serviceProviderList.unshift(payload),
    DELETE_SERVICE_PROVIDER_SUCCESS: (state, payload) => state.serviceProviderList.splice(state.serviceProviderList.indexOf(payload), 1),
    UPDATE_SERVICE_PROVIDER_SUCCESS (state, payload) {
        const index = state.serviceProviderList.findIndex(serviceProvider => serviceProvider.id === payload.serviceProviderId)
        if (index !== -1) {
            state.serviceProviderList.splice(state.serviceProviderList.indexOf(state.temp), 1)
            state.serviceProviderList.unshift(state.temp)
        }
    },
}

const actions = {
    async doGetServiceProviderList ({ commit }) {
        await getList(state.serviceProviderUrl)
            .then(response =>
                commit('FETCH_SERVICE_PROVIDER_LIST_SUCCESS', response.data.content))
    },
    async doCreateServiceProvider ({ commit }, payload) {
        await post(state.serviceProviderUrl, payload).then(() => commit('CREATE_SERVICE_PROVIDER_SUCCESS', payload))
    },
    async doDeleteServiceProvider ({ commit }, payload) {
        await remove(state.serviceProviderUrl, payload.serviceProviderId).then(() => commit('DELETE_SERVICE_PROVIDER_SUCCESS', payload))
    },
    async doUpdateServiceProvider ({ commit }, [payload, id]) {
        await update(state.serviceProviderUrl, id, payload).then(() => commit('UPDATE_SERVICE_PROVIDER_SUCCESS', payload))
    },
}

const getters = {
    serviceProviderList: state => state.serviceProviderList,
    serviceProviderHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
