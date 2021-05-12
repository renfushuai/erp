package net.rfs.smartadmin.module.business.erp.domain.dto;

import lombok.Data;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新建 [  ]
 *
 * @author 任富帅
 * @version 1.0
 * @company rfs
 * @copyright (c) 2018 rfsInc. All rights reserved.
 * @date  2021-05-12 15:00:50
 * @since JDK1.8
 */
@Data
public class SalesOrderInfoAddDTO {
    @ApiModelProperty("销售单号")
    private String orderCode;

    @ApiModelProperty("商品Id")
    private Long productId;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("货号")
    private String productNumber;

    @ApiModelProperty("规格")
    private String specifications;

    @ApiModelProperty("数量")
    private Integer salesQuantity;

    @ApiModelProperty("出货金额")
    private BigDecimal salesPrice;

    @ApiModelProperty("出货单价")
    private Integer standardPrice;


}
