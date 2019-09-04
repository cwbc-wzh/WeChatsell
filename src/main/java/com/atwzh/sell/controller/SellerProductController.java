package com.atwzh.sell.controller;

import com.atwzh.sell.dateobject.ProductCategory;
import com.atwzh.sell.dateobject.ProductInfo;
import com.atwzh.sell.exception.SellException;
import com.atwzh.sell.form.ProductForm;
import com.atwzh.sell.service.CategoryService;
import com.atwzh.sell.service.ProductInfoService;
import com.atwzh.sell.utils.KeyUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author wangzihang
 * @createTime 2019/8/6
 * @description
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    ProductInfoService productInfoService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "5") Integer size,
                             Map<String, Object> map) {

        PageRequest request = new PageRequest(page - 1, size);

        Page<ProductInfo> list = productInfoService.findAll(request);

        map.put("productInfo", list);
        map.put("currentpage", page);
        map.put("size",size);

        return new ModelAndView("product/list",map);
    }

    @RequestMapping("onSale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {

        try {
            productInfoService.onSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    @RequestMapping("offSale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {

        try {
            productInfoService.offSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {
        if(!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productInfoService.getOne(productId);
            map.put("productInfo", productInfo);
        }

        List<ProductCategory> categoryList = categoryService.findAll();

        map.put("categoryList", categoryList);

        return new ModelAndView("product/index", map);
    }

    @PostMapping("/save")
    public ModelAndView save(ProductForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if(bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error");
        }

        ProductInfo productInfo = new ProductInfo();

        try{
            if(!StringUtils.isEmpty(form.getProductId())) {
                productInfo = productInfoService.getOne(form.getProductId());
            } else {
                form.setProductId(KeyUtil.generalKey());
            }
            BeanUtils.copyProperties(form,productInfo);
            productInfoService.save(productInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error");
        }
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }
}
