import { post, remove, getList, get } from '@/utils/api'

const state = {
    complainUrl: 'complain',
    complainList: [],
    isLoading: true,
    dialog: false,
    headers: [
        { text: 'Complain Id', value: 'complainId', filterable: false },
        { text: 'Message', value: 'message' },
        { text: 'Reply', value: 'reply', filterable: false },
        { text: 'status', value: 'status', filterable: false },
        { text: 'Topic', value: 'topic', filterable: false },
        { text: 'Actions', value: 'action', filterable: false },
    ],
    persistIndex: -1,
    temp: {
        complainId: 0,
        customerMergerDTO: {
            id: 0,
            username: 'string',
        },
        message: 'string',
        reply: 'string',
        status: 'SOLVED',
        topic: 'string',
    },
    complain: {
        complainId: 0,
        customerMergerDTO: {
            id: 0,
            username: 'string',
        },
        message: 'string',
        reply: 'string',
        status: 'SOLVED',
        topic: 'string',
    },
    defaultItem: {
        complainId: 0,
        customerMergerDTO: {
            id: 0,
            username: 'string',
        },
        message: 'string',
        reply: 'string',
        status: 'SOLVED',
        topic: 'string',
    },
}

const mutations = {
    FETCH_COMPLAIN_SUCCESS (state, payload) {
        state.complainList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 500)
    },
    CREATE_COMPLAIN_SUCCESS: (state, payload) => state.complainList.unshift(payload),
    DELETE_COMPLAIN_SUCCESS: (state, payload) => state.complainList.splice(state.complainList.indexOf(payload), 1),
    GET_COMPLAIN (state, payload) {
        state.complain = payload
    },
}

const actions = {
    async doGetComplainList ({ commit }) {
        await getList(state.complainUrl)
            .then(response =>
                commit('FETCH_COMPLAIN_SUCCESS', response.data.content))
    },
    async doCreateComplain ({ commit }, payload) {
        await post(state.complainUrl, payload).then(() => commit('CREATE_COMPLAIN_SUCCESS', payload))
    },
    async doDeleteComplain ({ commit }, payload) {
        await remove(state.complainUrl, payload.brandId).then(() => commit('DELETE_COMPLAIN_SUCCESS', payload))
    },
    async getComplainById ({ commit }, complainId) {
        await get(state.complainUrl, complainId).then(response => commit('', response.data.content))
    },
}

const getters = {
    complainList: state => state.complainList,
    complainHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
