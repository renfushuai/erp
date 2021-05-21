package net.rfs.smartadmin.module.business.erp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.rfs.smartadmin.module.business.erp.domain.dto.GroupProductOutputDto;
import net.rfs.smartadmin.module.business.erp.domain.dto.ProductQueryDTO;
import net.rfs.smartadmin.module.business.erp.domain.entity.ProductEntity;
import net.rfs.smartadmin.module.business.erp.domain.vo.ProductVO;
import net.rfs.smartadmin.module.business.erp.domain.vo.ProductExcelVO;
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
 * @date 2021-05-14 15:53:42
 * @since JDK1.8
 */
@Mapper
@Component
public interface ProductDao extends BaseMapper<ProductEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ProductVO
    */
    IPage<ProductVO> queryByPage(Page page, @Param("queryDTO") ProductQueryDTO queryDTO);

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
    List<ProductExcelVO> queryAllExportData(@Param("queryDTO")ProductQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ProductExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);
    /**
    * @author: rfs
    * @create: 2021/5/21
    * @description: 聚合所有产品，id逗号分割
    **/
    List<GroupProductOutputDto> groupProduct();
}
