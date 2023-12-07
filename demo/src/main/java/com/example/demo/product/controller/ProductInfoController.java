package com.example.demo.product.controller;


import com.example.demo.product.entity.ProductInfo;
import com.example.demo.product.service.ProductInfoService;
import com.example.demo.product.vo.ProductInfoAddVo;
import com.example.demo.product.vo.ProductInfoQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author bugpool
 * @since 2020-04-14
 */
@RestController
@RequestMapping("/product/product-info")
public class ProductInfoController {

    @Autowired
    ProductInfoService productInfoService;

    @PostMapping("/findById")
    public ProductInfoQueryVo findById(@Valid @NotNull Integer id) {
        return null;//BeanConvertUtils.convertTo(productInfoService.getById(id), ProductInfoQueryVo::new);
    }

    /*@PostMapping("/page")
    public IPage findPage(Page page, @Validated ProductInfoQueryVo vo) {
        // 将vo => po，进行page查询
       // productInfoService.page(page, new QueryWrapper<ProductInfo>(BeanConvertUtils.convertTo(vo, ProductInfo::new)));
        // page.getRecords()此时为po类型，转换为vo
        //page.setRecords(BeanConvertUtils.convertListTo(page.getRecords(), ProductInfoQueryVo::new));
        return page;
    }*/

    @PostMapping("/findByLikeName")
    public List findByLikeName(String productName) {
        // lambda query写法
        // List<ProductInfo> list = productInfoService.lambdaQuery().like(ProductInfo::getProductName, productName).list();

        // 为了演示自定义sql
        List<ProductInfo> list = productInfoService.getByLikeName(productName);
        return list;
    }

    @PostMapping("/add")
    public boolean add(ProductInfoAddVo vo) {
        return false;// productInfoService.save(BeanConvertUtils.convertTo(vo, ProductInfo::new));
    }

    @PostMapping("/deleteById")
    public boolean deleteById(@Valid @NotNull Integer id) {
        return false;//productInfoService.removeById(id);
    }

    @PostMapping("/updateById")
    public boolean updateById(@Valid ProductInfoAddVo vo) {
        return false;//productInfoService.updateById(BeanConvertUtils.convertTo(vo, ProductInfo::new));
    }
}
