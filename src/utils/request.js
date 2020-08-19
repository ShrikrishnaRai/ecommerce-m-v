import axios from 'axios'

// const baseUrl = 'http://ec2-107-23-74-171.compute-1.amazonaws.com/ecommerce_m_v-0.0.1/'

// const baseUrl = 'http://localhost/ecommerce_m_v-0.0.1/'

const baseUrl = 'http://localhost:8080'

const service = axios.create({
    baseURL: baseUrl,
    timeout: 50000,
})

export default service
