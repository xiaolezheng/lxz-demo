package com.lxz.xxx.api.sms.model;


import com.lxz.xxx.api.support.JsonPrint;

import java.io.Serializable;

/**
 * 短信实体
 *
 * Created by xiaolezheng on 16/9/5.
 */
public class SmsEntry extends JsonPrint implements Serializable {
    private static final long serialVersionUID = -7186245326899896952L;
    /**
     * 目标手机号, 手机号非法会被拦截
     */
    private String mobile;
    /**
     * 短信内容,短信内容为空会被拦截
     */
    private String content;


    public SmsEntry() {
    }

    public SmsEntry(Builder builder) {
        this.mobile = builder.mobile;
        this.content = builder.content;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static final class Builder {
        private String mobile;
        private String content;

        public Builder() {

        }
        public Builder setMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public SmsEntry build() {
            SmsEntry smsEntry = new SmsEntry(this);
            return smsEntry;
        }
    }
}
