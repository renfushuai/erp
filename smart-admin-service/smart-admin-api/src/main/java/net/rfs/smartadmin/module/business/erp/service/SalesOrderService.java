package net.rfs.smartadmin.module.business.erp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.rfs.smartadmin.common.domain.PageResultDTO;
import net.rfs.smartadmin.common.domain.ResponseDTO;
import net.rfs.smartadmin.module.business.erp.dao.SalesOrderDao;
import net.rfs.smartadmin.module.business.erp.domain.dto.SalesOrderAddDTO;
import net.rfs.smartadmin.module.business.erp.domain.dto.SalesOrderUpdateDTO;
import net.rfs.smartadmin.module.business.erp.domain.dto.SalesOrderQueryDTO;
import net.rfs.smartadmin.module.business.erp.domain.entity.SalesOrderEntity;
import net.rfs.smartadmin.module.business.erp.domain.vo.SalesOrderVO;
import net.rfs.smartadmin.module.business.erp.domain.vo.SalesOrderExcelVO;
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
 * @date 2021-05-12 14:59:01
 * @since JDK1.8
 */
@Service
public class SalesOrderService {

    @Autowired
    private SalesOrderDao salesOrderDao;

    /**
     * 根据id查询
     */
    public SalesOrderEntity getById(Long id){
        return  salesOrderDao.selectById(id);
    }

    /**
     * 分页查询
     * @author 任富帅
     * @date 2021-05-12 14:59:01
     */
    public ResponseDTO<PageResultDTO<SalesOrderVO>> queryByPage(SalesOrderQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<SalesOrderVO> voList = salesOrderDao.queryByPage(page, queryDTO);
        PageResultDTO<SalesOrderVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author 任富帅
     * @date 2021-05-12 14:59:01
     */
    public ResponseDTO<String> add(SalesOrderAddDTO addDTO) {
        SalesOrderEntity entity = SmartBeanUtil.copy(addDTO, SalesOrderEntity.class);
        salesOrderDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author 任富帅
     * @date 2021-05-12 14:59:01
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(SalesOrderUpdateDTO updateDTO) {
        SalesOrderEntity entity = SmartBeanUtil.copy(updateDTO, SalesOrderEntity.class);
        salesOrderDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author 任富帅
     * @date 2021-05-12 14:59:01
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        salesOrderDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author 任富帅
     * @date 2021-05-12 14:59:01
     */
    public List<SalesOrderExcelVO> queryAllExportData(SalesOrderQueryDTO queryDTO) {
        return salesOrderDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author 任富帅
     * @date 2021-05-12 14:59:01
     */
    public List<SalesOrderExcelVO> queryBatchExportData(List<Long> idList) {
        return salesOrderDao.queryBatchExportData(idList);
    }
}
