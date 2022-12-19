import request from "./request";

//员工登录接口
export function ApiWorkerLogin(data) {
    return request({
        url: '/worker/login',
        method: 'post',
        data
    })
}
//管理员登录接口
export function ApiAdminLogin(data) {
    return request({
        url: '/admin/login',
        method: 'post',
        data
    })
}
//添加员工
export function ApiAddWorker(data) {
    return request({
        url: '/admin/addWorker',
        method: 'post',
        data
    })
}
//查询员工列表

export function ApiQueryWorker(page) {
    return request({
        url: '/admin/queryWorkers/'+page,
        method: 'get',
    })
}
//更新员工信息
export function ApiEditWorker(data) {
    return request({
        url: '/admin/updateWorker',
        method: 'put',
        data
    })
}
//重置员工密码
export function ApiResetWorkerPwd(id) {
    return request({
        url: '/admin/resetPwd/'+id,
        method: 'put',
    })
}
//删除员工
export function ApiDeleteWorker(id) {
    return request({
        url: '/admin/deleteWorker/'+id,
        method: 'delete',
    })
}

//查询客户列表
export function ApiQueryCustom(page) {
    return request({
        url: '/custom/queryCustom/'+page,
        method: 'get',
    })
}

//新增客户
export function ApiAddCustom(data) {
    return request({
        url: '/custom/addCustom',
        method: 'post',
        data,
    })
}
//修改客户
export function ApiEditClient(data) {
    return request({
        url: '/custom/updateCustom',
        method: 'put',
        data,
    })
}
export function ApiSendMail(data) {
    return request({
        url: '/custom/sendMail',
        method: 'post',
        data,
    })
}
//客户模糊查询
export function ApiVagueQuery(data) {
    return request({
        url: '/custom/vagueQuery',
        method: 'post',
        data,
    })
}
//订单列表
export function ApiGetOrderList(page) {
    return request({
        url: '/deal/queryDeal/'+page,
        method: 'get',
    })
}
//新增订单
export function ApiAddNewOrder(data) {
    return request({
        url: '/deal/addDeal',
        method: 'post',
        data
    })
}
//库存列表

export function ApiGetStockList(pageNum) {
    return request({
        url: '/stock/queryStock/'+pageNum,
        method: 'get',
    })
}

//添加库存
export function ApiAddNewStock(data) {
    return request({
        url: '/stock/addStock',
        method: 'post',
        data
    })
}
//导出Excel

export function ApiExportExcel() {
    return request({
        url: '/deal/getExcel',
        method: 'get',
        responseType: 'bobl'
    })
}
//获取表格数据

export function ApiGetTableData(type) {
    return request({
        url: '/statistics/getInfo/'+type,
        method: 'get',
    })
}
