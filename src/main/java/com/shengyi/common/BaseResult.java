package com.shengyi.common;

import java.io.Serializable;

//基础返回类
public class BaseResult implements Serializable {
    private static final long serialVersionUID = -9190938586310296899L;
    /** 成功标志*/
    private boolean success;

    /** 信息码 */
    private String code;

    /** 描述 */
    private String description;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseResult{");
        sb.append("success=").append(success);
        sb.append(", code='").append(code).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}