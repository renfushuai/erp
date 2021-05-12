package net.rfs.smartadmin.module.business.erp.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.rfs.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 医院 ]
 *
 * @author 任富帅
 * @version 1.0
 * @company rfs
 * @copyright (c)  rfsInc. All rights reserved.
 * @date 2021-05-12 14:41:23
 * @since JDK1.8
 */
@Data
@TableName("erp_hospital")
public class HospitalEntity extends BaseEntity{


    /**
     * 医院名称
     */
    private String name;

    /**
     * 地区编号
     */
    private String areaCode;

    /**
     * 状态
     */
    private Integer status;



}
