import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter);

let router = new VueRouter({
  routes: [
    {
      name: 'Home',
      path: '/home',
      component: () => import('@/views/home'),
      meta: { title: '首页' },
      children:[  {
        name: 'Worker',
        path: '/home/worker',
        component: () => import('@/views/worker'),
        meta: {
            title: '员工管理',
            icon: 'el-icon-user-solid',
            permission: ["admin"]
        },
    }, {
        name: 'Stock',
        path: '/home/stock',
        component: () => import('@/views/stock'),
        meta: {
            title: '库存管理',
            icon: 'el-icon-coin',
            permission: ["user"]
        },
    }, {
        name: 'Client',
        path: '/home/client',
        component: () => import('@/views/client'),
        meta: {
            title: '客户管理',
            icon: 'el-icon-user-solid',
            permission: ["user"]
        },
    }, {
        name: 'Order',
        path: '/home/order',
        component: () => import('@/views/order'),
        meta: {
            title: '订单管理',
            icon: 'el-icon-document-checked',
            permission: ["user"]
        },
    }, {
      name: 'Statistics',
      path: '/home/statistics',
      component: () => import('@/views/statistics'),
      meta: {
          title: '表格统计',
          icon: 'el-icon-data-analysis',
          permission: ["user"]
      },
  },]
    },
  {
    name: 'Login',
    path: '/login',
    component: () => import('@/views/login'),
  },
  {
    name: 'AdminLogin',
    path: '/admin-login',
    component: () => import('@/views/adminlogin'),
  },
 
  {
      path: '/',
      redirect: '/login'
    },
  ]

})

export default router;