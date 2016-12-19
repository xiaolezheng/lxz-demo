package com.lxz.xxx.web.support;

import com.lxz.xxx.api.exception.XxxException;
import com.lxz.xxx.api.support.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Created by xiaolezheng on 16/9/6.
 */
@Slf4j
public class OuterAccessExceptionMapper implements ExceptionMapper<Exception> {
    private final String MEDIA_TYPE = MediaType.APPLICATION_JSON;

    @Override
    public Response toResponse(Exception exception) {
        log.error("异常拦截", exception);

        if (exception instanceof IllegalArgumentException) {
            return Response.status(HttpStatus.BAD_REQUEST.value())
                    .entity(toJsonStr(exception.getMessage()))
                    .type(MEDIA_TYPE)
                    .build();
        } else if (exception instanceof XxxException) {
            return Response.status(HttpStatus.SERVICE_UNAVAILABLE.value())
                    .entity(toJsonStr(exception.getMessage()))
                    .type(MEDIA_TYPE)
                    .build();
        }

        return Response.status(HttpStatus.SERVICE_UNAVAILABLE.value())
                .entity(toJsonStr("Service Unavailable"))
                .type(MEDIA_TYPE)
                .build();

    }

    private String toJsonStr(String msg) {
        return "{\"status\":\"" + ResponseStatus.FAIL.ordinal() + "\",\"msg\":\"" + msg + "\"}";
    }
}
