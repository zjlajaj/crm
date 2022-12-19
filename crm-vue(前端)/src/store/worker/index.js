import { ApiAddWorker, ApiQueryWorker ,ApiEditWorker, ApiResetWorkerPwd, ApiDeleteWorker} from "../../api";
import { Message } from 'element-ui';
const state = {
    workerList: [],
    workerListPage:{}
};
const mutations = {
    SET_TOKEN: (state, data) => {
        state.token = data;
    },
    SET_WORKERLIST: (state, data) => {
        state.workerList = data;
    },
    SET_WORKERLISTPAGE: (state, data) => {
        state.workerListPage = data;
    },
};
const actions = {

    async AddWorker({ commit }, data) {
        const params = { workAccount: data.workAccount, workAddr: data.workAddr, workName: data.workName, workPwd: data.workPwd, workTel: data.workTel };
        let res = await ApiAddWorker(params);

        if (res.code == 200) {
            Message.success(res.msg);
        } else{
            Message.error(res.msg);
        }
    },
    
   
    async QueryWorkers({ commit }, page) {
        let res = await ApiQueryWorker(page);
        if (res.code == 200) {
            commit('SET_WORKERLIST', res.data.records);
            let page = {current:res.data.current, size:res.data.size, total:res.data.total, pages:res.data.pages};
            commit('SET_WORKERLISTPAGE', page);
        } else {
            Message.error(res.msg);
        }

    },
    async ResetWorkerPwd({ commit }, id){
        let res = await ApiResetWorkerPwd(id);

        if (res.code == 200) {
            Message.success(res.msg);
        } else{
            Message.error(res.msg);
        }
    },
    async DeleteWorker({ commit }, id) {
        let res = await ApiDeleteWorker(id);
        if (res.code == 200) {
            Message.success(res.msg);
        } else{
            Message.error(res.msg);
        }

    },
    async EditWorker({ commit }, data) {

        const params = { id:data.id,workAccount: data.workAccount, workAddr: data.workAddr, workName: data.workName, workPwd: data.workPwd, workTel: data.workTel };
        let res = await ApiEditWorker(params);

        if (res.code == 200) {
            Message.success(res.msg);
        } else{
            Message.error(res.msg);
        }
    },

};
const getters = {
    WorkerList: (state) => state.workerList,
    WorkerListPage:(state) => state.workerListPage,
};
export default {
    state,
    mutations,
    actions,
    getters,
}