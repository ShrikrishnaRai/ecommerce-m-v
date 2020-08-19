import { post, remove, update, getList } from '@/utils/api'

const state = {
    serviceCategoryUrl: 'serviceCategory',
    serviceCategoryList: [],
    isLoading: true,
    dialog: false,
    headers: [
        { text: 'ServiceCategory Id', value: 'serviceCategoryId', filterable: false },
        { text: 'ServiceCategory Name', value: 'serviceCategoryName' },
        { text: 'Service Provider', value: 'serviceProviderMergerDTO', filterable: false },
        { text: 'Actions', value: 'action', filterable: false },
    ],
    persistIndex: -1,
    temp: {
        serviceCategoryId: 0,
        serviceCategoryName: '',
        serviceDescription: '',
        serviceProviderMergerDTO: {
            serviceProviderId: 0,
        },
        serviceSlug: '',
    },
    defaultItem: {
        serviceCategoryId: 0,
        serviceCategoryName: '',
        serviceDescription: '',
        serviceProviderMergerDTO: {
            serviceProviderId: 0,
        },
        serviceSlug: '',
    },
}

const mutations = {
    FETCH_SERVICE_CATEGORY_LIST_SUCCESS (state, payload) {
        state.serviceCategoryList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 500)
    },
    CREATE_SERVICE_CATEGORY_SUCCESS: (state, payload) => state.serviceCategoryList.unshift(payload),
    DELETE_SERVICE_CATEGORY_SUCCESS: (state, payload) => state.serviceCategoryList.splice(state.serviceCategoryList.indexOf(payload), 1),
    UPDATE_SERVICE_CATEGORY_SUCCESS (state, payload) {
        const index = state.serviceCategoryList.findIndex(serviceCategory => serviceCategory.id === payload.serviceCategoryId)
        if (index !== -1) {
            state.serviceCategoryList.splice(state.serviceCategoryList.indexOf(state.temp), 1)
            state.serviceCategoryList.unshift(state.temp)
        }
    },
}

const actions = {
    async doGetServiceCategoryList ({ commit }) {
        await getList(state.serviceCategoryUrl)
            .then(response =>
                commit('FETCH_SERVICE_CATEGORY_LIST_SUCCESS', response.data.content))
    },
    async doCreateServiceCategory ({ commit }, payload) {
        await post(state.serviceCategoryUrl, payload).then(() => commit('CREATE_SERVICE_CATEGORY_SUCCESS', payload))
    },
    async doDeleteServiceCategory ({ commit }, payload) {
        await remove(state.serviceCategoryUrl, payload.serviceCategoryId).then(() => commit('DELETE_SERVICE_CATEGORY_SUCCESS', payload))
    },
    async doUpdateServiceCategory ({ commit }, [payload, id]) {
        await update(state.serviceCategoryUrl, id, payload).then(() => commit('UPDATE_SERVICE_CATEGORY_SUCCESS', payload))
    },
}

const getters = {
    serviceCategoryList: state => state.serviceCategoryList,
    serviceCategoryHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
