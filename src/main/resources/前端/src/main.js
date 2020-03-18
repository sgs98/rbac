import App from './App.vue'
import router from './router'
import store from './store'
import Vue from 'vue';
import 'element-ui/lib/theme-chalk/index.css';
import {initMenu} from "./utils/menus";
import ElementUI from 'element-ui';


import {postRequest} from "./utils/api";
import {putRequest} from "./utils/api";
import {deleteRequest} from "./utils/api";
import {getRequest} from "./utils/api";
import {postKeyValueRequest} from "./utils/api";

Vue.prototype.postRequest = postRequest;
Vue.prototype.postKeyValueRequest = postKeyValueRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.getRequest = getRequest;

Vue.config.productionTip = false

Vue.use(ElementUI,{size:'small'});

Vue.use(ElementUI);

router.beforeEach((to, from, next) => {
    initMenu(router, store);
    if (to.path == '/') {
        next();
    }else {
        if (window.sessionStorage.getItem("user")) {
            initMenu(router, store);
            next();
        }else{
            next('/?redirect='+to.path);
        }
    }
})
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
