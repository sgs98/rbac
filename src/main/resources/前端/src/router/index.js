import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Home from '../views/Home.vue'
import UserInfo from '../views/user/UserInfo'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Login',
        component: Login,
        hidden: true
    },{
        path: '/home',
        name: 'Home',
        component: Home,
        hidden: true,
        children:[
            {
                path: '/userInfo',
                name: '在线聊天',
                component: UserInfo,
                hidden: true
            }
        ]
    }

]

const router = new VueRouter({
    routes
})

export default router
