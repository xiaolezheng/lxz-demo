package com.lxz.xxx.web.support;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * Created by xiaolezheng on 16/9/6.
 */
public class MyApplication extends ResourceConfig {
    private static final String PACKAGE = "com.lxz.xxx.web.endpoint";

    public MyApplication() {
        register(RequestContextFilter.class);

        // 注册数据转换器
        register(JacksonJsonProvider.class);
        // 注册resource
        packages(PACKAGE);
        // 注册异常转换器
        register(MyExceptionMapper.class);
    }
}
