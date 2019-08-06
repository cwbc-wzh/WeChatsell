package com.atwzh.sell.controller;

import com.atwzh.sell.dateobject.ProductInfo;
import com.atwzh.sell.exception.SellException;
import com.atwzh.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author wangzihang
 * @createTime 2019/8/6
 * @description
 */
@Controller
@RequestMapping("/seller/product")
public class SellProductController {

    @Autowired
    ProductInfoService productInfoService;

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
}
