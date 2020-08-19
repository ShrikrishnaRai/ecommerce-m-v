import {post, remove, update, getList} from '@/utils/api'

const state = {
    isAuthenticated: false
}

const mutations = {
}

const actions = {
}

const getters = {
    isAuthenticated: state => state.brandList,
    brandHeader: state => state.headers,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}
