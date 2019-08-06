package com.business.bbs.base.view;

/**
 * Created by fenxio on 2016/8/23.
 * 用于和web 交互
 */
public class WebModel {

    public WebModel(Integer code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public WebModel(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public WebModel(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    /**
     * 错误码
     */
    private Integer code;
    /**
     * 返回结果对象
     */
    private Object data;
    /**
     * 信息
     */
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
