import {ApiQueryCustom, ApiAddCustom, ApiEditClient, ApiVagueQuery, ApiSendMail} from "../../api";
import router from '@/router';
import { Message } from 'element-ui';
const state = {
    clientDetail:{},
    clientList:[],
    clientListPage:{},
};
const mutations = {
    // token
    SET_CLIENTLIST:(state,data) => {
        state.clientList = data;
    },
    SET_CLIENTLISTPAGE:(state,data) => {
        state.clientListPage = data;
    },
};
const actions = {
    async editClient({ commit }, data){
        const params = { id: data.pkId, customAddr: data.customAddr, customEmail: data.customEmail, customFrom: data.customFrom, customName: data.customName, customTel: data.customTel, customWork: data.customWork  };
        let res = await ApiEditClient(params);

        if (res.code == 200) {
            Message.success(res.msg);
        } else{
            Message.error(res.msg);
        }
    },
    async getClientList({ commit }, pageNum) {
        let res = await ApiQueryCustom(pageNum);
        if (res.code == 200) {
            commit('SET_CLIENTLIST', res.data.records);
            let page = {current:res.data.current, size:res.data.size, total:res.data.total, pages:res.data.pages};
            commit('SET_CLIENTLISTPAGE', page);
            Message.success(res.msg);
        } else {
            Message.error(res.msg);
        }
    },
    async sendMail({ commit }, data) {
        const params = { receiver:data.reciver,subject:data.title,text:data.xiaoxi };
        let res = await ApiSendMail(params);
        if (res.code == 200) {
            Message.success(res.msg);
        } else{
            Message.error(res.msg);
        }
    },
    async addClient({ commit }, data) {
        let res = await ApiAddCustom(data);
        if (res.code == 200) {
            Message.success(res.msg);
        } else{
            Message.error(res.msg);
        }
    },
    async searchClientList({ commit }, data) {
        let res = await ApiVagueQuery(data);
        if (res.code == 200) {
            commit('SET_CLIENTLIST', res.data.records);
            let page = {current:res.data.current, size:res.data.size, total:res.data.total, pages:res.data.pages};
            commit('SET_CLIENTLISTPAGE', page);
            Message.success(res.msg);
        } else {
            Message.error(res.msg);
        }
    },
};
const getters = {
    ClientDetail: (state) => state.clientDetail,
    ClientList:(state) => state.clientList,
    ClientListPage:(state) => state.clientListPage,
};
export default {
    state,
    mutations,
    actions,
    getters,
}
