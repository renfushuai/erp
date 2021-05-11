package net.rfs.smartadmin.module.business.erp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.rfs.smartadmin.module.business.erp.domain.dto.CompanyQueryDTO;
import net.rfs.smartadmin.module.business.erp.domain.entity.CompanyEntity;
import net.rfs.smartadmin.module.business.erp.domain.vo.CompanyVO;
import net.rfs.smartadmin.module.business.erp.domain.vo.CompanyExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

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
@Mapper
@Component
public interface CompanyDao extends BaseMapper<CompanyEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return CompanyVO
    */
    IPage<CompanyVO> queryByPage(Page page, @Param("queryDTO") CompanyQueryDTO queryDTO);

    /**
     * 根据id删除
     * @param id
     * @return
    */
    void deleteById(@Param("id")Long id);

    /**
     * 根据id批量删除
     * @param idList
     * @return
    */
    void deleteByIdList(@Param("idList") List<Long> idList);

        /**
     * 查询所有导出数据
     * @param queryDTO
     * @return
     */
    List<CompanyExcelVO> queryAllExportData(@Param("queryDTO")CompanyQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<CompanyExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);
}
