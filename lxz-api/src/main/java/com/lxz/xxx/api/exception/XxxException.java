package com.lxz.xxx.api.exception;

/**
 * 公共异常
 *
 * Created by xiaolezheng on 16/9/2.
 */
public class XxxException extends RuntimeException {

    public XxxException() {
        super();
    }

    public XxxException(String message) {
        super(message);
    }

    public XxxException(String message, Throwable cause) {
        super(message, cause);
    }

    public XxxException(Throwable cause, String format, Object... args){
        super(String.format(format, args));
    }

    public XxxException(Throwable cause) {
        super(cause);
    }
}
