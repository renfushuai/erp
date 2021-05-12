package net.rfs.smartadmin.module.business.erp.domain.dto;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新建 [ 医院 ]
 *
 * @author 任富帅
 * @version 1.0
 * @company rfs
 * @copyright (c) 2018 rfsInc. All rights reserved.
 * @date  2021-05-12 14:41:23
 * @since JDK1.8
 */
@Data
public class HospitalAddDTO {
    @ApiModelProperty("医院名称")
    private String name;

    @ApiModelProperty("地区编号")
    private String areaCode;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


}
