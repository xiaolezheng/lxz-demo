package com.lxz.xxx.common.support;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * okHttp3 cookie管理
 * 这里是设置cookie的
 * 但是并没有做持久化处理
 * 只是把cookie保存在内存中
 */
@Slf4j
public class CookieJarManage implements CookieJar {
    private static final Map<String, List<Cookie>> cookieStore = Maps.newConcurrentMap();

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        cookieStore.put(url.host(), cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url.host());
        return cookies != null ? cookies : new ArrayList<Cookie>();
    }
} 