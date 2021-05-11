package net.rfs.smartadmin.module.business.erp.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import java.util.Date;

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
public class CompanyExcelVO {
    @Excel(name = "id")
    private Long id;

    @Excel(name = "name")
    private String name;

    @Excel(name = "create_time", format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Excel(name = "status")
    private Integer status;



}
