package net.rfs.smartadmin.module.business.erp.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.rfs.smartadmin.common.domain.PageResultDTO;
import net.rfs.smartadmin.common.domain.ResponseDTO;
import net.rfs.smartadmin.module.business.erp.dao.HospitalDao;
import net.rfs.smartadmin.module.business.erp.dao.ProductDao;
import net.rfs.smartadmin.module.business.erp.dao.SalesOrderDao;
import net.rfs.smartadmin.module.business.erp.dao.SalesOrderInfoDao;
import net.rfs.smartadmin.module.business.erp.domain.dto.ImportSalesOrderExcelDto;
import net.rfs.smartadmin.module.business.erp.domain.dto.SalesOrderAddDTO;
import net.rfs.smartadmin.module.business.erp.domain.dto.SalesOrderQueryDTO;
import net.rfs.smartadmin.module.business.erp.domain.dto.SalesOrderUpdateDTO;
import net.rfs.smartadmin.module.business.erp.domain.entity.HospitalEntity;
import net.rfs.smartadmin.module.business.erp.domain.entity.ProductEntity;
import net.rfs.smartadmin.module.business.erp.domain.entity.SalesOrderEntity;
import net.rfs.smartadmin.module.business.erp.domain.entity.SalesOrderInfoEntity;
import net.rfs.smartadmin.module.business.erp.domain.vo.CompanyVO;
import net.rfs.smartadmin.module.business.erp.domain.vo.SalesOrderExcelVO;
import net.rfs.smartadmin.module.business.erp.domain.vo.SalesOrderVO;
import net.rfs.smartadmin.util.SmartBeanUtil;
import net.rfs.smartadmin.util.SmartPageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    @Autowired
    private SalesOrderInfoDao salesOrderInfoDao;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private HospitalDao hospitalDao;
    @Autowired
    private ProductDao productDao;

    /**
     * 根据id查询
     */
    public SalesOrderEntity getById(Long id) {
        return salesOrderDao.selectById(id);
    }

    public SalesOrderEntity getByOrderCode(String orderCode) {
        return salesOrderDao.selectOne(Wrappers.<SalesOrderEntity>lambdaQuery()
                .eq(SalesOrderEntity::getCode, orderCode));
    }

    /**
     * 分页查询
     *
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
     *
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
     *
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
     *
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
     *
     * @author 任富帅
     * @date 2021-05-12 14:59:01
     */
    public List<SalesOrderExcelVO> queryAllExportData(SalesOrderQueryDTO queryDTO) {
        return salesOrderDao.queryAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     *
     * @author 任富帅
     * @date 2021-05-12 14:59:01
     */
    public List<SalesOrderExcelVO> queryBatchExportData(List<Long> idList) {
        return salesOrderDao.queryBatchExportData(idList);
    }
    @Transactional(rollbackFor = Exception.class)
    public void uploadSalesOrder(List<ImportSalesOrderExcelDto> dataList, Integer orderType, Integer sourceId, String orderTypeName) {
        List<CompanyVO> companyList = companyService.getAll().getData();
        for (ImportSalesOrderExcelDto data : dataList) {
            if (StringUtils.isBlank(data.getOrderCode())&&StringUtils.isBlank(data.getProductName())) {
                continue;
            }
            // 判断销售单主表是否存在
            SalesOrderEntity salesOrder = getByOrderCode(data.getOrderCode());
            SalesOrderInfoEntity salesOrderInfo = new SalesOrderInfoEntity();
            // 判断商品是否存在
            LambdaQueryWrapper lambda3 = Wrappers.<ProductEntity>lambdaQuery()
                    .eq(ProductEntity::getProductName, data.getProductName().trim());
            ProductEntity productEntity = productDao.selectOne(lambda3);
            if (productEntity == null) {
                productEntity = new ProductEntity();
                productEntity.setProductName(data.getProductName().trim());
                productEntity.setProductNumber(data.getProductNumber());
                productEntity.setProductType(orderType);
                productEntity.setStatus(1);
                productEntity.setSpecifications(data.getSpecifications());
                productEntity.setStandardPrice(data.getStandardPrice());
                productEntity.setStock(10000);
                productEntity.setCreateTime(new Date());
                productEntity.setUpdateTime(new Date());
                productDao.insert(productEntity);
                productEntity = productDao.selectOne(lambda3);
            }
            salesOrderInfo.setOrderCode(data.getOrderCode());
            salesOrderInfo.setProductId(productEntity.getId());
            salesOrderInfo.setProductNumber(data.getProductNumber());
            salesOrderInfo.setProductName(data.getProductName());
            salesOrderInfo.setSalesQuantity(data.getSalesQuantity());
            salesOrderInfo.setSpecifications(data.getSpecifications());
            salesOrderInfo.setSalesPrice(data.getSalesPrice());
            salesOrderInfo.setStandardPrice(data.getStandardPrice());
            HospitalEntity hospitalModel = hospitalDao.selectOne(Wrappers.<HospitalEntity>lambdaQuery().eq(HospitalEntity::getName, data.getHospitalName()));
            if (salesOrder == null) {
                // 判断医院是否存在
                if (StringUtils.isNotBlank(data.getHospitalName()) && hospitalModel == null) {
                    HospitalEntity hospitalEntity = new HospitalEntity();
                    hospitalEntity.setCreateTime(new Date());
                    hospitalEntity.setUpdateTime(new Date());
                    hospitalEntity.setName(data.getHospitalName());
                    hospitalEntity.setStatus(1);
                    hospitalEntity.setAreaCode(data.getAreaCode());
                    hospitalDao.insert(hospitalEntity);
                    hospitalModel = hospitalDao.selectOne(Wrappers.<HospitalEntity>lambdaQuery().eq(HospitalEntity::getName, data.getHospitalName()));
                }

                salesOrder = new SalesOrderEntity();
                salesOrder.setCode(data.getOrderCode());
                salesOrder.setActuallyAmount(salesOrderInfo.getSalesPrice());
                salesOrder.setOrderAmount(salesOrderInfo.getSalesPrice());
                salesOrder.setHospitalName(data.getHospitalName());
                salesOrder.setHospitalId(hospitalModel.getId());
                salesOrder.setOrderType(orderType);
                salesOrder.setOrderTypeName(orderTypeName);
                salesOrder.setManager(data.getManager());
                if (data.getChannelName() != null && data.getChannelName().contains("医院")) {
                    salesOrder.setChannel(1);
                } else {
                    salesOrder.setChannel(2);
                }
                salesOrder.setChannelName(data.getChannelName());
                salesOrder.setAreaCode(data.getAreaCode());
                salesOrder.setMemo(data.getMemo());
                salesOrder.setVersion(1);
                salesOrder.setCreateTime(data.getCreateTime());
                salesOrder.setOrderSourceName(companyList.stream().filter(m -> m.getId().equals(Long.valueOf(sourceId))).findFirst().get().getName());
                salesOrder.setOrderSourceId(sourceId);
                salesOrderDao.insert(salesOrder);


            }
            salesOrderInfoDao.insert(salesOrderInfo);
        }
    }

}
