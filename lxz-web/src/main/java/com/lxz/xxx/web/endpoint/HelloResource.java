package com.lxz.xxx.web.endpoint;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.lxz.xxx.api.exception.XxxException;
import com.lxz.xxx.api.support.Response;
import com.lxz.xxx.service.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Path("hello")
@Produces(MediaType.APPLICATION_JSON)
@Component
@Slf4j
public class HelloResource {
    @Autowired
    private Config config;

    @PostConstruct
    public void init() {
        log.info("hello jersey");
        log.info("config: {}", config);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object getHello() {
        return new Response.Builder().succ().build();
    }

    @GET
    @Path("query")
    public Object query(@QueryParam("name") String name) {
        Map data = ImmutableMap.of("name", name, "age", 10, "desc", "测试");

        return new Response.Builder().setData(data).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("save")
    public Object save(Map params) {
        Map data = Maps.newHashMap(params);
        return new Response.Builder().setData(data).build();
    }

    @GET
    @Path("error")
    public Object error() {
        throw new XxxException("test exception");
    }
}