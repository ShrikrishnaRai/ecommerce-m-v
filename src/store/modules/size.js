import { post, remove, update, getList } from '@/utils/api'

const state = {
    sizeUrl: 'size',
    sizeList: [],
    isLoading: true,
    dialog: false,
    headers: [
        { text: 'Size Id', value: 'sizeId', filterable: false },
        { text: 'Size Name', value: 'size' },
        { text: 'Actions', value: 'action', filterable: false },
    ],
    persistIndex: -1,
    temp: {
        size: '',
        sizeId: 0,
    },
    defaultItem: {
        size: '',
        sizeId: 0,
    },
}

const mutations = {
    FETCH_SIZE_LIST_SUCCESS (state, payload) {
        state.sizeList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 500)
    },
    CREATE_SIZE_SUCCESS: (state, payload) => state.sizeList.unshift(payload),
    DELETE_SIZE_SUCCESS: (state, payload) => state.sizeList.splice(state.sizeList.indexOf(payload), 1),
    UPDATE_SIZE_SUCCESS (state, payload) {
        const index = state.sizeList.findIndex(size => size.id === payload.sizeId)
        if (index !== -1) {
            state.sizeList.splice(state.sizeList.indexOf(state.temp), 1)
            state.sizeList.unshift(state.temp)
        }
    },
}

const actions = {
    async doGetSizeList ({ commit }) {
        await getList(state.sizeUrl)
            .then(response =>
                commit('FETCH_SIZE_LIST_SUCCESS', response.data.content))
    },
    async doCreateSize ({ commit }, payload) {
        await post(state.sizeUrl, payload).then(() => commit('CREATE_SIZE_SUCCESS', payload))
    },
    async doDeleteSize ({ commit }, payload) {
        await remove(state.sizeUrl, payload.sizeId).then(() => commit('DELETE_SIZE_SUCCESS', payload))
    },
    async doUpdateSize ({ commit }, [payload, id]) {
        await update(state.sizeUrl, id, payload).then(() => commit('UPDATE_SIZE_SUCCESS', payload))
    },
}

const getters = {
    sizeList: state => state.sizeList,
    sizeHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
