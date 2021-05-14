import Main from '@/components/main';
// erp_product路由
export const product = [
    {
        path: '/product',
        name: 'Product',
        component: Main,
        meta: {
            title: '产品管理',
            icon: 'icon iconfont iconyoujianguanli'
        },
        children: [
            //  列表
            {
                path: '/product/product-list',
                name: 'ProductList',
                meta: {
                    title: '产品列表',
                    icon: 'icon iconfont iconyoujianguanli',
                    privilege: [
                        { title: '查询', name: 'product-list-query' },
                        { title: '新增', name: 'product-list-add' },
                        { title: '编辑', name: 'product-list-update' },
                        { title: '批量删除', name: 'product-list-batch-delete' },
                        { title: '批量导出', name: 'product-list-batch-export' },
                        { title: '导出全部', name: 'product-list-export-all' }
                    ]
                },
                component: () => import('@/views/business/product/product-list.vue')
             }
        ]
    }
];
