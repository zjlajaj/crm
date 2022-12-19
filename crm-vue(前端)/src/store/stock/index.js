import { ApiGetStockList,ApiAddNewStock ,ApiEditNewStock} from "../../api";
import { Message } from 'element-ui';
const state = {
    stockList: [],
    stockListPage:{}
};
const mutations = {
    SET_ORDERLIST: (state, data) => {
        state.stockList = data;
    },
    SET_OEDERLISTPAGE: (state, data) => {
        state.stockListPage = data;
    },
};
const actions = {
    async editStock({ commit }, data) {
        console.log(data)
        const params = { id:data.id,stockLeft:data.stockLeft,stockName:data.stockName,stockOut: data.stockOut, stockPrice: data.stockPrice, stockUnit: data.stockUnit};
        let res = await ApiEditNewStock(params);

        if (res.code == 200) {
            Message.success(res.msg);
        } else{
            Message.error(res.msg);
        }
    },
    async newStock({ commit }, data) {
        console.log(data)
        const params = { stockLeft:data.stockLeft,stockName:data.stockName,stockOut: data.stockOut, stockPrice: data.stockPrice, stockUnit: data.stockUnit};
        let res = await ApiAddNewStock(params);

        if (res.code == 200) {
            Message.success(res.msg);
        } else{
            Message.error(res.msg);
        }
    },
    
   
    async getStockList({ commit }, pageNum) {
        let res = await ApiGetStockList(pageNum);
        if (res.code == 200) {
            commit('SET_ORDERLIST', res.data.records);
            let page = {current:res.data.current, size:res.data.size, total:res.data.total, pages:res.data.pages};
            commit('SET_OEDERLISTPAGE', page);
        } else {
            Message.error(res.msg);
        }

    },

    // async DeleteWorker({ commit }, id) {
    //     let res = await ApiDeleteWorker(id);
    //     if (res.code == 200) {
    //         Message.success(res.msg);
    //     } else{
    //         Message.error(res.msg);
    //     }

    // },
    // async EditWorker({ commit }, data) {

    //     const params = { id:data.id,workAccount: data.workAccount, workAddr: data.workAddr, workName: data.workName, workPwd: data.workPwd, workTel: data.workTel };
    //     let res = await ApiEditWorker(params);

    //     if (res.code == 200) {
    //         Message.success(res.msg);
    //     } else{
    //         Message.error(res.msg);
    //     }
    // },

};
const getters = {
    StockList:(state) => state.stockList,
    StockListPage:(state) => state.stockListPage,
};
export default {
    state,
    mutations,
    actions,
    getters,
}