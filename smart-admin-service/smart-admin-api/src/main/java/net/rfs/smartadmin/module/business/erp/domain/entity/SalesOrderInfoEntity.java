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
 * @date 2021-05-12 15:00:50
 * @since JDK1.8
 */
@Data
@TableName("erp_sales_order_info")
public class SalesOrderInfoEntity extends BaseEntity{


    /**
     * 销售单号
     */
    private String orderCode;

    /**
     * 商品Id
     */
    private Long productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 货号
     */
    private String productNumber;

    /**
     * 规格
     */
    private String specifications;

    /**
     * 数量
     */
    private Integer salesQuantity;

    /**
     * 出货金额
     */
    private BigDecimal salesPrice;

    /**
     * 出货单价
     */
    private Integer standardPrice;

}
