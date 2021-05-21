package net.rfs.smartadmin.module.business.erp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.rfs.smartadmin.module.business.erp.domain.dto.OrderStatisticsInputDto;
import net.rfs.smartadmin.module.business.erp.domain.dto.OrderStatisticsOutputDto;
import net.rfs.smartadmin.module.business.erp.domain.dto.SalesOrderInfoQueryDTO;
import net.rfs.smartadmin.module.business.erp.domain.entity.SalesOrderInfoEntity;
import net.rfs.smartadmin.module.business.erp.domain.vo.SalesOrderInfoVO;
import net.rfs.smartadmin.module.business.erp.domain.vo.SalesOrderInfoExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

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
@Mapper
@Component
public interface SalesOrderInfoDao extends BaseMapper<SalesOrderInfoEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return SalesOrderInfoVO
    */
    IPage<SalesOrderInfoVO> queryByPage(Page page, @Param("queryDTO") SalesOrderInfoQueryDTO queryDTO);

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
    List<SalesOrderInfoExcelVO> queryAllExportData(@Param("queryDTO")SalesOrderInfoQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<SalesOrderInfoExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    List<Object> orderStatistics(OrderStatisticsInputDto dto);
}
