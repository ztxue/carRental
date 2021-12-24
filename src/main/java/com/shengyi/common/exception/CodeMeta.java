package com.shengyi.common.exception;

import java.io.Serializable;

//错误码类
public class CodeMeta implements Serializable {
    private static final long serialVersionUID = -4508888328271951143L;
    public static final String ZH_CN = "zh_CN";
    public static final String ZH_TW = "zh_TW";
    public static final String EN_US = "en_US";

    /**
     * 错误码
     */
    private String code;
    /**
     * 异常名称
     */
    private String name;
    /**
     * 异常中文描述
     */
    private String msgZhCN;
    /**
     * 异常英文描述
     */
    private String msgEnUS;

    public CodeMeta(String code, String name, String msgZhCN) {
        super();
        this.code = code;
        this.name = name;
        this.msgZhCN = msgZhCN;
    }
    public CodeMeta(String code, String name, String msgZhCN, String msgEnUS) {
        super();
        this.code = code;
        this.name = name;
        this.msgZhCN = msgZhCN;
        this.msgEnUS = msgEnUS;
    }

    public String getMsg() {
        if(name==null) {
            if (msgZhCN != null) {
                return msgZhCN;
            }
            if (msgEnUS != null) {
                return msgEnUS;
            }
            return "";
        }
        if (msgZhCN != null) {
            return "[" + name + "]  " + msgZhCN;
        }
        if (msgEnUS != null) {
            return "[" + name + "]  " + msgEnUS;
        }
        return "[" + name + "]";
    }
    public String getMsg(String local) {

        if(name==null) {
            if (ZH_CN.equals(local)) {
                return msgZhCN;
            }

            if (EN_US.equals(local)) {
                return msgEnUS;
            }

            return "";
        }

        if (ZH_CN.equals(local)) {
            return "[" + name + "]  " + msgZhCN;
        }

        if (EN_US.equals(local)) {
            return "[" + name + "]  " + msgEnUS;
        }

        return "[" + name + "]";
    }
    @Override
    public String toString(){
        return code + "=[" + name + "] " + getMsg();
    }

    public String toString(String local){
        return code + "=[" + name + "] " + getMsg(local);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsgZhCN() {
        return msgZhCN;
    }

    public void setMsgZhCN(String msgZhCN) {
        this.msgZhCN = msgZhCN;
    }

    public String getMsgEnUS() {
        return msgEnUS;
    }

    public void setMsgEnUS(String msgEnUS) {
        this.msgEnUS = msgEnUS;
    }
}
