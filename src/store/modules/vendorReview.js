import { getList, get, remove, post } from '@/utils/api'

const state = {
    vendorsReviewList: [],
    vendorReviewList: [],
    headers: [],
    isLoading: true,
vendorReviewUrl: 'vendorReview',

}

const mutations = {
    FETCH_VENDOR_REVIEW_LIST_SUCCESS (state, payload) {
        state.vendorsReviewList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 1000)
    },
    REVIEW_VENDOR_SUCCESS (state, payload) {
        state.vendorReviewList.unshift(payload)
    },
    DELETE_VENDOR_REVIEW_SUCCESS (state, payload) {
        state.vendorReviewList.splice(state.vendorReviewList.indexOf(payload), 1)
    },
    FETCH_VENDOR_REVIEW (state, payload) {
        state.vendorReviewList = payload
    },
}

const actions = {
    doGetVendorReviewsLists ({ commit }) {
        getList(state.vendorReviewUrl).then(response => {
            commit('FETCH_VENDOR_REVIEW_LIST_SUCCESS', response.data.content)
        })
    },
    doReviewVendorReview ({ commit }, payload) {
        post(state.vendorReviewUrl, payload).then(() => {
            commit('REVIEW_VENDOR_SUCCESS', payload)
        })
    },
    doDeleteVendorReview ({ commit }, payload) {
        remove(state.vendorReviewUrl, payload.vendorId).then(() => {
            commit('DELETE_VENDOR_REVIEW_SUCCESS', payload)
        })
    },
    doGetVendorReviewList ({ commit }, payload) {
        get(state.vendorReviewUrl, payload.vendorId).then(() => {
            commit('FETCH_VENDOR_REVIEW', payload)
        })
    },
}

const getters = {
    vendorReviewList: state => state.vendorReviewList,
    vendorsReviewList: state => state.vendorsReviewList,
    vendorReviewHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
