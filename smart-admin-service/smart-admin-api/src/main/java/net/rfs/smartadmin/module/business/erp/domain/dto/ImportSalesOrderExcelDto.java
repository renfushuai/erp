package net.rfs.smartadmin.module.business.erp.domain.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
* @author: rfs
* @create: 2021/5/13
* @description: 结算单导入
**/
@Data
public class ImportSalesOrderExcelDto {
    @Excel(name = "结算单")
    private String orderCode;
    @Excel(name = "名称")
    private String productName;
    /** 规格 */
    @Excel(name = "规格")
    private String specifications;
    @Excel(name = "货号")
    private String productNumber;
    /** 数量 */
    @Excel(name = "数量")
    private Integer salesQuantity;
    /** 出货单价 */
    @Excel(name = "出货单价")
    private BigDecimal standardPrice;
    /** 出货金额 */
    @Excel(name = "出货金额")
    private BigDecimal salesPrice;
    /** 实付金额 */
    @Excel(name = "结算金额")
    private BigDecimal actuallyAmount;
    @Excel(name = "去向")
    private String hospitalName;
    @Excel(name = "备注")
    private String memo;
    @Excel(name = "用货日期")
    private Date createTime;
    /** 经办人 */
    @Excel(name = "经办人")
    private String manager;
    /** 1=医院 2=代理商 */
    @Excel(name = "类别")
    private String channelName;
    /** 地区 */
    @Excel(name = "地区")
    private String areaCode;
}
