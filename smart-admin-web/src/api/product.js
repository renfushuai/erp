import { postAxios, getAxios, postDownloadAxios } from '@/lib/http';

export const productApi = {
    groupProduct: () => {
        return getAxios('/product/group');
    },
    // 添加 @author 任富帅
    addProduct: (data) => {
        return postAxios('/product/add', data);
    },
    // 分页查询 @author 任富帅
    queryProduct: (data) => {
        return postAxios('/product/page/query', data);
    },
    // 批量删除 @author 任富帅
    batchDeleteProduct: (idList) => {
        return postAxios('/product/deleteByIds', idList);
    },
    // 修改  @author 任富帅
    updateProduct: (data) => {
        return postAxios('/product/update',data);
    },
    // 导出全部  @author 任富帅
    exportAll:(data)=>{
        return postDownloadAxios('/product/export/all',data);
    },
    // 批量导出  @author 任富帅
    batchExport: (idList) => {
        return postDownloadAxios('/product/export/batch', idList);
    },
};
