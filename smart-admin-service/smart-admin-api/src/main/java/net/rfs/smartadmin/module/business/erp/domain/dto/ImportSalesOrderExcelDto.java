package net.rfs.smartadmin.module.business.erp.domain.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ImportSalesOrderExcelDto {
    @Excel(name = "123")
    private String orderCode;
    @Excel(name = "123")
    private String productName;
    /** 规格 */
    @Excel(name = "123")
    private String specifications;
    @Excel(name = "123")
    private String productNumber;
    /** 数量 */
    @Excel(name = "123")
    private Integer salesQuantity;
    /** 出货单价 */
    @Excel(name = "123")
    private Integer standardPrice;
    /** 出货金额 */
    @Excel(name = "123")
    private BigDecimal salesPrice;
    /** 实付金额 */
    @Excel(name = "123")
    private BigDecimal actuallyAmount;
    @Excel(name = "123")
    private String hospitalName;
    @Excel(name = "123")
    private String memo;
    @Excel(name = "123")
    private Date createTime;
    /** 经办人 */
    @Excel(name = "123")
    private String manager;
    /** 1=医院 2=代理商 */
    @Excel(name = "123")
    private String channelName;
    /** 地区 */
    @Excel(name = "123")
    private String areaCode;
}
