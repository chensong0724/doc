package com.example.demo.product.entity;

import com.example.demo.hide.HideProuducPriceSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author bugpool
 * @since 2020-04-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品主键
     */
   // @TableId(value = "product_id", type = IdType.AUTO)
    private Integer productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 单价
     */
    @JsonSerialize(using = HideProuducPriceSerializer.class)
    private BigDecimal productPrice;

    /**
     * 描述
     */
    private String productDescription;

    /**
     * 商品状态,0正常1下架
     */
    private Integer productStatus;

    /**
     * 创建时间
     */
   // @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    //@TableField(fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 修改时间
     */
    //@TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    //@TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;


}
