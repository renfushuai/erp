package net.rfs.smartadmin.module.business.erp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.rfs.smartadmin.common.domain.PageResultDTO;
import net.rfs.smartadmin.common.domain.ResponseDTO;
import net.rfs.smartadmin.module.business.erp.dao.HospitalDao;
import net.rfs.smartadmin.module.business.erp.domain.dto.HospitalAddDTO;
import net.rfs.smartadmin.module.business.erp.domain.dto.HospitalUpdateDTO;
import net.rfs.smartadmin.module.business.erp.domain.dto.HospitalQueryDTO;
import net.rfs.smartadmin.module.business.erp.domain.entity.HospitalEntity;
import net.rfs.smartadmin.module.business.erp.domain.vo.HospitalVO;
import net.rfs.smartadmin.module.business.erp.domain.vo.HospitalExcelVO;
import net.rfs.smartadmin.util.SmartPageUtil;
import net.rfs.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 医院 ]
 *
 * @author 任富帅
 * @version 1.0
 * @company rfs
 * @copyright (c)  rfsInc. All rights reserved.
 * @date 2021-05-12 14:41:23
 * @since JDK1.8
 */
@Service
public class HospitalService {

    @Autowired
    private HospitalDao hospitalDao;

    /**
     * 根据id查询
     */
    public HospitalEntity getById(Long id){
        return  hospitalDao.selectById(id);
    }

    /**
     * 分页查询
     * @author 任富帅
     * @date 2021-05-12 14:41:23
     */
    public ResponseDTO<PageResultDTO<HospitalVO>> queryByPage(HospitalQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<HospitalVO> voList = hospitalDao.queryByPage(page, queryDTO);
        PageResultDTO<HospitalVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author 任富帅
     * @date 2021-05-12 14:41:23
     */
    public ResponseDTO<String> add(HospitalAddDTO addDTO) {
        HospitalEntity entity = SmartBeanUtil.copy(addDTO, HospitalEntity.class);
        hospitalDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author 任富帅
     * @date 2021-05-12 14:41:23
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(HospitalUpdateDTO updateDTO) {
        HospitalEntity entity = SmartBeanUtil.copy(updateDTO, HospitalEntity.class);
        hospitalDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author 任富帅
     * @date 2021-05-12 14:41:23
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        hospitalDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author 任富帅
     * @date 2021-05-12 14:41:23
     */
    public List<HospitalExcelVO> queryAllExportData(HospitalQueryDTO queryDTO) {
        return hospitalDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author 任富帅
     * @date 2021-05-12 14:41:23
     */
    public List<HospitalExcelVO> queryBatchExportData(List<Long> idList) {
        return hospitalDao.queryBatchExportData(idList);
    }
}
