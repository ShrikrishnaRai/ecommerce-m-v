import { post, remove, update, getList } from '@/utils/api'

const state = {
    serviceReviewUrl: 'serviceReview',
    serviceReviewList: [],
    isLoading: true,
    dialog: false,
    headers: [
        { text: 'ServiceReview Id', value: 'serviceReviewId', filterable: false },
        { text: 'ServiceReview Name', value: 'serviceReviewName' },
        { text: 'Service', value: 'serviceMergerDTO', filterable: false },
        { text: 'Reply', value: 'reply', filterable: false },
        { text: 'Rating', value: 'rating', filterable: false },
        { text: 'Actions', value: 'action', filterable: false },
    ],
    persistIndex: -1,
    temp: {
        rating: 0,
        reply: 'string',
        review: 'string',
        serviceMergerDTO: {
            serviceId: 0,
            serviceName: 'string',
        },
        serviceReviewId: 0,
    },
    defaultItem: {
        rating: 0,
        reply: 'string',
        review: 'string',
        serviceMergerDTO: {
            serviceId: 0,
            serviceName: 'string',
        },
        serviceReviewId: 0,
    },
}

const mutations = {
    FETCH_SERVICE_REVIEW_LIST_SUCCESS (state, payload) {
        state.serviceReviewList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 500)
    },
    CREATE_SERVICE_REVIEW_SUCCESS: (state, payload) => state.serviceReviewList.unshift(payload),
    DELETE_SERVICE_REVIEW_SUCCESS: (state, payload) => state.serviceReviewList.splice(state.serviceReviewList.indexOf(payload), 1),
    UPDATE_SERVICE_REVIEW_SUCCESS (state, payload) {
        const index = state.serviceReviewList.findIndex(serviceReview => serviceReview.id === payload.serviceReviewId)
        if (index !== -1) {
            state.serviceReviewList.splice(state.serviceReviewList.indexOf(state.temp), 1)
            state.serviceReviewList.unshift(state.temp)
        }
    },
}

const actions = {
    async doGetServiceReviewList ({ commit }) {
        await getList(state.serviceReviewUrl)
            .then(response =>
                commit('FETCH_SERVICE_REVIEW_LIST_SUCCESS', response.data))
    },
    async doCreateServiceReview ({ commit }, payload) {
        await post(state.serviceReviewUrl, payload).then(() => commit('CREATE_SERVICE_REVIEW_SUCCESS', payload))
    },
    async doDeleteServiceReview ({ commit }, payload) {
        await remove(state.serviceReviewUrl, payload.serviceReviewId).then(() => commit('DELETE_SERVICE_REVIEW_SUCCESS', payload))
    },
    async doUpdateServiceReview ({ commit }, [payload, id]) {
        await update(state.serviceReviewUrl, id, payload).then(() => commit('UPDATE_SERVICE_REVIEW_SUCCESS', payload))
    },
}

const getters = {
    serviceReviewList: state => state.serviceReviewList,
    serviceReviewDataTableHeaders: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
