import { post, getList, remove } from '@/utils/api'

const state = {
    enquiryUrl: 'enquiry',
    enquiryList: [],
    isLoading: true,
    dialog: false,
    enquiryHeaders: [
        { text: 'Id', value: 'enquiryId' },
        { text: 'Sender email', value: 'senderEmail' },
        { text: 'Subject', value: 'subject' },
        { text: 'Date created', value: 'dateCreated' },
        { text: 'Timestamp', value: 'timeStamp' },
        { text: 'Reply Date', value: 'replyDate' },
        { text: 'Actions', value: 'action' },
    ],
    editedIndex: -1,
    editedItem: {
        enquiryId: 0,
        senderEmail: '',
        subject: '',
        timeStamp: '',
        replyDate: '',
    },
    defaultItem: {
        enquiryId: 0,
        senderEmail: '',
        subject: '',
        timeStamp: '',
        replyDate: '',
    },
}

const mutations = {
    ENQUIRY_LIST_FETCH_SUCCESS (state, payload) {
        state.enquiryList = payload
        setTimeout(() => {
            this.state.isLoading = false
        }, 100)
    },
    ENQUIRY_CREATE_SUCCESS (state, payload) {
        state.enquiryList.unshift(payload)
    },
    ENQUIRY_DELETION_SUCCESS (state, payload) {
        state.enquiryList.splice(state.enquiryList.indexOf(payload))
    },
}

const actions = {
    doGetEnquiryList ({ commit }) {
        getList(state.enquiryUrl).then(response => {
            commit('ENQUIRY_LIST_FETCH_SUCCESS', response.data.content)
        })
    },
    doCreateEnquiry ({ commit }, payload) {
        try {
            post(state.enquiryUrl, payload).then(() => {
                commit('ENQUIRY_CREATE_SUCCESS', payload)
            })
        } catch (e) {
            alert(e.toString())
        }
    },
    doDeleteEnquiry ({ commit }, payload) {
        remove(state.enquiryUrl, payload.enquiryId).then(() => {
            commit('ENQUIRY_DELETION_SUCCESS', payload)
        })
    },
}

const getters = {
    enquiryList: state => state.enquiryList,
    enquiryHeaders: state => state.enquiryHeaders,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
