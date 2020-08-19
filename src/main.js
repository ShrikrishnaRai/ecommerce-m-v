import Vue from 'vue'
import App from './App.vue'
import router from './utils/router/routerIndex'
import store from './store'
import vuetify from './plugins/vuetify'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import * as VueGoogleMaps from 'vue2-google-maps'

Vue.config.productionTip = false
Vue.use(VueGoogleMaps, {
load: {
    key: 'AIzaSyCP5pptRr_fjQULLe-L94_9CuamJ8zF5Yw',
    libraries: 'places',
},
})

new Vue({
    router: router,
    store: store,
    vuetify,
    render: h => h(App),
}).$mount('#app')

// register globally
Vue.component('vue-multiselect', window.VueMultiselect.default)
