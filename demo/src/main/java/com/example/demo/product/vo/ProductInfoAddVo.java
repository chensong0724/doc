package com.example.demo.product.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProductInfoAddVo {
    // 主键
    private Integer productId;

    // 商品名称
    @NotBlank(message = "商品名称不允许为空")
    private String productName;

    // 商品价格
    @Min(value = 0, message = "商品价格不允许为负数")
    @NotNull(message = "商品价格不允许没空")
    private BigDecimal productPrice;

    // 描述
    private String productDescription;

    // 上架状态
    private Integer productStatus;
}
