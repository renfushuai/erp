package net.rfs.smartadmin.module.business.erp.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 *  [  ]
 *
 * @author 任富帅
 * @version 1.0
 * @company rfs
 * @copyright (c) rfsInc. All rights reserved.
 * @date  2021-05-12 14:59:01
 * @since JDK1.8
 */
@Data
public class SalesOrderExcelVO {
    @Excel(name = "id")
    private Long id;

    @Excel(name = "订单号")
    private String code;

    @Excel(name = "实付金额")
    private BigDecimal actuallyAmount;

    @Excel(name = "订单金额")
    private BigDecimal orderAmount;

    @Excel(name = "医院")
    private String hospitalName;

    @Excel(name = "医院id")
    private Long hospitalId;

    @Excel(name = "1=外科2=内科3=器械")
    private Integer orderType;

    @Excel(name = "销售单类型")
    private String orderTypeName;

    @Excel(name = "经办人")
    private String manager;

    @Excel(name = "渠道1=医院 2=代理商")
    private Integer channel;

    @Excel(name = "渠道名称")
    private String channelName;

    @Excel(name = "地区编号")
    private String areaCode;

    @Excel(name = "地区名称")
    private String areaName;

    @Excel(name = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Excel(name = "备注")
    private String memo;

    @Excel(name = "版本")
    private Integer version;

    @Excel(name = "订单来源")
    private Integer order_source_id;
    @Excel(name = "订单来源")
    private String order_source_name;
}
