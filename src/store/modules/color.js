import { post, remove, update, getList } from '@/utils/api'

const state = {
    colorUrl: 'color',
    colorList: [],
    isLoading: true,
    dialog: false,
    temp: {
        colorCode: '',
        colorId: 0,
        colorName: '',
        productMergerDTOS: [
            {
                productId: '',
                productName: '',
            },
        ],
    },
    headers: [
        { text: 'Color Id', value: 'colorId', filterable: false },
        { text: 'Color Name', value: 'colorName' },
        { text: 'Color Code', value: 'colorCode', filterable: false },
        { text: 'Actions', value: 'action', filterable: false },
    ],
    persistIndex: -1,
    defaultItem: {
        colorCode: 'string',
        colorId: 0,
        colorName: 'string',
        productMergerDTOS: [
            {
                productId: '',
                productName: 'string',
            },
        ],
    },
}

const mutations = {
    FETCH_COLOR_LIST_SUCCESS (state, payload) {
        state.colorList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 500)
    },
    CREATE_COLOR_SUCCESS: (state, payload) => state.colorList.unshift(payload),
    DELETE_COLOR_SUCCESS: (state, payload) => state.colorList.splice(state.colorList.indexOf(payload), 1),
    UPDATE_COLOR_SUCCESS (state, payload) {
        const index = state.colorList.findIndex(color => color.id === payload.colorId)
        if (index !== -1) {
            state.colorList.splice(state.colorList.indexOf(state.temp), 1)
            state.colorList.unshift(state.temp)
        }
    },
}

const actions = {
    async doGetColorList ({ commit }) {
        await getList(state.colorUrl)
            .then(response =>
                commit('FETCH_COLOR_LIST_SUCCESS', response.data.content))
    },
    async doCreateColor ({ commit }, payload) {
        console.log(payload)
        await post(state.colorUrl, payload).then(() => commit('CREATE_COLOR_SUCCESS', payload))
    },
    async doDeleteColor ({ commit }, payload) {
        await remove(state.colorUrl, payload.colorId).then(() => commit('DELETE_COLOR_SUCCESS', payload))
    },
    async doUpdateColor ({ commit }, [payload, id]) {
        await update(state.colorUrl, payload, id).then(() => commit('UPDATE_COLOR_SUCCESS', payload))
    },
}

const getters = {
    colorList: state => state.colorList,
    colorHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
