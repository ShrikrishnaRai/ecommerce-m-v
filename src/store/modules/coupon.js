import { post, remove, getList, get, update } from '@/utils/api'

const state = {
    couponUrl: 'coupon',
    couponList: [],
    isLoading: true,
    dialog: false,
    headers: [
        { text: 'Coupon Id', value: 'couponId', filterable: false },
        { text: 'Coupon Name', value: 'name' },
        { text: 'Coupon Code', value: 'couponCode', filterable: false },
        { text: 'status', value: 'status', filterable: false },
        { text: 'Discount', value: 'discount', filterable: false },
        { text: 'Discount Type', value: 'discountType', filterable: false },
        { text: 'Actions', value: 'action', filterable: false },
    ],
    persistIndex: -1,
    temp: {
        couponCode: 'string',
        couponId: 0,
        description: 'string',
        discountType: 'PERCENT',
        discountValue: 0,
        name: 'string',
        status: 'PUBLISH',
        usesPerCoupon: 0,
        validFrom: 'string',
        validTo: 'string',
        vendorMergerDTO: {
            vendorId: 0,
            vendorName: 'string',
        },
    },
    defaultItem: {
        couponCode: 'string',
        couponId: 0,
        description: 'string',
        discountType: 'PERCENT',
        discountValue: 0,
        name: 'string',
        status: 'PUBLISH',
        usesPerCoupon: 0,
        validFrom: 'string',
        validTo: 'string',
        vendorMergerDTO: {
            vendorId: 0,
            vendorName: 'string',
        },
    },
}

const mutations = {
    FETCH_COUPON_LIST_SUCCESS (state, payload) {
        state.couponList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 500)
    },
    CREATE_COUPON_SUCCESS: (state, payload) => state.couponList.unshift(payload),
    DELETE_COUPON_SUCCESS: (state, payload) => state.couponList.splice(state.couponList.indexOf(payload), 1),
    UPDATE_COUPON_SUCCESS (state, payload) {
        const index = state.couponList.findIndex(coupon => coupon.id === payload.couponId)
        if (index !== -1) {
            state.couponList.splice(state.couponList.indexOf(state.temp), 1)
            state.couponList.unshift(state.temp)
        }
    },
}

const actions = {
    async doGetCouponList ({ commit }) {
        await getList(state.couponUrl)
            .then(response =>
                commit('FETCH_COUPON_LIST_SUCCESS', response.data.content))
    },
    async doCreateCoupon ({ commit }, payload) {
        await post(state.couponUrl, payload).then(() => commit('CREATE_COUPON_SUCCESS', payload))
    },
    async doDeleteCoupon ({ commit }, payload) {
        await remove(state.couponUrl, payload.couponId).then(() => commit('DELETE_COUPON_SUCCESS', payload))
    },
    async doUpdateCoupon ({ commit }, [payload, id]) {
        await update(state.couponUrl, id, payload).then(() => commit('UPDATE_COUPON_SUCCESS', payload))
    },
    async doGetCoupon ({ commit }, id) {
        await get(state.couponUrl, id).then(response => console.log(response))
    },
}

const getters = {
    couponList: state => state.couponList,
    couponHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
