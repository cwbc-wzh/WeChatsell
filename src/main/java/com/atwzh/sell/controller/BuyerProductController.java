package com.atwzh.sell.controller;

import com.atwzh.sell.dateobject.ProductCategory;
import com.atwzh.sell.dateobject.ProductInfo;
import com.atwzh.sell.service.CategoryService;
import com.atwzh.sell.service.ProductInfoService;
import com.atwzh.sell.utils.ResultVOUtil;
import com.atwzh.sell.vo.ProductInfoVO;
import com.atwzh.sell.vo.ProductVO;
import com.atwzh.sell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangzihang
 * @createTime 2019/6/26
 * @description 买家商品controller
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list")
    public ResultVO list() {

        List<ProductInfo> upAll = productInfoService.findUpAll();
        List<Integer> categoryTypeList = new ArrayList<>();
        for(ProductInfo productInfo : upAll) {
            categoryTypeList.add(productInfo.getCategoryType());
        }

        List<ProductCategory> byCategoryTypeIn = categoryService.findByCategoryTypeIn(categoryTypeList);


        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory : byCategoryTypeIn) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productINfoVOList = new ArrayList<>();
            for(ProductInfo productInfo : upAll) {
                ProductInfoVO productInfoVO = new ProductInfoVO();
                if(productInfo.getCategoryType().equals(productVO.getCategoryType())) {
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productINfoVOList.add(productInfoVO);
                }
            }
            productVO.setProdcutVOList(productINfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }

}
