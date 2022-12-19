//二次封装Axios
import axios from "axios"
import store from '@/store';
import { Message } from 'element-ui';
//利用axios对象的方法create,创建实例
const request = axios.create({
    baseURL: "http://127.0.0.1:7400//",
    timeout: 5000,
    headers: {
        'Content-Type': 'application/json'
        },

})
//请求拦截器
//再发送请求之前，作出一些逻辑
request.interceptors.request.use((config) => {
    // congig:配置对象，对象里面有headers请求头
    if (config.url == '/worker/login' || config.url == '/admin/login') {
        return config;
    }
    if(config.url == '/deal/getExcel'){
        config.responseType = 'blob';
    }
    let token = window.localStorage.getItem('token');
    if (token) {
        config.headers.token = token;
        return config;
    } {
        Message.error('token错误，请重新登陆')
        return Promise.reject(new Error('token错误，请重新登陆'));
    }
})

//响应拦截器
request.interceptors.response.use((res) => {

    return res.data;
}, (error) => {
    Message.error(error)
    return Promise.reject(new Error('faile'));
})

export default request;
