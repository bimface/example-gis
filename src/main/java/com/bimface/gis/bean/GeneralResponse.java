package com.bimface.gis.bean;

import java.io.Serializable;

public class GeneralResponse<T> implements Serializable {

    private static final long  serialVersionUID = 1390573278822224616L;

    // 定义的通用错误码
    public static final String CODE_SUCCESS     = "success";

    // 统一的日期格式
    public static final String DATE_FORMAT      = "yyyy-MM-dd HH:mm:ss";

    private String             code             = CODE_SUCCESS;         // 成功
    private String             message;                                 // 失败时，错误原因的简单英文描述
    private T                  data;                                    // 执行成功后返回的结果

    public GeneralResponse() {
    }

    public GeneralResponse(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
