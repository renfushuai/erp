package net.rfs.smartadmin.module.business.erp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.rfs.smartadmin.module.business.erp.domain.dto.SalesOrderQueryDTO;
import net.rfs.smartadmin.module.business.erp.domain.entity.SalesOrderEntity;
import net.rfs.smartadmin.module.business.erp.domain.vo.SalesOrderVO;
import net.rfs.smartadmin.module.business.erp.domain.vo.SalesOrderExcelVO;
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
 * @date 2021-05-12 14:59:01
 * @since JDK1.8
 */
@Mapper
@Component
public interface SalesOrderDao extends BaseMapper<SalesOrderEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return SalesOrderVO
    */
    IPage<SalesOrderVO> queryByPage(Page page, @Param("queryDTO") SalesOrderQueryDTO queryDTO);

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
    List<SalesOrderExcelVO> queryAllExportData(@Param("queryDTO")SalesOrderQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<SalesOrderExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);
}
