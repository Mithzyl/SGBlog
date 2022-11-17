package com.sangeng.exception;

import com.sangeng.enums.AppHttpCodeEnum;

public class SystemException extends RuntimeException{
    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public SystemException(AppHttpCodeEnum appHttpCodeEnum) {
        super(appHttpCodeEnum.getMessage());
        this.code = appHttpCodeEnum.getCode();
        this.message = appHttpCodeEnum.getMessage();
    }
}
