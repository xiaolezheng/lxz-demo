package com.lxz.xxx.api.support;

import org.apache.commons.lang3.StringUtils;

/**
 * toolbox工程http接口返回对象封装
 *
 * Created by xiaolezheng on 16/9/7.
 */
public class Response<T> extends JsonPrint{
    private int status;
    private String msg;
    private T data;

    public Response() {
    }

    public Response(Builder<T> builder){
        this.status = builder.status;
        this.msg = builder.msg;
        this.data = builder.data;
    }



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static class Builder<T>{
        private int status = ResponseStatus.SUCC.ordinal();
        private String msg = StringUtils.EMPTY;
        private T data;

        public Builder setStatus(int status) {
            this.status = status;
            return this;
        }

        public Builder setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder setData(T data) {
            this.data = data;
            return this;
        }

        public Builder succ(){
            this.status = ResponseStatus.SUCC.ordinal();
            return this;
        }

        public Builder succ(T data){
            this.status = ResponseStatus.SUCC.ordinal();
            this.data = data;
            return this;
        }

        public Builder fail(String msg){
            this.status = ResponseStatus.FAIL.ordinal();
            this.msg = msg;
            return this;
        }

        public Response<T> build(){
            Response<T> response = new Response<>(this);
            return response;
        }
    }
}
