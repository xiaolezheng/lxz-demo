package com.lxz.xxx.task.support;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by xiaolezheng on 16/9/3.
 */
@Slf4j
public class ApplicationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("ApplicationContextListener init............");

    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        log.info("ApplicationContextListener destroyed............");
    }
}
