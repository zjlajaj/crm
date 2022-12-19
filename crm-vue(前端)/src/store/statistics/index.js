import { ApiGetTableData } from "../../api";
import { Message } from 'element-ui';
const state = {
    counts: [],
    names: []
};
const mutations = {
    SET_COUNTS: (state, data) => {
        state.counts = data;
    },
    SET_NAMES: (state, data) => {
        state.names = data;
    },

};
const actions = {

    async getTableData({ commit }, type) {

        let res = await ApiGetTableData(type);
        if (res.code == 200) {
            let counts = [];
            let names = [];
            res.data.map((item, index) => {
                counts.push(item.count);
                names.push(item.name);
            })
            commit('SET_COUNTS', counts);
            commit('SET_NAMES', names);
            console.log(counts)
        } else {
            Message.error(res.msg);
        }

    },
};
const getters = {
    Counts: (state) => state.counts,
    Names: (state) => state.names,
};
export default {
    state,
    mutations,
    actions,
    getters,
}