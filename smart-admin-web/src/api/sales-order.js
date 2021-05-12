import { postAxios, getAxios, postDownloadAxios } from '@/lib/http';

export const salesOrderApi = {
    // 添加 @author 任富帅
    addSalesOrder: (data) => {
        return postAxios('/salesOrder/add', data);
    },
    // 分页查询 @author 任富帅
    querySalesOrder: (data) => {
        return postAxios('/salesOrder/page/query', data);
    },
    // 批量删除 @author 任富帅
    batchDeleteSalesOrder: (idList) => {
        return postAxios('/salesOrder/deleteByIds', idList);
    },
    // 修改  @author 任富帅
    updateSalesOrder: (data) => {
        return postAxios('/salesOrder/update',data);
    },
    // 导出全部  @author 任富帅
    exportAll:(data)=>{
        return postDownloadAxios('/salesOrder/export/all',data);
    },
    // 批量导出  @author 任富帅
    batchExport: (idList) => {
        return postDownloadAxios('/salesOrder/export/batch', idList);
    },
};
