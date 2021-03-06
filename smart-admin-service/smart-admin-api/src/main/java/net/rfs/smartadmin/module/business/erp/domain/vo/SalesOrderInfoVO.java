package net.rfs.smartadmin.module.business.erp.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * [  ]
 *
 * @author 任富帅
 * @version 1.0
 * @company rfs
 * @copyright (c) rfsInc. All rights reserved.
 * @date 2021-05-12 15:00:50
 * @since JDK1.8
 */
@Data
public class SalesOrderInfoVO {
    @ApiModelProperty("id")
    private Long id;

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
    /***************订单主表*****************/

    @ApiModelProperty("实付金额")
    private BigDecimal actuallyAmount;

    @ApiModelProperty("订单金额")
    private BigDecimal orderAmount;

    @ApiModelProperty("医院")
    private String hospitalName;

    @ApiModelProperty("医院id")
    private Long hospitalId;

    @ApiModelProperty("1=外科2=内科3=器械")
    private Integer orderType;

    @ApiModelProperty("销售单类型")
    private String orderTypeName;

    @ApiModelProperty("经办人")
    private String manager;

    @ApiModelProperty("渠道1=医院 2=代理商")
    private Integer channel;

    @ApiModelProperty("渠道名称")
    private String channelName;

    @ApiModelProperty("地区编号")
    private String areaCode;

    @ApiModelProperty("地区名称")
    private String areaName;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("备注")
    private String memo;

    @ApiModelProperty("版本")
    private Integer version;

    @ApiModelProperty(name = "订单来源")
    private Integer orderSourceId;
    @ApiModelProperty(name = "订单来源")
    private String orderSourceName;
}
