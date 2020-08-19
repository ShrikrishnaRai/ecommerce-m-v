import { getList, update, remove, post } from '@/utils/api'

const state = {
    subscriberList: [],
    isLoading: true,
    dialog: false,
    headers: [],
    editedIndex: -1,
    editedItem: {},
    defaultItem: {},
subscriberUrl: 'subscriber',

}

const mutations = {
    FETCH_SUBSCRIBER_LIST_SUCCESS (state, payload) {
        state.subscriberList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 1000)
    },
    CREATE_SUBSCRIBER_SUCCESS (state, payload) {
        state.subscriberList.unshift(payload)
    },
    DELETE_SUBSCRIBER_SUCCESS (state, payload) {
        state.subscriberList.splice(state.subscriberList.indexOf(payload), 1)
    },
    UPDATE_SUBSCRIBER_SUCCESS (state, payload) {
        const index = state.subscriberList.findIndex(subscriber => subscriber.subscriberId === payload.subscriberId)
        if (index !== -1) {
            state.subscriberList.splice(index, 1, state.editedItem)
        }
    },
}

const actions = {
    async doGetSubscriberList ({ commit }) {
        await getList(state.subscriberUrl).then(response => {
            commit('FETCH_SUBSCRIBER_LIST_SUCCESS', response.data.content)
        })
    },
    async doCreateSubscriberList ({ commit }, payload) {
        await post(state.subscriberUrl, payload).then(() => {
            commit('CREATE_SUBSCRIBER_SUCCESS', payload)
        })
    },
    async doDeleteSubscriber ({ commit }, payload) {
        await remove(state.subscriberUrl, payload.subscriberId).then(() => {
            commit('DELETE_SUBSCRIBER_SUCCESS', payload)
        })
    },
    async doUpdateSubscriber ({ commit }, payload) {
        await update(state.subscriberUrl, payload, payload.id).then(() => {
            commit('UPDATE_SUBSCRIBER_SUCCESS', payload)
        })
    },
}

const getters = {
    subscriberList: state => state.subscriberList,
    subscriberHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
