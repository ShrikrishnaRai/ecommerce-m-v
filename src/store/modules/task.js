import { post, getList, update, remove } from '@/utils/api'

const state = {
    taskList: [],
    dialog: false,
    isLoading: true,
    headers: [
        { text: 'Task Id', value: 'taskId', filterable: false },
        { text: 'Status', value: 'status' },
        { text: 'Delivery Boy', value: 'deliveryBoyMergerDTO.name' },
        { text: 'Actions', value: 'action', filterable: false },
    ],
    editedIndex: -1,
    editedItem: {
        deliveryBoyMergerDTO: {
            deliveryBoyId: 0,
            name: '',
        },
        status: '',
        taskId: 0,
    },
    defaultItem: {},
taskUrl: 'task',

}

const mutations = {
    LIST_SUCCESS (state, payload) {
        console.log(payload)
        state.taskList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 1000)
    },
    CREATE_LIST_SUCCESS (state, payload) {
        state.taskList.unshift(payload)
        console.log('this is', payload)
    },
    DELETE_LIST_SUCCESS (state, payload) {
        const index = state.taskList.indexOf(payload)
        state.taskList.splice(index, 1)
    },
    UPDATE_LIST_SUCCESS (state, payload) {
        for (const v of state.taskList) {
            if (v.taskId === payload) {
                const index = state.taskList.indexOf(v)
                state.taskList.splice(index, 1, state.editedItem)
            }
        }
    },
}

const actions = {
    doGetTaskList ({ commit }) {
        getList(state.taskUrl).then(response => {
            commit('LIST_SUCCESS', response.data)
        })
    },
    doCreateTask ({ commit }, payload) {
        try {
            post(state.taskUrl, payload).then(() => {
                commit('CREATE_LIST_SUCCESS', payload)
            })
        } catch (e) {
            console.log(e)
        }
    },
    doDeleteTask ({ commit }, payload) {
        try {
            remove(state.taskUrl, payload.taskId).then(() => {
                commit('DELETE_LIST_SUCCESS', payload)
                console.log(payload)
            })
        } catch (e) {
            console.log(e)
        }
    },
    doUpdateTask ({ commit }, payload) {
        try {
            update(state.taskUrl, payload.taskId).then(() => {
                commit('UPDATE_LIST_SUCCESS', payload)
                console.log(payload)
            })
        } catch (e) {
            console.log(e)
        }
    },
}

const getters = {
    taskList: state => state.taskList,
    taskHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
