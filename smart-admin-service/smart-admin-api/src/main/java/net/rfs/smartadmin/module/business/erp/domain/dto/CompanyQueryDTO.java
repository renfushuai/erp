package net.rfs.smartadmin.module.business.erp.domain.dto;

import net.rfs.smartadmin.common.domain.PageParamDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

/**
 * [  ]
 *
 * @author 卓大
 * @version 1.0
 * @company 任富帅
 * @copyright (c)  任富帅Inc. All rights reserved.
 * @date 2021-05-12 05:37:59
 * @since JDK1.8
 */
@Data
public class CompanyQueryDTO extends PageParamDTO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("name")
    private String name;

    @ApiModelProperty("创建时间-开始")
    private Date createTimeBegin;

    @ApiModelProperty("创建时间-截止")
    private Date createTimeEnd;

    @ApiModelProperty("上次更新时间-开始")
    private Date updateTimeBegin;

    @ApiModelProperty("上次更新创建时间-开始")
    private Date updateTimeEnd;
}
