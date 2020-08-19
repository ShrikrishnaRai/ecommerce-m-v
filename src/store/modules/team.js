import { getList, post, update, remove } from '@/utils/api'

const state = {
    teamList: [],
    dialog: false,
    isLoading: true,
    headers: [
        { text: 'Team Id', value: 'teamId', filterable: false },
        { text: 'Team Name', value: 'teamName' },
        { text: 'Delivery Boy', value: 'deliveryBoyMergerDTO.map(x=> x.name)' },
        { text: 'Status', value: 'status' },
        { text: 'Actions', value: 'action', filterable: false },
    ],
    editedIndex: -1,
    editedItem: {
        status: '',
        teamName: '',
        teamId: 0,
    },
    defaultItem: {
        teamId: 0,
        teamName: '',
        status: '',
    },
teamUrl: 'team',

}

const mutations = {
    LIST_SUCCESS (state, payload) {
        state.teamList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 1000)
    },
    CREATE_LIST_SUCCESS (state, payload) {
        state.teamList.unshift(payload)
    },
    DELETE_LIST_SUCCESS (state, payload) {
        state.teamList.splice(state.teamList.indexOf(payload), 1)
    },
    UPDATE_LIST_SUCCESS (state, payload) {
        for (const v of state.teamList) {
            if (v.teamId === payload) {
                state.teamList.splice(state.teamList.indexOf(v),
                    1, state.editedItem)
            }
        }
    },
}

const actions = {
    doGetTeamList ({ commit }) {
        getList(state.teamUrl).then(response => {
            commit('LIST_SUCCESS', response.data.content)
        })
    },
    doCreateTeam ({ commit, payload }) {
        try {
            post(state.teamUrl, payload).then(() => {
                commit('CREATE_LIST_SUCCESS', payload)
            })
        } catch (e) {
        }
    },
    doDeleteTeam ({ commit }, payload) {
        try {
            remove(state.teamUrl, payload.teamId).then(() => {
                commit('DELETE_LIST_SUCCESS', payload)
            })
        } catch (e) {
        }
    },
    doUpdateTeam ({ commit }, payload) {
        try {
            update(state.teamUrl, payload.teamId).then(() => {
                commit('UPDATE_LIST_SUCCESS', payload)
            })
        } catch (e) {
        }
    },
}

const getters = {
    teamList: state => state.teamList,
    teamHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
