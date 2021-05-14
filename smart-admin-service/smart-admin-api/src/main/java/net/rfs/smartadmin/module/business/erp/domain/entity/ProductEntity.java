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
 * @date 2021-05-14 15:53:42
 * @since JDK1.8
 */
@Data
@TableName("erp_product")
public class ProductEntity extends BaseEntity{


    /**
     * 货号
     */
    private String productNumber;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 规格
     */
    private String specifications;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 单价
     */
    private BigDecimal standardPrice;

    /**
     * 商品类型1=外科，2=内科 3=器械
     */
    private Integer productType;

    /**
     * 状态
     */
    private Integer status;



}
