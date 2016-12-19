package com.lxz.xxx.common.util;


import com.lxz.xxx.api.exception.XxxException;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;

/**
 * Created by xiaolezheng on 16/9/2.
 */
@Slf4j
public final class CommonUtil {
    public static String getHost(String urlStr) {
        try {
            URL url = new URL(urlStr);
            return url.getHost();
        } catch (Exception e) {
            throw new XxxException("从url获取host异常", e);
        }
    }
}
