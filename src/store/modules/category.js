import { post, remove, update, getList } from '@/utils/api'

const state = {
    categoryUrl: 'category',
    categoryProjectionUrl: 'category/namesList',
    categoryList: [],
    categoryNamesList: [],
    isLoading: true,
    dialog: false,
    headers: [
        { text: 'Category Id', value: 'categoryId' },
        { text: 'Parent Id', value: 'parentId' },
        { text: 'Category Name', value: 'categoryName' },
        { text: 'Actions', value: 'action' },
    ],
    persistIndex: -1,
    temp: {
        categoryId: 0,
        parentId: '',
        categoryName: '',
        description: '',
        categoryImage: '',
        productMergerDTOList: [
            {
                productId: '',
                productName: '',
            },
        ],
    },
    defaultItem: {
        categoryId: 0,
        parentId: '',
        categoryName: '',
        description: '',
        categoryImage: '',
        productMergerDTOList: [
            {
                productId: '',
                productName: '',
            },
        ],
    },
}

const mutations = {
    FETCH_CATEGORY_LIST_SUCCESS (state, payload) {
        state.categoryList = payload
        setTimeout(() => {
            state.isLoading = false
        }, 500)
    },
    FETCH_CATEGORY_NAMES_LIST_SUCCESS (state, payload) {
        state.categoryNamesList = payload
    },
    DELETE_CATEGORY_SUCCESS: (state, payload) => state.categoryList.splice(payload),
    CREATE_CATEGORY_SUCCESS: (state, payload) => state.categoryList.unshift(payload),
    UPDATE_CATEGORY_SUCCESS (state, payload) {
        const index = state.categoryList.findIndex(category => category.id === payload.categoryId)
        if (index !== -1) {
            state.categoryList.splice(state.categoryList.indexOf(state.temp), 1)
            state.categoryList.unshift(state.temp)
        }
    },
}

const actions = {
    async doGetCategoriesLists ({ commit }) {
        await getList(state.categoryUrl)
        .then(response =>
            commit('FETCH_CATEGORY_LIST_SUCCESS', response.data.content))
            .catch((error) => {
                if (error.response.status === 401) {
                    localStorage.removeItem('user-token')
                }
            })
    },
    async doCreateCategory ({ commit }, payload) {
         await post(state.categoryUrl, payload)
             .then(() =>
                 commit('CREATE_CATEGORY_SUCCESS', payload))
    },
    async doDeleteCategory ({ commit }, payload) {
         await remove(state.categoryUrl, payload.categoryId)
             .then(() =>
                commit('DELETE_CATEGORY_SUCCESS', payload))
    },
    async doUpdateCategory ({ commit }, payload) {
          await update(state.categoryUrl, payload.categoryId, payload)
              .then(() =>
                commit('UPDATE_CATEGORY_SUCCESS', payload))
    },
    async doGetCategoriesNamesLists ({ commit }) {
        await getList(state.categoryNamesList)
            .then(response =>
                commit('FETCH_CATEGORY_NAMES_LIST_SUCCESS', response.data))
    },
}

const getters = {
    categoryList: state => state.categoryList,
    categoryHeader: state => state.headers,
    categoryNamesList: state => state.categoryNamesList,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
