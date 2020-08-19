import { getList } from '@/utils/api'

const state = {
    deliveryBoyUrl: 'deliveryBoy',
    deliveryBoyList: [],
    isLoading: true,
    dialog: false,
    deliveryHeaders: [
        { text: 'Id', value: 'deliveryBoyId' },
        { text: 'Name', value: 'name' },
        { text: 'Email', value: 'email' },
        { text: 'Phone', value: 'phone' },
        { text: 'Permanent Address', value: 'permanentAddress' },
        { text: 'Temporary Address', value: 'temporaryAddress' },
        { text: 'Citizenship Number', value: 'citizenshipNo' },
        { text: 'License No', value: 'licenseNo' },
        { text: 'Vehicle Type', value: 'vehicleType' },
        { text: 'Number Plate', value: 'numberPlateVehicle' },
        { text: 'Status', value: 'Status' },
        { text: 'Username', value: 'Username' },
        { text: 'Avatar', value: 'avatar' },
    ],
    editedItem: {
        deliveryBoyId: 0,
        name: '',
        email: '',
        phone: '',
        permanentAddress: '',
        temporaryAddress: '',
        citizenshipNo: '',
        licenseNo: '',
        vehicleType: '',
        numberPlateVehicle: '',
        status: '',
        username: '',
        avatar: '',
        deliveryBoyDocumentMergerDTO: {
            deliveryBoyDocumentId: 0,
        },
        deliveryBoyBankDetailMergerDTO: {
            bankId: 0,
            accountHolder: '',
        },
        deliveryBoyWalletMergerDTO: {
            deliveryBoyWalletId: 0,
            currentAmount: 0,
        },
    },
    defaultItem: {
        deliveryBoyId: 0,
        name: '',
        email: '',
        phone: '',
        permanentAddress: '',
        temporaryAddress: '',
        citizenshipNo: '',
        licenseNo: '',
        vehicleType: '',
        numberPlateVehicle: '',
        status: '',
        username: '',
        avatar: '',
        deliveryBoyDocumentMergerDTO: {
            deliveryBoyDocumentId: 0,
        },
        deliveryBoyBankDetailMergerDTO: {
            bankId: 0,
            accountHolder: '',
        },
        deliveryBoyWalletMergerDTO: {
            deliveryBoyWalletId: 0,
            currentAmount: 0,
        },
    },
}

const mutations = {
    LIST_SUCCESS (state, payload) {
        console.log(payload)
        state.deliveryBoyList = payload
    },
}

const actions = {
    doGetDeliveryBoysLists ({ commit }) {
        getList(state.deliveryBoyUrl).then(response => {
         //   let x = response.data.map(x => x.content);
            commit('LIST_SUCCESS', response.data.content)
        })
    },
}

const getters = {
    deliveryBoyList: state => state.deliveryBoyList,
    deliveryHeaders: state => state.deliveryHeaders,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
