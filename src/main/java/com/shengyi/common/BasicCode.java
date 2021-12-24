package com.shengyi.common;

import com.shengyi.common.exception.CodeMeta;

//基础返回类code
public class BasicCode {
    /**
     * ReadMe：
     * ----每一条错误信息应该包括：错误代码和注释。
     *
     * ----命名规范如下：
     * 例如：000000、000001等，代表公用的、常见的一些错误类型。
     *
     * ----特别提醒：
     *  一定要写注释，方便自己和别人快速查看！
     */

    /**未知错误*/
    public static final CodeMeta UNKNOWN_ERROR = new CodeMeta("000001", "UNKNOWN_ERROR",
            "未知错误{}", "Unknown error, see: {}");

    /** UNDEFINED_ERROR */
    public static final CodeMeta UNDEFINED_ERROR = new CodeMeta("000002", "UNDEFINED_ERROR",
            "{}", "{}");

    /** 无效的参数 */
    public static final CodeMeta INVALID_PARAM = new CodeMeta("000003", "UNVALID_PARAM",
            "无效的参数{}", "unvalid param: {}");

    /** 参数({})不能为空！*/
    public static final CodeMeta PARAM_NULL = new CodeMeta("000004", "PARAM_ERROR",
            "参数({})不能为空", "this argument ({}) is required; it must not be null");
    public static final CodeMeta REFLECT_OP_EX = new CodeMeta("000005",
            "DB_ERROR", "对象反射({})!", "REFLECT ({}) error!");
    public static final CodeMeta NOT_FOUND = new CodeMeta("000006",
            "NOT_FOUND_ERROR","接口或方法不存在", "service or method not found!");
    /** 不支持的数据类型({})! */
    public static final CodeMeta UN_SUPPORT = new CodeMeta("000006",
            "UN_SUPPORT", "不支持的数据类型!", "unsurpport data ({}) !");
    /** token值错误({})! */
    public static final CodeMeta TOKEN_ERROR = new CodeMeta("000007",
            "TOKEN_ERROR", "token错误!", "token error!");
    public static final CodeMeta NO_PERMISSION = new CodeMeta("000008",
            "NO_PERMISSION", "无权限!", "no permission error!");
    public static final CodeMeta TIME_FORMATE_ERROR = new CodeMeta("000009",
            "TIME_FORMATE_ERROR", "时间格式错误!", "time format error!");


    /** 数据({})状态不正常！ */
    public static final CodeMeta DATA_STATE_NOT_NORMAL = new CodeMeta("000011",
            "BUSINESS_CHECK_ERROR", "数据对象{}不正常", "data{} not normal!");

    /** 数据对象不存在*/
    public static final CodeMeta DATA_NOT_EXIST = new CodeMeta("000012", "DATA_NOT_EXIST",
            "数据对象{}不存在", " Data ({}) is not exist!");
    /** 数据对象已经存在*/
    public static final CodeMeta DATA_EXIST = new CodeMeta("000013", "DATA_EXIST",
            "数据对象{}已经存在", " Data ({}) is already exist!");



    /** 数据删除失败*/
    public static final CodeMeta DB_DELETE_FAILED = new CodeMeta("000021",
            "DB_ERROR", "数据({})删除失败!", "data ({}) delete failed!");

    /** 数据更新失败! */
    public static final CodeMeta DB_UPDATE_FAILED = new CodeMeta("000022",
            "DB_ERROR", "数据({})更新失败!", "data ({}) update failed!");

    /** 数据插入失败! */
    public static final CodeMeta DB_INSERT_FAILED = new CodeMeta("000023",
            "DB_ERROR", "数据({})插入失败!", "data ({}) insert failed!");

    /** 数据不存在({})! */
    public static final CodeMeta DB_DATA_NOT_FOUND = new CodeMeta("000024",
            "DB_ERROR", "数据不存在({})!", "data ({}) not found!");

    public static final CodeMeta COUNT_NOT_ENOUGH = new CodeMeta("000025",
            "COUNT_NOT_ENOUGH","所选用户小于两人，不能群发!","less than two,not send!");
}
