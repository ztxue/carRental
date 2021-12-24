package com.shengyi.common.exception;

import com.shengyi.common.StringUtils;
import org.apache.commons.lang3.LocaleUtils;

import java.util.Locale;

//service异常类
public class ServiceException extends BasicRuntimeException {
    private static final long serialVersionUID = 37899449648357361L;

    private final CodeMeta errorCode;
    private final Object[] params;


    public ServiceException(CodeMeta errorCode){
        super();
        this.errorCode = errorCode;
        this.params = null;
    }

    public ServiceException(CodeMeta errorCode, Object... args){
        super();
        this.errorCode = errorCode;
        this.params = args;
    }

    @Override
    public String getMessage() {
        return getMessage(Locale.CHINA);
    }
    public String getMessage(Locale localLocale){
        return  StringUtils.replaceParams( errorCode.getMsg(localLocale.toString()) , params);
    }


    public String getMessage(String localLocale, String a){

        return  StringUtils.replaceParams( errorCode.getMsg(LocaleUtils.toLocale(localLocale).toString()), params);
    }
    @Override
    public String toString(){
        return getMessage(Locale.US);
    }

    public CodeMeta getErrorCode() {
        return errorCode;
    }

    public Object[] getParams() {
        return params;
    }
}
