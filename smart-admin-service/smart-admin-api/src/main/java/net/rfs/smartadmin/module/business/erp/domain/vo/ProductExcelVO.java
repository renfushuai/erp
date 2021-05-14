package net.rfs.smartadmin.module.business.erp.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 *  [  ]
 *
 * @author 任富帅
 * @version 1.0
 * @company rfs
 * @copyright (c) rfsInc. All rights reserved.
 * @date  2021-05-14 15:53:42
 * @since JDK1.8
 */
@Data
public class ProductExcelVO {
    @Excel(name = "id")
    private Long id;

    @Excel(name = "货号")
    private String productNumber;

    @Excel(name = "商品名称")
    private String productName;

    @Excel(name = "规格")
    private String specifications;

    @Excel(name = "库存")
    private Integer stock;

    @Excel(name = "单价")
    private BigDecimal standardPrice;

    @Excel(name = "商品类型1=外科，2=内科 3=器械")
    private Integer productType;

    @Excel(name = "状态")
    private Integer status;

    @Excel(name = "create_time", format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Excel(name = "update_time", format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;



}
