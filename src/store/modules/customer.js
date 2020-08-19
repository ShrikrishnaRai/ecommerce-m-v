import { post, get, remove, update, getList } from '@/utils/api'

const state = {
    customerUrl: 'customer',
    customerList: [],
    isLoading: true,
    dialog: false,
    headers: [
        { text: 'Id', value: 'customerId' },
        { text: 'Name', value: 'customerName' },
        { text: 'Email', value: 'email' },
        { text: 'Status', value: 'status' },
        { text: 'Username', value: 'username' },
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
        contactNo: '',
        customerId: 0,
        email: '',
        image: '',
        password: '',
        preBookingMergerDTOList: [
            {
                preBookingId: 0,
                quantity: 0,
            },
        ],
        status: '',
        username: '',
        verificationCode: '',
        zipCode: '',
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
        contactNo: '',
        customerId: 0,
        email: '',
        image: '',
        password: '',
        preBookingMergerDTOList: [
            {
                preBookingId: 0,
                quantity: 0,
            },
        ],
        status: '',
        username: '',
        verificationCode: '',
        zipCode: '',
    },
}

const mutations = {
    LIST_SUCCESS (state, payload) {
        console.log(payload)
        state.customerList = payload
    },
    GET_CUSTOMER_SUCCESS (state, payload) {
        state.defaultItem = payload
    },
    CREATE_CUSTOMER_SUCCESS (state, payload) {
        state.customerList.unshift(payload)
    },
    DELETE_CUSTOMER_SUCCESS (state, payload) {
        state.customerList.splice(state.customerList.indexOf(payload), 1)
    },
    UPDATE_CUSTOMER_SUCCESS (state, payload) {
        for (const v of state.customerList) {
            if (v.customerId === payload) {
                const index = state.customerList.indexOf(v)
                state.customerList.splice(index, 1, state.editedItem)
            }
        }
    },
}

const actions = {

    doGetCustomersLists ({ commit }) {
        getList(state.customerUrl).then(response => {
            commit('LIST_SUCCESS', response.data.content)
        })
    },
    doCreateCustomer ({ commit }, payload) {
        post(state.customerUrl, payload).then(() => {
            commit('CREATE_CUSTOMER_SUCCESS', payload)
        })
    },
    doDeleteCustomer ({ commit }, payload) {
        remove(state.customerUrl, payload.customerId).then(() => {
            commit('DELETE_CUSTOMER_SUCCESS', payload)
        })
    },
    doUpdateCustomer ({ commit }, payload) {
        update(state.customerUrl, payload.customerId, payload).then(() => {
            commit('UPDATE_CUSTOMER_SUCCESS', payload)
        })
    },
    doGetCustomer ({ commit }, customerId) {
        get(state.customerUrl, customerId).then(response => {
            commit('GET_CUSTOMER_SUCCESS', response.data)
        })
    },
}

const getters = {
    customerList: state => state.customerList,
    customerHeader: state => state.headers,
    defaultItem: state => state.defaultItem,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
