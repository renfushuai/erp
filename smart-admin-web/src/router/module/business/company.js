import Main from '@/components/main';
// erp_company路由
export const company = [
    {
        path: '/company',
        name: 'Company',
        component: Main,
        meta: {
            title: '公司表管理',
            icon: 'icon iconfont iconyoujianguanli'
        },
        children: [
            //  公司表列表
            {
                path: '/company/company-list',
                name: 'CompanyList',
                meta: {
                    title: '公司表列表',
                    privilege: [
                        { title: '查询', name: 'company-list-query' },
                        { title: '新增', name: 'company-list-add' },
                        { title: '编辑', name: 'company-list-update' },
                        { title: '批量删除', name: 'company-list-batch-delete' },
                        { title: '批量导出', name: 'company-list-batch-export' },
                        { title: '导出全部', name: 'company-list-export-all' }
                    ]
                },
                component: () => import('@/views/business/company/company-list.vue')
             }
        ]
    }
];
