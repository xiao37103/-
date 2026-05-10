// import Vue from 'vue'
// import Vuex from 'vuex'
// Vue.use(Vuex)

// // 用来存储数据
// const state = {

//     }
//     // 响应组件中的事件
// const actions = {

//     }
//     // 操作数据
// const mutations = {

//     }
//     // 用来将state数据进行加工
// const getters = {

//     }
//     // 新建并暴露store
// export default new Vuex.Store({
//     state,
//     actions,
//     mutations,
//     getters
// })
// src/store/index.js
// import createPersistedState from 'vuex-persistedstate'
import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

const store = new Vuex.Store({
    state: {
        message: '登录'
    },
    mutations: {
        // 更新message状态
        updateMessage(state, payload) {
            state.message = payload;
        }
    },

});

export default store;