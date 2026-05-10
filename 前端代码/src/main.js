import Vue from 'vue';
import App from './App.vue';
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import dataV from '@jiaminghi/data-view';
Vue.use(dataV);
// 引入路由
import router from '@/router';
// 高德地图
import VueAMap from '@vuemap/vue-amap';
import '@vuemap/vue-amap/dist/style.css'
Vue.use(VueAMap);

VueAMap.initAMapApiLoader({
    key: '83ffb61a50076602733441db0331b179',
    plugin: ['AMap.MouseTool', 'AMap.PolyEditor', 'AMap.ToolBar', 'AMap.MapType', 'AMap.PlaceSearch', 'AMap.Geolocation', 'AMap.Geocoder', 'AMap.DistrictSearch', 'AMap.InfoWindow'],
    v: '1.4.4',
    uiVersion: '1.0.11'

});
import VueAmapThree from '@vuemap/vue-amap/dist/three'
Vue.use(VueAmapThree);
// 引入jquery
import $ from 'jquery'
// 跨域问题

import axios from 'axios' // 安装axios后引入
Vue.prototype.$axios = axios
    // Vue.prototype.$axios = axios
    // axios.defaults.baseURL = '/api'



Vue.use(ElementUI);

Vue.config.productionTip = false

new Vue({
    components: {},
    render: h => h(App),
    // 注册路由kv一致省略v
    // 注册路由信息：这里书写router的时候，组件身上都拥有$route,$router属性
    router,
    store,
    $
}).$mount('#app')