package com.is.health.entity;

/**
 * Created by Administrator on 2016/8/20 0020.
 */
public class BaseResponse<T> {
    public int code;
    public String msg;
    public T data;
}