import { post, remove, update, getList } from '@/utils/api'

const state = {
    brandUrl: 'brand',
    brandList: [],
    isLoading: true,
    dialog: false,
    headers: [
        { text: 'Brand Id', value: 'brandId', filterable: false },
        { text: 'Brand Name', value: 'brandName' },
        { text: 'Company Name', value: 'companyName', filterable: false },
        { text: 'status', value: 'status', filterable: false },
        { text: 'Actions', value: 'action', filterable: false },
    ],
    persistIndex: -1,
    temp: {
        brandId: 0,
        brandName: '',
        companyName: '',
        slug: '',
        status: '',
        productMergerDTOList: [
            {
                productId: '',
                productName: '',
            },
        ],
        fileDTO: {
            base64: 'string',
            lastModified: 'string',
            name: 'string',
            type: 'string',
        },
    },
    defaultItem: {
        brandId: 0,
        brandName: '',
        companyName: '',
        slug: '',
        status: '',
        productMergerDTOList: [
            {
                productId: '',
                productName: '',
            },
        ],
        fileDTO: {
            base64: 'string',
            lastModified: 'string',
            name: 'string',
            type: 'string',
        },
    },
}

const mutations = {
    FETCH_BRAND_LIST_SUCCESS (state, payload) {
        state.brandList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 500)
    },
    CREATE_BRAND_SUCCESS: (state, payload) => state.brandList.unshift(payload),
    DELETE_BRAND_SUCCESS: (state, payload) => state.brandList.splice(state.brandList.indexOf(payload), 1),
    UPDATE_BRAND_SUCCESS (state, payload) {
        const index = state.brandList.findIndex(brand => brand.id === payload.brandId)
        if (index !== -1) {
            state.brandList.splice(state.brandList.indexOf(state.temp), 1)
            state.brandList.unshift(state.temp)
        }
    },
}

const actions = {
    async doGetBrandList ({ commit }) {
        await getList(state.brandUrl)
            .then(response =>
                commit('FETCH_BRAND_LIST_SUCCESS', response.data.content),
            )
            .catch(error => alert(error))
    },
    async doCreateBrand ({ commit }, payload) {
        await post(state.brandUrl, payload).then(response => {
            alert(response.data)
            commit('CREATE_BRAND_SUCCESS', payload)
})
            .catch(error => alert(error))
    },
    async doDeleteBrand ({ commit }, payload) {
        await remove(state.brandUrl, payload.brandId).then(() => commit('DELETE_BRAND_SUCCESS', payload))
    },
    async doUpdateBrand ({ commit }, [payload, id]) {
        await update(state.brandUrl, id, payload).then(() => commit('UPDATE_BRAND_SUCCESS', payload))
    },
}

const getters = {
    brandList: state => state.brandList,
    brandHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
