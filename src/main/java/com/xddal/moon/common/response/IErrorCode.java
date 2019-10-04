package com.xddal.moon.common.response;

/**
 * 封装API的错误码
 * Created by xuedong on 2019/4/19.
 *
 * @author xuedong
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
