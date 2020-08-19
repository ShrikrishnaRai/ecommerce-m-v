import { post, remove, update, getList, get } from '@/utils/api'

const state = {
    productReviewUrl: 'productReview',
    productReviewList: [],
    isLoading: true,
    dialog: false,
    persistIndex: -1,
    temp: {
        id: 0,
        productMergerDTO: {
            productId: 0,
            productName: 'string',
        },
        rating: 0,
        reply: 'string',
        review: 'string',
    },
    defaultItem: {
        id: 0,
        productMergerDTO: {
            productId: 0,
            productName: 'string',
        },
        rating: 0,
        reply: 'string',
        review: 'string',
    },
}

const mutations = {
    FETCH_PRODUCT_REVIEW_LIST_SUCCESS (state, payload) {
        state.productReviewList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 500)
    },
    CREATE_PRODUCT_REVIEW_SUCCESS: (state, payload) => state.productReviewList.unshift(payload),
    DELETE_PRODUCT_REVIEW_SUCCESS: (state, payload) => state.productReviewList.splice(state.productReviewList.indexOf(payload), 1),
    UPDATE_PRODUCT_REVIEW_SUCCESS (state, payload) {
        const index = state.productReviewList.findIndex(productReview => productReview.id === payload.id)
        if (index !== -1) {
            state.productReviewList.splice(state.productReviewList.indexOf(state.temp), 1)
            state.productReviewList.unshift(state.temp)
        }
    },
    FETCH_PRODUCT_REVIEW_SUCCESS (state, payload) { state.defaultItem = payload },
}

const actions = {
    async doGetProductReviewByProductId ({ commit }, productId) {
        await get(state.productReviewUrl + '/product', productId)
            .then(response => {
                commit('FETCH_PRODUCT_REVIEW_LIST_SUCCESS', response.data)
            })
            .catch(error => alert(error),
        )
    },
    async doGetProductReviewList ({ commit }) {
        await getList(state.productReviewUrl)
            .then(response =>
                commit('FETCH_PRODUCT_REVIEW_LIST_SUCCESS', response.data.content)).catch(error => alert(error))
    },
    async doCreateProductReview ({ commit }, payload) {
        await post(state.productReviewUrl, payload).then(() => commit('CREATE_PRODUCT_REVIEW_SUCCESS', payload))
    },
    async doDeleteProductReview ({ commit }, payload) {
        await remove(state.productReviewUrl, payload.id).then(() => commit('DELETE_PRODUCT_REVIEW_SUCCESS', payload))
    },
    async doUpdateProductReview ({ commit }, [payload, id]) {
        await update(state.productReviewUrl, id, payload).then(() => commit('UPDATE_PRODUCT_REVIEW_SUCCESS', payload))
    },
}

const getters = {
    productReviewList: state => state.productReviewList,
    productReviewById: state => state.defaultItem,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
