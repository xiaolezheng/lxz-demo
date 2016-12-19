package com.lxz.xxx.service.support;

/**
 * Created by xiaolezheng on 16/9/5.
 */
public interface Interceptor<Input, Output> {
    Output intercept(Input input);
}
