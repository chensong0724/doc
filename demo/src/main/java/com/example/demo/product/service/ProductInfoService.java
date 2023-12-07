package com.example.demo.product.service;


import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author bugpool
 * @since 2020-04-14
 */
public interface ProductInfoService /*extends IService<ProductInfo>*/ {
    List getByLikeName(String productName);
}
