package com.sangeng.handler;

import com.sangeng.domain.ResponseResult;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * You can add extra (@ExceptionHandler) methods to any controller to specifically handle exceptions
     * thrown by request handling (@RequestMapping) methods in the same controller. Such methods can:
     *
     * Handle exceptions without the @ResponseStatus annotation (typically predefined exceptions that you didn’t write)
     * Redirect the user to a dedicated error view
     * Build a totally custom error response
     */
    @ExceptionHandler(SystemException.class)  // 注解抛出异常
    public ResponseResult systemExceptionHandler(SystemException e){
        // 打印异常信息
        log.error("出现了异常", e);
        // 从异常对象中获取提示信息封装返回

        return ResponseResult.errorResult(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)  // 注解抛出异常
    public ResponseResult exceptionHandler(Exception e){
        // 打印异常信息
        log.error("出现了异常", e);
        // 从异常对象中获取提示信息封装返回

        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }
}
