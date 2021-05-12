package net.rfs.smartadmin.module.business.erp.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import java.math.BigDecimal;

/**
 *  [  ]
 *
 * @author 任富帅
 * @version 1.0
 * @company rfs
 * @copyright (c) rfsInc. All rights reserved.
 * @date  2021-05-12 15:00:50
 * @since JDK1.8
 */
@Data
public class SalesOrderInfoExcelVO {
    @Excel(name = "id")
    private Long id;

    @Excel(name = "销售单号")
    private String orderCode;

    @Excel(name = "商品Id")
    private Long productId;

    @Excel(name = "商品名称")
    private String productName;

    @Excel(name = "货号")
    private String productNumber;

    @Excel(name = "规格")
    private String specifications;

    @Excel(name = "数量")
    private Integer salesQuantity;

    @Excel(name = "出货金额")
    private BigDecimal salesPrice;

    @Excel(name = "出货单价")
    private Integer standardPrice;



}
