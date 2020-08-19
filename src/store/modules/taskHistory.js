import { post, remove, update, getList } from '@/utils/api'

const state = {
    taskHistoryUrl: 'taskTransactionHistory',
    taskHistoryList: [],
    isLoading: true,
    dialog: false,
    headers: [
        { text: 'Id', value: 'taskHistoryID', filterable: false },
        { text: 'Task', value: 'taskMergerDTO' },
        { text: 'Timestamp', value: 'timeStamp', filterable: false },
        { text: 'Date', value: 'dateCreated', filterable: false },
        { text: 'Status', value: 'status', filterable: false },
        { text: 'Timestamp', value: 'timeStamp', filterable: false },
        { text: 'Actions', value: 'action', filterable: false },
    ],
    persistIndex: -1,
    temp: {
        dateCreated: 'string',
        dateModified: 'string',
        links: {
            empty: true,
        },
        status: 'ASSIGNED',
        taskHistoryID: 0,
        taskMergerDTO: {
            status: 'string',
            taskId: 0,
        },
        timeStamp: 'string',
    },
    defaultItem: {
        dateCreated: 'string',
        dateModified: 'string',
        links: {
            empty: true,
        },
        status: 'ASSIGNED',
        taskHistoryID: 0,
        taskMergerDTO: {
            status: 'string',
            taskId: 0,
        },
        timeStamp: 'string',
    },
}

const mutations = {
    FETCH_DELIVERY_BOY_WALLET_HISTORY_LIST_SUCCESS (state, payload) {
        state.taskHistoryList = payload
        console.log(payload)
        setTimeout(() => {
            state.isLoading = false
        }, 500)
    },
    CREATE_DELIVERY_BOY_WALLET_HISTORY_SUCCESS: (state, payload) => state.taskHistoryList.unshift(payload),
    DELETE_DELIVERY_BOY_WALLET_HISTORY_SUCCESS: (state, payload) => state.taskHistoryList.splice(state.taskHistoryList.indexOf(payload), 1),
    UPDATE_DELIVERY_BOY_WALLET_HISTORY_SUCCESS (state, payload) {
        const index = state.taskHistoryList.findIndex(taskHistory => taskHistory.id === payload.taskHistoryId)
        if (index !== -1) {
            state.taskHistoryList.splice(state.taskHistoryList.indexOf(state.temp), 1)
            state.taskHistoryList.unshift(state.temp)
        }
    },
}

const actions = {
    async doGetDeliveryBoyWalletHistoryList ({ commit }) {
        await getList(state.taskHistoryUrl)
            .then(response =>
                commit('FETCH_DELIVERY_BOY_WALLET_HISTORY_LIST_SUCCESS', response.data.content))
    },
    async doCreateDeliveryBoyWalletHistory ({ commit }, payload) {
        await post(state.taskHistoryUrl, payload).then(() => commit('CREATE_DELIVERY_BOY_WALLET_HISTORY_SUCCESS', payload))
    },
    async doDeleteDeliveryBoyWalletHistory ({ commit }, payload) {
        await remove(state.taskHistoryUrl, payload.taskHistoryId).then(() => commit('DELETE_DELIVERY_BOY_WALLET_HISTORY_SUCCESS', payload))
    },
    async doUpdateDeliveryBoyWalletHistory ({ commit }, [payload, id]) {
        await update(state.taskHistoryUrl, id, payload).then(() => commit('UPDATE_DELIVERY_BOY_WALLET_HISTORY_SUCCESS', payload))
    },
}

const getters = {
    taskHistoryList: state => state.taskHistoryList,
    taskHistoryHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
