import { getList, update, remove, post, get } from '@/utils/api'

const state = {
    vendorList: [],
    isLoading: true,
    dialog: false,
    headers: [
        { text: 'Vendor Id', value: 'vendorId' },
        { text: 'Vendor Name', value: 'vendorName' },
        { text: 'Vendor Type', value: 'vendorType' },
        { text: 'Status', value: 'status' },
        { text: 'Pan No', value: 'panNo' },
        { text: 'Contact', value: 'contactNo' },
        { text: 'Business type', value: 'businessType' },
        { text: 'Commission Amount', value: 'commissionAmount' },
    ],
    editedIndex: -1,
    editedItem: {
        address: {
            area: '',
            country: '',
            district: '',
            state: '',
            street: '',
            zipCode: '',
        },
        businessType: '',
        commissionAmount: 0,
        commissionMergerDTO: {
            commissionDescription: '',
            commissionTypeId: 0,
        },
        contact: {
            contactNo: '',
            email: '',
        },
        id: 0,
        image: '',
        latitude: '',
        longitude: '',
        panNo: '',
        password: '',
        status: '',
        taxClearanceNo: '',
        username: '',
        vendorName: '',
        vendorType: '',
    },
    defaultItem: {
        address: {
            area: '',
            country: '',
            district: '',
            state: '',
            street: '',
            zipCode: '',
        },
        businessType: '',
        commissionAmount: 0,
        commissionMergerDTO: {
            commissionDescription: '',
            commissionTypeId: 0,
        },
        contact: {
            contactNo: '',
            email: '',
        },
        id: 0,
        image: '',
        latitude: '',
        longitude: '',
        panNo: '',
        password: '',
        status: '',
        taxClearanceNo: '',
        username: '',
        vendorName: '',
        vendorType: '',
    },
vendorUrl: 'vendor',

}

const mutations = {
    LIST_SUCCESS (state, payload) {
        state.vendorList = payload
    },
    CREATE_VENDOR_SUCCESS (state, payload) {
        state.vendorList.unshift(payload)
    },
    DELETE_VENDOR_SUCCESS (state, payload) {
        state.vendorList.splice(state.vendorList.indexOf(payload), 1)
    },
    UPDATE_VENDOR_SUCCESS (state, payload) {
        for (const v of state.vendorList) {
            if (v.vendorId === payload) {
                const index = state.vendorList.indexOf(v)
                state.vendorList.splice(index, 1, state.editedItem)
            }
        }
    },
    GET_VENDOR_SUCCESS (state, payload) {
        state.defaultItem = payload
    },
}

const actions = {
    doGetVendorsLists ({ commit }) {
        getList(state.vendorUrl).then(response => {
            commit('LIST_SUCCESS', response.data.content)
        })
    },
    doCreateVendor ({ commit }, payload) {
        post(state.vendorUrl, payload).then(() => {
            commit('CREATE_VENDOR_SUCCESS', payload)
        })
    },
    doDeleteVendor ({ commit }, payload) {
        remove(state.vendorUrl, payload.vendorId).then(() => {
            commit('DELETE_VENDOR_SUCCESS', payload)
        })
    },
    doUpdateVendor ({ commit }, payload) {
        update(state.vendorUrl, payload.vendorId).then(() => {
            commit('UPDATE_VENDOR_SUCCESS', payload)
        })
    },
    doGetVendorById ({ commit }, vendorId) {
        get(state.vendorUrl, vendorId).then(response => {
            commit('GET_VENDOR_SUCCESS', response.data)
        })
    },
}

const getters = {
    vendorsList: state => state.vendorList,
    vendorHeader: state => state.headers,
    vendorDetails: state => state.defaultItem,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
