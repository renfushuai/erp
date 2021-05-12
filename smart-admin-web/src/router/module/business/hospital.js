import Main from '@/components/main';
// erp_hospital路由
export const hospital = [
    {
        path: '/hospital',
        name: 'Hospital',
        component: Main,
        meta: {
            title: '医院管理',
            icon: 'icon iconfont iconyoujianguanli'
        },
        children: [
            {
                path: '/hospital/hospital-list',
                name: 'HospitalList',
                meta: {
                    title: '医院列表',
                    privilege: [
                        { title: '查询', name: 'hospital-list-query' },
                        { title: '新增', name: 'hospital-list-add' },
                        { title: '编辑', name: 'hospital-list-update' },
                        { title: '批量删除', name: 'hospital-list-batch-delete' },
                        { title: '批量导出', name: 'hospital-list-batch-export' },
                        { title: '导出全部', name: 'hospital-list-export-all' }
                    ]
                },
                component: () => import('@/views/business/hospital/hospital-list.vue')
             }
        ]
    }
];
