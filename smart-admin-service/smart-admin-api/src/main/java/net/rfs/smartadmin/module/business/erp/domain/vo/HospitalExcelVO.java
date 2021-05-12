package net.rfs.smartadmin.module.business.erp.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import java.util.Date;

/**
 *  [ 医院 ]
 *
 * @author 任富帅
 * @version 1.0
 * @company rfs
 * @copyright (c) rfsInc. All rights reserved.
 * @date  2021-05-12 14:41:23
 * @since JDK1.8
 */
@Data
public class HospitalExcelVO {
    @Excel(name = "id")
    private Long id;

    @Excel(name = "医院名称")
    private String name;

    @Excel(name = "地区编号")
    private String areaCode;

    @Excel(name = "状态")
    private Integer status;

    @Excel(name = "更新时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Excel(name = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;



}
