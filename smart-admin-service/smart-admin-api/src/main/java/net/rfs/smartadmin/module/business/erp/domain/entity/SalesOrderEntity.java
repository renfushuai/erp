package net.rfs.smartadmin.module.business.erp.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.rfs.smartadmin.common.domain.BaseEntity;
import java.math.BigDecimal;
import lombok.Data;

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
@TableName("erp_sales_order")
public class SalesOrderEntity extends BaseEntity{


    /**
     * 订单号
     */
    private String code;

    /**
     * 实付金额
     */
    private BigDecimal actuallyAmount;

    /**
     * 订单金额
     */
    private BigDecimal orderAmount;

    /**
     * 医院
     */
    private String hospitalName;

    /**
     * 医院id
     */
    private Long hospitalId;

    /**
     * 1=外科2=内科3=器械
     */
    private Integer orderType;

    /**
     * 销售单类型
     */
    private String orderTypeName;

    /**
     * 经办人
     */
    private String manager;

    /**
     * 渠道1=医院 2=代理商
     */
    private Integer channel;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 地区编号
     */
    private String areaCode;

    /**
     * 地区名称
     */
    private String areaName;


    /**
     * 备注
     */
    private String memo;

    /**
     * 版本
     */
    private Integer version;

}
