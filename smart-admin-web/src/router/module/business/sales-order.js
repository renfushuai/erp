import Main from '@/components/main';
// erp_sales_order路由
export const salesOrder = [
    {
        path: '/sales-order',
        name: 'SalesOrder',
        component: Main,
        meta: {
            title: '销售单管理',
            icon: 'icon iconfont iconyoujianguanli'
        },
        children: [
            //  列表
            {
                path: '/sales-order/sales-order-list',
                name: 'SalesOrderList',
                meta: {
                    title: '销售单管理',
                    privilege: [
                        { title: '查询', name: 'sales-order-list-query' },
                        { title: '新增', name: 'sales-order-list-add' },
                        { title: '编辑', name: 'sales-order-list-update' },
                        { title: '批量删除', name: 'sales-order-list-batch-delete' },
                        { title: '批量导出', name: 'sales-order-list-batch-export' },
                        { title: '导出全部', name: 'sales-order-list-export-all' }
                    ]
                },
                component: () => import('@/views/business/sales-order/sales-order-list.vue')
             }
        ]
    }
];
