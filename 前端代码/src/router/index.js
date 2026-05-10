// 配置路由
import Vue from 'vue';
import VueRouter from 'vue-router';
// 使用插件
Vue.use(VueRouter);
// 配置路由
// 引入路由组件
import Souye from '@/pages/Souye'
import Dierye from '@/pages/Dierye'
import Shujuzhanxian from '@/pages/Shujuzhanxian'
import Login from '@/pages/Login'
import New1 from '@/pages/New1'
import New2 from '@/pages/New2'
import New3 from '@/pages/New3'
import New4 from '@/pages/New4'
import Navagation2 from '@/pages/Dierselection/Navagation2.vue'
import Navagation3 from '@/pages/Dierselection/Navagation3.vue'
import Navagation4 from '@/pages/Dierselection/Navagation4.vue'
import Analysis from '@/pages/Dierselection/Analysis.vue'
import Analysis1 from '@/pages/Dierselection/Analysis1.vue'





export default new VueRouter({
    // 配置路由
    routes: [{
            path: "/dierye",
            component: Dierye,
            meta: {
                keepAlive: false
            },
        }, {
            path: "/login",
            component: Login,
            meta: {
                keepAlive: true
            }
        },
        {
            path: "/Dierselection/Navagation2",
            component: Navagation2,
            meta: {
                keepAlive: false
            }
        },
        {
            path: "/Dierselection/Navagation3",
            component: Navagation3,
            meta: {
                keepAlive: false
            }
        },
        {
            path: "/Dierselection/Navagation4",
            component: Navagation4,
            meta: {
                keepAlive: false
            }
        },
        {
            path: "/Dierselection/Analysis",
            component: Analysis,
            meta: {
                keepAlive: false
            }
        }, {
            path: "/Dierselection/Analysis1",
            component: Analysis1,
            meta: {
                keepAlive: false
            }
        },
        {
            path: "/shujuzhanxian",
            component: Shujuzhanxian,
            meta: {
                keepAlive: false
            }
        },
        {
            path: "/souye",
            component: Souye,
            meta: {
                keepAlive: true
            }
        }, {
            path: "/new1",
            component: New1,
            meta: {
                keepAlive: true
            }
        }, {
            path: "/new2",
            component: New2,
            meta: {
                keepAlive: true
            }
        }, {
            path: "/new3",
            component: New3,
            meta: {
                keepAlive: true
            }
        }, {
            path: "/new4",
            component: New4,
            meta: {
                keepAlive: true
            }
        },
        // 重定向，在项目跑起来的时候，访问/，显示首页
        {
            path: '*',
            redirect: "/login"
        }
    ]
})