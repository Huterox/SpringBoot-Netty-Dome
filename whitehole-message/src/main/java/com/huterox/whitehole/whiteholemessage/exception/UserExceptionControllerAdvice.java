package com.huterox.whitehole.whiteholemessage.exception;

import com.huterox.common.exception.BadLoginParamsException;
import com.huterox.common.exception.BadLoginTokenException;
import com.huterox.common.exception.NotLoginException;
import com.huterox.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestControllerAdvice(basePackages = "com.huterox.whitehole.whiteholemessage.controller")
public class UserExceptionControllerAdvice {

    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public R handleVaildException(MethodArgumentNotValidException e){
        log.error("数据校验出现问题{}，异常类型：{}",e.getMessage(),e.getClass());
        BindingResult bindingResult = e.getBindingResult();

        Map<String,String> errorMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach((fieldError)->{
            errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
        });
        return R.error(BizCodeEnum.VAILD_EXCEPTION.getCode(),BizCodeEnum.VAILD_EXCEPTION.getMsg()).put("data",errorMap);
    }

    @ExceptionHandler(value= NotLoginException.class)
    public R notLoginException(Throwable throwable){
        log.error("错误：",throwable);
        return R.error(BizCodeEnum.NOT_LOGIN.getCode(),BizCodeEnum.NOT_LOGIN.getMsg());
    }

    @ExceptionHandler(value= BadLoginParamsException.class)
    public R badLoginParamsException(Throwable throwable){
        log.error("错误：",throwable);
        return R.error(BizCodeEnum.BAD_LOGIN_PARAMS.getCode(),BizCodeEnum.BAD_LOGIN_PARAMS.getMsg());
    }
    @ExceptionHandler(value= BadLoginTokenException.class)
    public R badLoginTokenException(Throwable throwable){
        log.error("错误：",throwable);
        return R.error(BizCodeEnum.BAD_TOKEN.getCode(),BizCodeEnum.BAD_TOKEN.getMsg());
    }


    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable){
        //拦截所有的异常
        log.error("错误：",throwable);
        return R.error(BizCodeEnum.UNKNOW_EXCEPTION.getCode(),BizCodeEnum.UNKNOW_EXCEPTION.getMsg());
    }

}
