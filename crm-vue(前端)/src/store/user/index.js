import { ApiAdminLogin, ApiWorkerLogin } from "../../api";
import router from '@/router';
import { Message } from 'element-ui';
const state = {
    token: '',
    workerList:[],
    userRole:"user"
};

const mutations = {
    SET_TOKEN: (state, data) => {
        state.token = data;
    },
    SET_USERROLE: (state, data) => {
        state.userId = data;
    },

};
const actions = {
    async WorkerLogin({ commit }, data) {
        // console.log(data)
        const params = { username: data.username, password: data.password };
        let res = await ApiWorkerLogin(params);
        if (res.code == 200) {
            commit('SET_TOKEN', res.data);
            window.localStorage.setItem('token', res.data)
            router.push('/home');
            Message.success(res.msg);
        } else if (res.code == 400) {
            Message.error(res.msg);
        }
        commit('SET_USERROLE', "user");
        sessionStorage.setItem("role", "user");
    },
    async AdminLogin({ commit }, data) {
        // console.log(data)
        const params = { username: data.username, password: data.password };
        let res = await ApiAdminLogin(params);
            if (res.code == 200) {
                commit('SET_TOKEN', res.data);
                window.localStorage.setItem('token', res.data)
                router.push('/home');
                Message.success(res.msg);
            } else if (res.data.code == 400) {
                Message.error(res.msg);
            }
            commit('SET_USERROLE', "admin");
            sessionStorage.setItem("role", "admin");
    },

};
const getters = {
    Token: (state) => state.token,
    UserRole: (state) => state.userRole,
};
export default {
    state,
    mutations,
    actions,
    getters,
}