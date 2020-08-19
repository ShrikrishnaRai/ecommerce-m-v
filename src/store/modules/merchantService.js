import { getList, update, remove, post } from '@/utils/api'

const state = {
    merchantServiceUrl: 'merchantServiceUrl',
    serviceList: [],
    isLoading: true,
    dialog: false,
    serviceHeaders: [
        { text: 'Id', value: 'serviceId' },
        { text: 'Name', value: 'serviceName' },
        { text: 'Description', value: 'description' },
        { text: 'Actions', value: 'action', filterable: false },
    ],
    editedIndex: -1,
    editedItem: {
        serviceId: 0,
        serviceName: '',
        description: '',
        slug: '',
    },
    defaultItem: {
        serviceId: 0,
        serviceName: '',
        description: '',
        slug: '',
    },
}

const mutations = {
    SERVICE_LIST_FETCH_SUCCESS (state, payload) {
        state.serviceList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 1000)
    },
    SERVICE_CREATE_SUCCESS (state, payload) {
        state.serviceList.unshift(payload)
    },
    SERVICE_DELETION_SUCCESS (state, payload) {
        state.serviceList.splice(state.serviceList.indexOf(payload), 1)
    },
    SERVICE_UPDATE_SUCCESS (state, payload) {
        for (const v of state.serviceList) {
            if (v.serviceId === payload) {
                state.serviceList.splice(state.serviceList.indexOf(v),
                    1,
                    state.editedItem)
            }
        }
    },
}

const actions = {
    doGetServiceList ({ commit }) {
        getList(state.merchantServiceUrl).then(response => {
            commit('SERVICE_LIST_FETCH_SUCCESS', response.data.content)
        })
    },
    doCreateService ({ commit }, payload) {
        try {
            post(state.merchantServiceUrl, payload).then(() => {
                commit('SERVICE_CREATE_SUCCESS', payload)
            })
        } catch (e) {
            console.log(e)
        }
    },
    doDeleteService ({ commit }, payload) {
        remove(state.merchantServiceUrl, payload.serviceId).then(() => {
            commit('SERVICE_DELETION_SUCCESS', payload)
        })
    },
    doUpdateService ({ commit }, payload) {
        update(state.merchantServiceUrl, payload.serviceId).then(() => {
            commit('SERVICE_UPDATE_SUCCESS', payload)
        })
    },
}

const getters = {
    serviceList: state => state.serviceList,
    serviceHeaders: state => state.serviceHeaders,

}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
