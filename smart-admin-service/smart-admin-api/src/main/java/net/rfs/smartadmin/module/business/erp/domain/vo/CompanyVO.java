package net.rfs.smartadmin.module.business.erp.domain.vo;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 *  [  ]
 *
 * @author 卓大
 * @version 1.0
 * @company 任富帅
 * @copyright (c) 任富帅Inc. All rights reserved.
 * @date  2021-05-12 05:37:59
 * @since JDK1.8
 */
@Data
public class CompanyVO {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("name")
    private String name;

    @ApiModelProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty("status")
    private Integer status;



}
