
import Main from '@/components/main';

import { peony } from './peony';
import { emailSetting } from './email';
import { keepAlive } from './keep-alive';
import { notice } from './notice';
import { threeRouter } from './three-router';
import { company } from './company';
import { product } from './product';
import { hospital } from './hospital';
import { salesOrder } from './sales-order';
import {salesOrderInfo} from './sales-order-info'
// 业务
export const business = [
  {
    path: '/business',
    name: 'Business',
    component: Main,
    meta: {
      title: 'ERP系统',
      topMenu:true,
      icon: 'icon iconfont iconyoujianguanli'
    },
    children: [
      ...peony,
      ...emailSetting,
      ...keepAlive,
      ...notice,
      ...threeRouter,
      ...salesOrder,
      ...salesOrderInfo,
      ...hospital,
      ...company,
      ...product
    ]
  }
];
