package com.xddal.moon.common.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 同意返回格式
 *
 * @param <T>
 * @author xuedong
 */
@Data
public class Restful<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int OK = 111111;
    private static final int FAIL = 999999;

    /**
     * 服务端数据
     */
    private T data;
    /**
     * 状态码
     */
    private int retCode = OK;
    /**
     * 描述信息
     */
    private String retMsg = "";

    /**
     * APIS
     *
     * @return
     */
    /**
     * Constructors
     */
    public Restful() {
    }

    public static Restful isOk() {
        return new Restful();
    }

    public static Restful isFail() {
        return new Restful().retCode(FAIL);
    }

    public static Restful isFail(Throwable e) {
        return isFail().retMsg(e);
    }

    public Restful retMsg(Throwable e) {
        this.setRetMsg(e.toString());
        return this;
    }

    public Restful data(T data) {
        this.setData(data);
        return this;
    }

    public Restful retCode(int retCode) {
        this.setRetCode(retCode);
        return this;
    }


}
