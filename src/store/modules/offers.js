import { post, remove, update, getList } from '@/utils/api'

const state = {
    offerUrl: 'offer',
    dialog: false,
    isLoading: true,
    offerList: [],
    headers: [
        { text: 'Offer Id', value: 'offerId', filterable: false },
        { text: 'Offer Name', value: 'offerName' },
        { text: 'Discount Type', value: 'discountType', filterable: false },
        { text: 'Discount Value', vale: 'discountValue', filterable: false },
        { text: 'Minimum Order', value: 'minimumOrder', filterable: false },
        { text: 'Products', value: 'productMergerDTOList' },
        { text: 'Vendor', value: 'vendorMergerDTO' },
        { text: 'Status', value: 'status' },
        { text: 'Valid From', value: 'validFrom' },
        { text: 'Valid To', value: 'validTo' },
        { text: 'Description', value: 'description', filterable: false },
    ],
    editedIndex: -1,
    offerEditedItem: {
        offerId: 0,
        offerName: '',
        discountType: '',
        discountValue: '',
        minimumOrder: '',
        productMergerDTOList: [],
        vendorMergerDTO: [],
        status: '',
        validFrom: '',
        validTo: '',
        description: '',
    },
    offerDefaultItem: {
        offerId: 0,
        offerName: '',
        discountType: '',
        discountValue: '',
        minimumOrder: '',
        productMergerDTOList: [],
        vendorMergerDTO: [],
        status: '',
        validFrom: '',
        validTo: '',
        description: '',
    },
}

const mutations = {
    LIST_SUCCESS (state, payload) {
        console.log(payload)
        state.offerList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 1000)
    },
    CREATE_LIST_SUCCESS (state, payload) {
        state.offerList.unshift(payload)
        console.log('this is', payload)
    },
    DELETE_LIST_SUCCESS (state, payload) {
        const index = state.offerList.indexOf(payload)
        state.offerList.splice(index, 1)
    },
    UPDATE_LIST_SUCCESS (state, payload) {
        for (const v of state.offerList) {
            if (v.offerId === payload) {
                const index = state.offerList.indexOf(v)
                state.offerList.splice(index, 1, state.offerEditedItem)
            }
        }
    },
}

const actions = {
    doGetOfferLists ({ commit }) {
        getList(state.offerUrl).then(response => {
            console.log(response)
            const offerList = response.data.map(x => x)
            commit('LIST_SUCCESS', offerList)
        })
    },
    doCreateOffer ({ commit }, payload) {
        try {
            post(state.offerUrl, payload).then(() => {
                commit('CREATE_LIST_SUCCESS', payload)
            })
        } catch (e) {
            console.log(e)
        }
    },
    doDeleteOffer ({ commit }, payload) {
        try {
            remove(state.offerUrl, payload.offerId).then(() => {
                commit('DELETE_LIST_SUCCESS', payload)
                console.log(payload)
            })
        } catch (e) {
            console.log(e)
        }
    },
    doUpdateOffer ({ commit }, payload) {
        try {
            update(state.offerUrl, payload.offerId).then(() => {
                commit('UPDATE_LIST_SUCCESS', payload)
                console.log(payload)
            })
        } catch (e) {
            console.log(e)
        }
    },
}

const getters = {
    offerList: state => state.offerList,
    offerHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
