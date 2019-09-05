package com.atwzh.sell.handler;


import com.atwzh.sell.exception.ResponseBankException;
import com.atwzh.sell.exception.SellException;
import com.atwzh.sell.exception.SellerAuthorizeException;
import com.atwzh.sell.utils.ResultVOUtil;
import com.atwzh.sell.vo.ResultVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellExceptionHandler {

    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthrizeException() {
        return new ModelAndView("redirect:"+"http:www.baidu.com");
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public void handleResponseBankException() {

    }
}
