package com.atwzh.sell.handler;


import com.atwzh.sell.exception.SellerAuthorizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellExceptionHandler {

    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthrizeException() {
        return new ModelAndView("redirect:"+"http:www.baidu.com");
    }

}
