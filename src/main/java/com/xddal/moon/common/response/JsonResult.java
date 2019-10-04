package com.xddal.moon.common.response;

import lombok.*;

import java.io.Serializable;

/**
 * 用于封装服务器到客户端的Json返回值
 *
 * @author xuedong
 */
@Data
public class JsonResult<T> implements Serializable {
    /**
     * Serializable将对象的状态保存在存储媒体中以便可以在以后重新创建出完全相同的副本
     */

    public static final int SUCCESS = 111111;
    public static final int ERROR = 999999;

    private int state;
    private String message = "";
    private T data;

    public JsonResult() {
        state = SUCCESS;
    }

    /**
     * 为了方便，重载n个构造器
     *
     * @param state int
     * @param message String
     * @param data T
     */
    public JsonResult(int state, String message, T data) {
        super();
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public JsonResult(int state, String error) {
        this(state, error, null);
    }

    public JsonResult(int state, T data) {
        this(state, "", data);
    }

    public JsonResult(String error) {
        this(ERROR, error, null);
    }

    public JsonResult(T data) {
        this(SUCCESS, "", data);
    }

    public JsonResult(int state) {
        this(state, "", null);
    }

    public JsonResult(Throwable e) {
        this(ERROR, e.getMessage(), null);
    }

    public static int getSuccess() {
        return SUCCESS;
    }

    public static int getError() {
        return ERROR;
    }

}
