import Vue from 'vue'
import Router from 'vue-router'
import {
 publicRoute,
    protectedRouteAdmin,
    protectedRouteSuperAdmin,
    sharedRoute,
    protectedRouteClientDashboard,
} from './routerConfig'

const routes = publicRoute
    .concat(protectedRouteAdmin)
    .concat(protectedRouteSuperAdmin)
    .concat(sharedRoute)
    .concat(protectedRouteClientDashboard)

Vue.use(Router)

const router = new Router({
    mode: 'history',
    linkedActiveClass: 'active',
    routes: routes,
})

export default router
