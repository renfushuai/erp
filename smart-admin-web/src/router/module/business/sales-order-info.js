import Main from '@/components/main';
// erp_sales_order_info路由
export const salesOrderInfo = [
    {
        path: '/sales-order-info',
        name: 'SalesOrderInfo',
        component: Main,
        meta: {
            title: '销售单商品管理',
            icon: 'icon iconfont iconyoujianguanli'
        },
        children: [
            //  列表
            {
                path: '/sales-order-info/sales-order-info-list',
                name: 'SalesOrderInfoList',
                meta: {
                    title: '销售单商品管理',
                    privilege: [
                        { title: '查询', name: 'sales-order-info-list-query' },
                        { title: '新增', name: 'sales-order-info-list-add' },
                        { title: '编辑', name: 'sales-order-info-list-update' },
                        { title: '批量删除', name: 'sales-order-info-list-batch-delete' },
                        { title: '批量导出', name: 'sales-order-info-list-batch-export' },
                        { title: '导出全部', name: 'sales-order-info-list-export-all' }
                    ]
                },
                component: () => import('@/views/business/sales-order-info/sales-order-info-list.vue')
             }
        ]
    }
];
