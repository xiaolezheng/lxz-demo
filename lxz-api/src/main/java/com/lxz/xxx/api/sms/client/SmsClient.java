package com.lxz.xxx.api.sms.client;

import com.lxz.xxx.api.sms.model.SmsEntry;
import com.lxz.xxx.api.support.Response;
import feign.Headers;
import feign.RequestLine;

import java.util.List;

/**
 * 短信发送API
 * <p>
 * Created by xiaolezheng on 16/9/7.
 */
public interface SmsClient {
    /**
     * 同步发送短信
     *
     * @param smsEntry
     * @return
     */
    @RequestLine("POST /api/sms")
    @Headers("Content-Type: application/json")
    Response send(SmsEntry smsEntry);

    /**
     * 异步发送短信
     *
     * @param smsEntryList
     * @return
     */
    @RequestLine("POST /api/sms/async")
    @Headers("Content-Type: application/json")
    Response asyncSend(List<SmsEntry> smsEntryList);
}
