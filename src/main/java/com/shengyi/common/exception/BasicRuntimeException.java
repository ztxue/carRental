package com.shengyi.common.exception;

import com.shengyi.common.StringUtils;

//运行异常类
public class BasicRuntimeException extends RuntimeException {
    public BasicRuntimeException(){
        super();
    }
    public BasicRuntimeException(String message, String... args){
        super(StringUtils.replaceParams(message, args));
    }
    public BasicRuntimeException(Throwable e){
        super(e);
    }
    public BasicRuntimeException(String message, Throwable e){
        super(message, e);
    }
    public BasicRuntimeException(Throwable e, String message, String... args){
        super(StringUtils.replaceParams(message, args), e);
    }
}
