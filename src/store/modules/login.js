import { login } from '@/utils/api'
import { AUTH_ERROR } from '@/store/actions/auth'

const state = {
    token: localStorage.getItem('user-token') || '',
    status: '',
    hasLoadedOnce: false,
    temp: { email: '', password: '' },
    authUrl: 'login',
}

const mutations = {
    AUTH_REQUEST: state => { state.status = 'loading' },
    AUTH_SUCCESS: (state, response) => {
        state.status = 'admin'
        state.token = response.token
        state.hasLoadedOnce = true
    },
    AUTH_ERROR: state => {
        state.status = 'error'
        state.hasLoadedOnce = true
    },
    AUTH_LOGOUT: state => { state.token = '' },
}

const getters = {
    isAuthenticated: state => !state.token,
    status: state => state.status,
}

const actions = {
    async doLogin ({ commit }, payload) {
        commit('AUTH_REQUEST')
        await login(state.authUrl, payload)
            .then(response => {
                localStorage.setItem('user-token', response.data.jwt)
                commit('AUTH_SUCCESS', response)
                if (response.data.role === 'ROLE_ADMIN') {
                    localStorage.setItem('user-role', 'admin')
                    localStorage.setItem('user-id', response.data.userId)
                    localStorage.setItem('user-name', response.data.userName)
                    localStorage.setItem('user-profile-image', response.data.userImageUrl)
                } if (response.data.role === 'ROLE_USER') {
                    localStorage.setItem('user-role', 'user')
                    localStorage.setItem('user-id', response.data.userId)
                    localStorage.setItem('user-name', response.data.userName)
                    localStorage.setItem('user-profile-image', response.data.userImageUrl)
                } if (response.data.role === 'ROLE_SUPER_ADMIN') {
                    localStorage.setItem('user-role', 'superAdmin')
                } if (response.data.role === 'ROLE_DELIVERY_BOY') {
                    localStorage.setItem('user-role', 'deliveryBoy')
                }
            })
            .catch(error => {
                commit(AUTH_ERROR, error)
                localStorage.removeItem('user-token')
                localStorage.removeItem('user-role')
            })
    },
}

export default {
    namespaced: true,
    state,
    actions,
    mutations,
    getters,
}
