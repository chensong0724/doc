package com.example.demo.product.service.impl;


import com.example.demo.product.entity.ProductInfo;
import com.example.demo.product.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author bugpool
 * @since 2020-04-14
 */
@Service
public class ProductInfoServiceImpl /*extends ServiceImpl<ProductInfoMapper, ProductInfo>*/ implements ProductInfoService {

   /* @Autowired
    ProductInfoMapper productInfoMapper;
*/
    @Override
    //@Cacheable(value = "redis", key = "#root.targetClass + '::' + #root.methodName + '::' + #productName")
    public List getByLikeName(String productName) {
        List<ProductInfo> productInfos = new ArrayList<>();
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductPrice(BigDecimal.valueOf(0));
        productInfo.setProductName("并能霏霏");
        productInfos.add(productInfo);
        return productInfos;
        //return productInfoMapper.getByLikeName(productName);
    }

}
