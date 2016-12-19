package com.lxz.xxx.service.sms;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lxz.xxx.api.exception.XxxException;
import com.lxz.xxx.api.sms.model.SmsEntry;
import com.lxz.xxx.common.util.PropertyUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.collections.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiaolezheng on 16/9/5.
 */
@Slf4j
public final class SmsSender {
    private static final SmsSender instance = new SmsSender();
    private static final Map<Class, SmsInterceptor> interceptorMap = Maps.newConcurrentMap();
    private static final String SMS_URL = PropertyUtil.getProperty("msg_url");

    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS).connectTimeout(30, TimeUnit.SECONDS).build();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    static {
        instance.addInterceptor(new SmsIllegalInterceptor());
    }

    private SmsSender() {
    }

    public static SmsSender getInstance() {
        return instance;
    }

    public void addInterceptor(SmsInterceptor interceptor) {
        if (interceptor != null) {
            interceptorMap.put(interceptor.getClass(), interceptor);
        }
    }

    public void remove(Class interceptorClazz) {
        interceptorMap.remove(interceptorClazz);
    }

    private boolean intercept(SmsEntry smsEntry) {
        for (SmsInterceptor interceptor : interceptorMap.values()) {
            if (interceptor.intercept(smsEntry)) {
                return true;
            }
        }

        return false;
    }

    public boolean send(SmsEntry entry) {
        if (!intercept(entry)) {
            Request request = buildRequest(entry);
            try {
                Response response = okHttpClient.newCall(request).execute();
                log.info("发送短信成功, entry:{}, response: {}", entry, response.body().string());
            } catch (Exception e) {
                throw new XxxException("短信发送异常", e);
            }
        }

        return false;
    }

    public void asyncSend(List<SmsEntry> entryList) {
        if (CollectionUtils.isNotEmpty(entryList)) {
            for (final SmsEntry entry : entryList) {
                try {
                    if (!intercept(entry)) {

                        Request request = buildRequest(entry);
                        // 异步请求
                        okHttpClient.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                log.error("发送短信失败, entry:{}", entry, e);
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                log.info("发送短信成功, entry:{}, response: {}", entry, response.body().string());
                            }
                        });
                    }
                } catch (Exception e) {
                    throw new XxxException("短信发送异常", e);
                }
            }
        }
    }

    private Request buildRequest(SmsEntry entry) {
        String content = "";
        RequestBody requestBody = RequestBody.create(JSON, content);
        return new Request.Builder().url(SMS_URL).post(requestBody).build();
    }


    public static void main(String[] args) {
        SmsEntry smsEntry = new SmsEntry.Builder().setMobile("xxx").setContent("你好").build();
        SmsSender.getInstance().asyncSend(Lists.newArrayList(smsEntry));
        //SmsSender.getInstance().send(smsEntry);
    }
}
