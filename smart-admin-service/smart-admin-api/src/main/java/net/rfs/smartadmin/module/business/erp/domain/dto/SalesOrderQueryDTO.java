package net.rfs.smartadmin.module.business.erp.domain.dto;

import net.rfs.smartadmin.common.domain.PageParamDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.Date;

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
@Data
public class SalesOrderQueryDTO extends PageParamDTO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("经办人")
    private String manager;

    @ApiModelProperty("医院")
    private String hospitalName;

    @ApiModelProperty("订单号")
    private String code;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("创建时间-开始")
    private Date createTimeBegin;

    @ApiModelProperty("创建时间-截止")
    private Date createTimeEnd;

    @ApiModelProperty("上次更新时间-开始")
    private Date updateTimeBegin;

    @ApiModelProperty("上次更新创建时间-开始")
    private Date updateTimeEnd;
}
