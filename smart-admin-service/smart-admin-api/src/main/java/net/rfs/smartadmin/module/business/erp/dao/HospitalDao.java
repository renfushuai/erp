package net.rfs.smartadmin.module.business.erp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.rfs.smartadmin.module.business.erp.domain.dto.HospitalQueryDTO;
import net.rfs.smartadmin.module.business.erp.domain.entity.HospitalEntity;
import net.rfs.smartadmin.module.business.erp.domain.vo.HospitalExcelVO;
import net.rfs.smartadmin.module.business.erp.domain.vo.HospitalVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

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
@Mapper
@Component
public interface HospitalDao extends BaseMapper<HospitalEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return HospitalVO
    */
    IPage<HospitalVO> queryByPage(Page page, @Param("queryDTO") HospitalQueryDTO queryDTO);

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
    List<HospitalExcelVO> queryAllExportData(@Param("queryDTO")HospitalQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<HospitalExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);
}
