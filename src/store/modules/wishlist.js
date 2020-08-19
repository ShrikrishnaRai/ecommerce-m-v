import { getList, remove, update, post } from '@/utils/api'

const state = {
    wishList: [],
    isLoading: true,
    dialog: false,
    headers: [
        { text: 'Wishlist Id', value: 'wishListId' },
        { text: 'Products', value: 'productMergerDTOList' },
        { text: 'Customers', value: 'customerMergerDTO' },
    ],
    editedIndex: -1,
    editedItem: {
        customerMergerDTO: {
            id: 0,
            username: '',
        },
        productMergerDTOList: [
            {
                productId: 0,
                productName: '',
            },
        ],
        wishListId: 0,
    },
    defaultItem: {
        customerMergerDTO: {
            id: 0,
            username: '',
        },
        productMergerDTOList: [
            {
                productId: 0,
                productName: '',
            },
        ],
        wishListId: 0,
    },
wishListUrl: 'wishList',
}

const mutations = {
    FETCH_WISHLIST_SUCCESS (state, payload) {
        state.wishList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 1000)
    },
    CREATE_WISHLIST_SUCCESS (state, payload) {
        state.wishList.unshift(payload)
    },
    DELETE_WISHLIST_SUCCESS (state, payload) {
        state.wishList.splice(state.wishList.indexOf(payload), 1)
    },
    UPDATE_WISHLIST_SUCCESS (state, payload) {
        for (const v of state.wishList) {
            if (v.wishListId === payload) {
                const index = state.wishList.indexOf(v)
                state.wishList.splice(index, 1, state.editedItem)
            }
        }
    },
}

const actions = {
    doGetWishList ({ commit }) {
        getList(state.wishListUrl).then(response => {
            commit('FETCH_WISHLIST_SUCCESS', response.data)
        })
    },
    doCreateWishList ({ commit }, payload) {
        post(state.wishListUrl, payload).then(() => {
            commit('CREATE_WISHLIST_SUCCESS', payload)
        })
    },
    doDeleteWishList ({ commit }, payload) {
        remove(state.wishListUrl, payload.wishListId).then(() => {
            commit('DELETE_WISHLIST_SUCCESS', payload)
        })
    },
    doUpdateWishLIst ({ commit }, payload) {
        update(state.wishListUrl, payload, payload.wishListId).then(() => {
            commit('UPDATE_WISHLIST_SUCCESS', payload)
        })
    },
}

const getters = {
    wishList: state => state.wishList,
    wishListHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
