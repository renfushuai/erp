package net.rfs.smartadmin.module.business.erp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.rfs.smartadmin.common.domain.PageResultDTO;
import net.rfs.smartadmin.common.domain.ResponseDTO;
import net.rfs.smartadmin.module.business.erp.dao.SalesOrderInfoDao;
import net.rfs.smartadmin.module.business.erp.domain.dto.SalesOrderInfoAddDTO;
import net.rfs.smartadmin.module.business.erp.domain.dto.SalesOrderInfoUpdateDTO;
import net.rfs.smartadmin.module.business.erp.domain.dto.SalesOrderInfoQueryDTO;
import net.rfs.smartadmin.module.business.erp.domain.entity.SalesOrderInfoEntity;
import net.rfs.smartadmin.module.business.erp.domain.vo.SalesOrderInfoVO;
import net.rfs.smartadmin.module.business.erp.domain.vo.SalesOrderInfoExcelVO;
import net.rfs.smartadmin.util.SmartPageUtil;
import net.rfs.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [  ]
 *
 * @author 任富帅
 * @version 1.0
 * @company rfs
 * @copyright (c)  rfsInc. All rights reserved.
 * @date 2021-05-12 15:00:50
 * @since JDK1.8
 */
@Service
public class SalesOrderInfoService {

    @Autowired
    private SalesOrderInfoDao salesOrderInfoDao;

    /**
     * 根据id查询
     */
    public SalesOrderInfoEntity getById(Long id){
        return  salesOrderInfoDao.selectById(id);
    }

    /**
     * 分页查询
     * @author 任富帅
     * @date 2021-05-12 15:00:50
     */
    public ResponseDTO<PageResultDTO<SalesOrderInfoVO>> queryByPage(SalesOrderInfoQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<SalesOrderInfoVO> voList = salesOrderInfoDao.queryByPage(page, queryDTO);
        PageResultDTO<SalesOrderInfoVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author 任富帅
     * @date 2021-05-12 15:00:50
     */
    public ResponseDTO<String> add(SalesOrderInfoAddDTO addDTO) {
        SalesOrderInfoEntity entity = SmartBeanUtil.copy(addDTO, SalesOrderInfoEntity.class);
        salesOrderInfoDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author 任富帅
     * @date 2021-05-12 15:00:50
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(SalesOrderInfoUpdateDTO updateDTO) {
        SalesOrderInfoEntity entity = SmartBeanUtil.copy(updateDTO, SalesOrderInfoEntity.class);
        salesOrderInfoDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author 任富帅
     * @date 2021-05-12 15:00:50
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        salesOrderInfoDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author 任富帅
     * @date 2021-05-12 15:00:50
     */
    public List<SalesOrderInfoExcelVO> queryAllExportData(SalesOrderInfoQueryDTO queryDTO) {
        return salesOrderInfoDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author 任富帅
     * @date 2021-05-12 15:00:50
     */
    public List<SalesOrderInfoExcelVO> queryBatchExportData(List<Long> idList) {
        return salesOrderInfoDao.queryBatchExportData(idList);
    }
}
