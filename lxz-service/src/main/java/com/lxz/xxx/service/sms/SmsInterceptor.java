package com.lxz.xxx.service.sms;

import com.lxz.xxx.api.sms.model.SmsEntry;
import com.lxz.xxx.service.support.Interceptor;

/**
 * 短信拦截
 *
 * Created by xiaolezheng on 16/9/5.
 */
public interface SmsInterceptor extends Interceptor<SmsEntry, Boolean> {
}
