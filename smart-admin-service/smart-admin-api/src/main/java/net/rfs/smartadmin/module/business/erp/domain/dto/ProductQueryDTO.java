package net.rfs.smartadmin.module.business.erp.domain.dto;

import net.rfs.smartadmin.common.domain.PageParamDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

/**
 * [  ]
 *
 * @author 任富帅
 * @version 1.0
 * @company rfs
 * @copyright (c)  rfsInc. All rights reserved.
 * @date 2021-05-14 15:53:42
 * @since JDK1.8
 */
@Data
public class ProductQueryDTO extends PageParamDTO {

    @ApiModelProperty("规格")
    private String specifications;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("货号")
    private String productNumber;

    @ApiModelProperty("创建时间-开始")
    private Date createTimeBegin;

    @ApiModelProperty("创建时间-截止")
    private Date createTimeEnd;

    @ApiModelProperty("上次更新时间-开始")
    private Date updateTimeBegin;

    @ApiModelProperty("上次更新创建时间-开始")
    private Date updateTimeEnd;
}
