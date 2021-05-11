package net.rfs.smartadmin.module.business.erp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.rfs.smartadmin.common.domain.PageResultDTO;
import net.rfs.smartadmin.common.domain.ResponseDTO;
import net.rfs.smartadmin.module.business.erp.dao.CompanyDao;
import net.rfs.smartadmin.module.business.erp.domain.dto.CompanyAddDTO;
import net.rfs.smartadmin.module.business.erp.domain.dto.CompanyUpdateDTO;
import net.rfs.smartadmin.module.business.erp.domain.dto.CompanyQueryDTO;
import net.rfs.smartadmin.module.business.erp.domain.entity.CompanyEntity;
import net.rfs.smartadmin.module.business.erp.domain.vo.CompanyVO;
import net.rfs.smartadmin.module.business.erp.domain.vo.CompanyExcelVO;
import net.rfs.smartadmin.util.SmartPageUtil;
import net.rfs.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [  ]
 *
 * @author 卓大
 * @version 1.0
 * @company 任富帅
 * @copyright (c)  任富帅Inc. All rights reserved.
 * @date 2021-05-12 05:37:59
 * @since JDK1.8
 */
@Service
public class CompanyService {

    @Autowired
    private CompanyDao companyDao;

    /**
     * 根据id查询
     */
    public CompanyEntity getById(Long id){
        return  companyDao.selectById(id);
    }

    /**
     * 分页查询
     * @author 卓大
     * @date 2021-05-12 05:37:59
     */
    public ResponseDTO<PageResultDTO<CompanyVO>> queryByPage(CompanyQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<CompanyVO> voList = companyDao.queryByPage(page, queryDTO);
        PageResultDTO<CompanyVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author 卓大
     * @date 2021-05-12 05:37:59
     */
    public ResponseDTO<String> add(CompanyAddDTO addDTO) {
        CompanyEntity entity = SmartBeanUtil.copy(addDTO, CompanyEntity.class);
        companyDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author 卓大
     * @date 2021-05-12 05:37:59
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(CompanyUpdateDTO updateDTO) {
        CompanyEntity entity = SmartBeanUtil.copy(updateDTO, CompanyEntity.class);
        companyDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author 卓大
     * @date 2021-05-12 05:37:59
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        companyDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author 卓大
     * @date 2021-05-12 05:37:59
     */
    public List<CompanyExcelVO> queryAllExportData(CompanyQueryDTO queryDTO) {
        return companyDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author 卓大
     * @date 2021-05-12 05:37:59
     */
    public List<CompanyExcelVO> queryBatchExportData(List<Long> idList) {
        return companyDao.queryBatchExportData(idList);
    }
}
