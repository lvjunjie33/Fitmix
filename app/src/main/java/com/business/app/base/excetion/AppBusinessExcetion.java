package com.business.app.base.excetion;

/**
 * Created by zhangtao on 2016/11/21.
 */
public class AppBusinessExcetion extends BaseExcetion {

    private Integer code;

    public AppBusinessExcetion(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
