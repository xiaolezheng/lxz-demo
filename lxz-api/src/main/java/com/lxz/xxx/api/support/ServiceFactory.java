package com.lxz.xxx.api.support;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xiaolezheng on 16/12/19.
 */
public final class ServiceFactory {
    private static final Map<Class<?>, Object> singletons = new ConcurrentHashMap<>();

    public static <T> T find(Environment env, Class<T> classType) {
        Object service = singletons.get(classType);
        if (service == null) {
            service = build(env, classType);
            singletons.putIfAbsent(classType, service);
        }

        return classType.cast(service);
    }

    private static <T> T build(Environment env, Class<T> classType) {
        System.out.println("build service: " + classType.getName());
        T service = Feign.builder().client(new OkHttpClient()).encoder(new JacksonEncoder()).decoder(new JacksonDecoder()).logger(new Logger.ErrorLogger())
                .logLevel(Logger.Level.BASIC).target(classType, env.value());
        return service;
    }
}
