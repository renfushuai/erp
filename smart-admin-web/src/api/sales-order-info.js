import { postAxios, getAxios, postDownloadAxios } from '@/lib/http';

export const salesOrderInfoApi = {
    // 添加 @author 任富帅
    addSalesOrderInfo: (data) => {
        return postAxios('/salesOrderInfo/add', data);
    },
    // 分页查询 @author 任富帅
    querySalesOrderInfo: (data) => {
        return postAxios('/salesOrderInfo/page/query', data);
    },
    // 批量删除 @author 任富帅
    batchDeleteSalesOrderInfo: (idList) => {
        return postAxios('/salesOrderInfo/deleteByIds', idList);
    },
    // 修改  @author 任富帅
    updateSalesOrderInfo: (data) => {
        return postAxios('/salesOrderInfo/update',data);
    },
    // 导出全部  @author 任富帅
    exportAll:(data)=>{
        return postDownloadAxios('/salesOrderInfo/export/all',data);
    },
    // 批量导出  @author 任富帅
    batchExport: (idList) => {
        return postDownloadAxios('/salesOrderInfo/export/batch', idList);
    },
    orderStatistics:(data)=>{
        return postAxios('/salesOrderInfo/orderStatistics',data);
    },
};
