import { ApiGetOrderList,ApiAddNewOrder, ApiExportExcel } from "../../api";
import { Message } from 'element-ui';
const state = {
    orderList: [],
    orderListPage:{}
};
const mutations = {
    SET_ORDERLIST: (state, data) => {
        state.orderList = data;
    },
    SET_OEDERLISTPAGE: (state, data) => {
        state.orderListPage = data;
    },
};
const actions = {
    async exportExcel(){

            let res = ApiExportExcel();
            res.then((data)=>{
                console.log(data);
                let fileSuffix = 'xlsx'
                let fileName = '订单详情';
                let fileTypeMime = '' // 文件 mime 类型，移动端必传，否则下载不成功；pc端可传可不传
                switch (fileSuffix) { // 获取后缀对应的 mime
                  case 'png': fileTypeMime = 'image/png'; break;
                  case 'doc': fileTypeMime = 'application/msword'; break;
                  case 'docx': fileTypeMime = 'application/vnd.openxmlformats-officedocument.wordprocessingml.document'; break;
                  case 'jpg': case 'jpeg': fileTypeMime = 'image/jpeg'; break;
                  case 'gif': fileTypeMime = 'image/gif'; break;
                  case 'svg': fileTypeMime = 'image/svg+xml'; break;
                  case 'tif': case 'tiff': fileTypeMime = 'image/tiff'; break;
                  case 'txt': fileTypeMime = 'text/plain'; break;
                  case 'ppt': fileTypeMime = 'application/vnd.ms-powerpoint'; break;
                  case 'pptx': fileTypeMime = 'application/vnd.openxmlformats-officedocument.presentationml.presentation'; break;
                  case 'xls': fileTypeMime = 'application/vnd.ms-excel'; break;
                  case 'xlsx': fileTypeMime = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'; break;
                  case 'zip': fileTypeMime = 'application/zip'; break;
                  case '7z': fileTypeMime = 'application/x-7z-compressed'; break;
                }
                let blob = window.URL.createObjectURL(new Blob([data], {
                  'type': fileTypeMime
                }))
                let link = document.createElement('a')
                link.style.display = 'none'
                link.href = blob
                link.setAttribute('download', fileName)
                document.body.appendChild(link)
                link.click()
                document.body.removeChild(link) //下载完成移除元素
                window.URL.revokeObjectURL(blob) //释放掉 blob 对象
            })

    },
    async newOrder({ commit }, data) {
        const params = { dealPeople:data.dealPeople,dealState:data.dealState,dealAddr: data.dealAddr, dealCustom: data.dealCustom, dealDetail: data.dealDetail, dealName: data.dealName, dealNum: data.dealNum, dealStockId: data.dealStockId,dealTel: data.dealTel,};
        let res = await ApiAddNewOrder(params);

        if (res.code == 200) {
            Message.success(res.msg);
        } else{
            Message.error(res.msg);
        }
    },
    
   
    async getOrderList({ commit }, page) {
        console.log(page)
        let res = await ApiGetOrderList(page);
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
    OrderList:(state) => state.orderList,
    OrderListPage:(state) => state.orderListPage,
};
export default {
    state,
    mutations,
    actions,
    getters,
}