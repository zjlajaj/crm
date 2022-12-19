import Vue from 'vue';
import Vuex from 'vuex';
Vue.use(Vuex);
import user from './user';
import worker from './worker';
import client from './client'
import order from './order'
import stock from './stock';
import statistics from './statistics'
export default new Vuex.Store({
    modules:{
        user,
        worker,
        client,
        order,
        stock,
        statistics
    }  
});