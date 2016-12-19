package com.lxz.xxx.service.config;

import com.lxz.xxx.api.support.JsonPrint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by xiaolezheng on 16/12/19.
 */
@Component
@Slf4j
public class Config extends JsonPrint{
    @Value("${env}")
    private String env;

    @Value("${key}")
    private String key;

    @PostConstruct
    public void init() {
        log.info("-----------------------------------");
        log.info("env: {}", env);
        log.info("url: {}", key);
        log.info("-----------------------------------");
    }
}
