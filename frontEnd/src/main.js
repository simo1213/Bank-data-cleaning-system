import Vue from 'vue'
import App from './App.vue'
import ElementUI, { Upload } from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import VueRouter from 'vue-router'
//import router from '@/router/index.js'

import upload from "./views/upload.vue";

import querydata1 from "./views/querydata1.vue";
import querydata2 from "./views/querydata2.vue";
Vue.use(VueRouter)
Vue.config.productionTip = false
Vue.use(ElementUI);

const router =new VueRouter(
  {
    routes:[
      {path:"/",component:upload},
      
      {path:"/queryData1",component:querydata1},
      {path:"/queryData2",component:querydata2},
    ]
  }
)

new Vue({
  router,
  render: h => h(App),
  
}).$mount('#app')
