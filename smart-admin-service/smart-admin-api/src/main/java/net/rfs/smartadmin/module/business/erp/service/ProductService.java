package net.rfs.smartadmin.module.business.erp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.rfs.smartadmin.common.domain.PageResultDTO;
import net.rfs.smartadmin.common.domain.ResponseDTO;
import net.rfs.smartadmin.module.business.erp.dao.ProductDao;
import net.rfs.smartadmin.module.business.erp.domain.dto.ProductAddDTO;
import net.rfs.smartadmin.module.business.erp.domain.dto.ProductUpdateDTO;
import net.rfs.smartadmin.module.business.erp.domain.dto.ProductQueryDTO;
import net.rfs.smartadmin.module.business.erp.domain.entity.ProductEntity;
import net.rfs.smartadmin.module.business.erp.domain.vo.ProductVO;
import net.rfs.smartadmin.module.business.erp.domain.vo.ProductExcelVO;
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
 * @date 2021-05-14 15:53:42
 * @since JDK1.8
 */
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    /**
     * 根据id查询
     */
    public ProductEntity getById(Long id){
        return  productDao.selectById(id);
    }

    /**
     * 分页查询
     * @author 任富帅
     * @date 2021-05-14 15:53:42
     */
    public ResponseDTO<PageResultDTO<ProductVO>> queryByPage(ProductQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ProductVO> voList = productDao.queryByPage(page, queryDTO);
        PageResultDTO<ProductVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author 任富帅
     * @date 2021-05-14 15:53:42
     */
    public ResponseDTO<String> add(ProductAddDTO addDTO) {
        ProductEntity entity = SmartBeanUtil.copy(addDTO, ProductEntity.class);
        productDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author 任富帅
     * @date 2021-05-14 15:53:42
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ProductUpdateDTO updateDTO) {
        ProductEntity entity = SmartBeanUtil.copy(updateDTO, ProductEntity.class);
        productDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author 任富帅
     * @date 2021-05-14 15:53:42
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        productDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author 任富帅
     * @date 2021-05-14 15:53:42
     */
    public List<ProductExcelVO> queryAllExportData(ProductQueryDTO queryDTO) {
        return productDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author 任富帅
     * @date 2021-05-14 15:53:42
     */
    public List<ProductExcelVO> queryBatchExportData(List<Long> idList) {
        return productDao.queryBatchExportData(idList);
    }
}
