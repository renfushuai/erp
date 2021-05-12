import { postAxios, getAxios, postDownloadAxios } from '@/lib/http';

export const companyApi = {
    // 添加公司表 @author 任富帅
    addCompany: (data) => {
        return postAxios('/company/add', data);
    },
    // 分页查询公司表 @author 任富帅
    queryCompany: (data) => {
        return postAxios('/company/page/query', data);
    },
    // 批量删除公司表 @author 任富帅
    batchDeleteCompany: (idList) => {
        return postAxios('/company/deleteByIds', idList);
    },
    // 修改公司表  @author 任富帅
    updateCompany: (data) => {
        return postAxios('/company/update',data);
    },
    // 导出全部  @author 任富帅
    exportAll:(data)=>{
        return postDownloadAxios('/company/export/all',data);
    },
    // 批量导出  @author 任富帅
    batchExport: (idList) => {
        return postDownloadAxios('/company/export/batch', idList);
    },
};
