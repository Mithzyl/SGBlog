package com.sangeng.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sangeng.enums.AppHttpCodeEnum;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> implements Serializable {
    // response code eg.200, 403
    private Integer code;

    // response message eg. "success"
    private String message;

    // response object
    private T data;

    // 默认成功响应
    public ResponseResult() {
        this.code = AppHttpCodeEnum.SUCCESS.getCode();
        this.message = AppHttpCodeEnum.SUCCESS.getMessage();
    }

    public ResponseResult(Integer code, T data){
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseResult setAppHttpCodeEnum(AppHttpCodeEnum enums){
        return okResult(enums.getCode(), enums.getMessage());
    }

    public static ResponseResult setAppHttpCodeEnum(AppHttpCodeEnum enums, String message){
        return okResult(enums.getCode(), message);
    }

    public static ResponseResult errorResult(AppHttpCodeEnum enums){
        return errorResult(enums.getCode(), enums.getMessage());
    }

    public static ResponseResult errorResult(int code, String message){
        ResponseResult result = new ResponseResult();
        return result.error(code, message);
    }

    public static ResponseResult okResult(){
        // 默认成功响应
        ResponseResult responseResult = new ResponseResult();
        return responseResult;
    }

    public static ResponseResult okResult(int code, String message){
        ResponseResult responseResult = new ResponseResult();
        return responseResult.ok(code, null, message);
    }

    // 返回对象
    public static ResponseResult okResult(Object data){
        ResponseResult responseResult = setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS, AppHttpCodeEnum.SUCCESS.getMessage());
        if(data != null){
            responseResult.setData(data);
        }
        return responseResult;
    }


    public ResponseResult<?> error(Integer code, String message){
        this.code = code;
        this.message = message;
        return this;
    }

    public ResponseResult<?> ok(Integer code, String message){
        this.code = code;
        this.message = message;
        return this;
    }

    public ResponseResult<?> ok(Integer code, T data, String message){
        this.code = code;
        this.data = data;
        this.message = message;
        return this;
    }

    public ResponseResult<?> ok(T data){
        this.data = data;
        return this;
    }

    public Integer getCode(){
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }
}
