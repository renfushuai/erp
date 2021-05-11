package net.rfs.smartadmin.module.business.erp.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.rfs.smartadmin.common.domain.BaseEntity;
import lombok.Data;

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
@TableName("erp_company")
public class CompanyEntity extends BaseEntity{


    /**
     * name
     */
    private String name;


    /**
     * status
     */
    private Integer status;

}
