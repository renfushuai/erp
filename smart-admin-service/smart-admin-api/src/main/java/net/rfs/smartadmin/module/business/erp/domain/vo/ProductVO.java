package net.rfs.smartadmin.module.business.erp.domain.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

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
public class ProductVO {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("货号")
    private String productNumber;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("规格")
    private String specifications;

    @ApiModelProperty("库存")
    private Integer stock;

    @ApiModelProperty("单价")
    private BigDecimal standardPrice;

    @ApiModelProperty("商品类型1=外科，2=内科 3=器械")
    private Integer productType;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;



}
