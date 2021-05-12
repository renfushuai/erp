import { postAxios, getAxios, postDownloadAxios } from '@/lib/http';

export const hospitalApi = {
    // 添加医院 @author 任富帅
    addHospital: (data) => {
        return postAxios('/hospital/add', data);
    },
    // 分页查询医院 @author 任富帅
    queryHospital: (data) => {
        return postAxios('/hospital/page/query', data);
    },
    // 批量删除医院 @author 任富帅
    batchDeleteHospital: (idList) => {
        return postAxios('/hospital/deleteByIds', idList);
    },
    // 修改医院  @author 任富帅
    updateHospital: (data) => {
        return postAxios('/hospital/update',data);
    },
    // 导出全部  @author 任富帅
    exportAll:(data)=>{
        return postDownloadAxios('/hospital/export/all',data);
    },
    // 批量导出  @author 任富帅
    batchExport: (idList) => {
        return postDownloadAxios('/hospital/export/batch', idList);
    },
};
