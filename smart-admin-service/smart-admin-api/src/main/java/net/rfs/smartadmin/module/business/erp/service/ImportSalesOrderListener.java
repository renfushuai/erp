package net.rfs.smartadmin.module.business.erp.service;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.rfs.smartadmin.module.business.erp.dao.SalesOrderDao;
import net.rfs.smartadmin.module.business.erp.dao.SalesOrderInfoDao;
import net.rfs.smartadmin.module.business.erp.domain.dto.ImportSalesOrderExcelDto;
import net.rfs.smartadmin.module.business.erp.domain.entity.SalesOrderEntity;
import net.rfs.smartadmin.module.business.erp.domain.entity.SalesOrderInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Data
@Service
public class ImportSalesOrderListener {
        //extends AnalysisEventListener<ImportSalesOrderExcelDto>
    @Autowired
    private SalesOrderService salesOrderService;
    @Autowired
    private SalesOrderInfoService salesOrderInfoService;
    @Autowired
    private SalesOrderDao salesOrderDao;
    @Autowired
    private SalesOrderInfoDao salesOrderInfoDao;
    private List<ImportSalesOrderExcelDto> dataList = Collections.synchronizedList(new ArrayList<ImportSalesOrderExcelDto>());

    public void save(Integer orderType,String sourceName, String orderTypeName) {
        dataList.forEach(data -> {
            SalesOrderEntity salesOrder = salesOrderService.getByOrderCode(data.getOrderCode());
            SalesOrderInfoEntity salesOrderInfo = new SalesOrderInfoEntity();
            salesOrderInfo.setOrderCode(data.getOrderCode());
            salesOrderInfo.setProductNumber(data.getProductNumber());
            salesOrderInfo.setProductName(data.getProductName());
            salesOrderInfo.setSalesQuantity(data.getSalesQuantity());
            salesOrderInfo.setSpecifications(data.getSpecifications());
            salesOrderInfo.setSalesPrice(data.getSalesPrice());
            salesOrderInfo.setStandardPrice(data.getStandardPrice());
            if (salesOrder == null) {
                salesOrder = new SalesOrderEntity();
                salesOrder.setCode(data.getOrderCode());
                salesOrder.setActuallyAmount(salesOrderInfo.getSalesPrice());
                salesOrder.setOrderAmount(salesOrderInfo.getSalesPrice());
                salesOrder.setHospitalName(data.getHospitalName());
                salesOrder.setOrderType(orderType);
                salesOrder.setOrderTypeName(orderTypeName);
                salesOrder.setManager(data.getManager());
                if (data.getChannelName().contains("医院")) {
                    salesOrder.setChannel(1);
                } else {
                    salesOrder.setChannel(2);
                }
                salesOrder.setChannelName(data.getChannelName());
                salesOrder.setAreaCode(data.getAreaCode());
                salesOrder.setMemo(data.getMemo());
                salesOrder.setVersion(1);
                salesOrder.setCreateTime(data.getCreateTime());
                salesOrder.setOrderSourceName(sourceName);
                salesOrderDao.insert(salesOrder);
            }
            salesOrderInfoDao.insert(salesOrderInfo);
        });
    }

   /* *//**
     * 解析每一条数据的时候都会进这个方法
     *
     * @param data
     * @param context
     *//*
    @Override
    public void invoke(ImportSalesOrderExcelDto data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        dataList.add(data);
    }

    *//**
     * 解析完成的时候也要保存一遍数据库
     *
     * @param context
     *//*
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

        log.info("所有数据解析完成！");
    }*/
}

