package com.lxz.xxx.service.sms;

import com.lxz.xxx.api.sms.model.SmsEntry;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by xiaolezheng on 16/9/7.
 */
@Slf4j
public class SmsIllegalInterceptor implements SmsInterceptor {
    @Override
    public Boolean intercept(SmsEntry smsEntry) {
        if (smsEntry == null || StringUtils.isEmpty(smsEntry.getMobile()) ||
                StringUtils.length(smsEntry.getMobile()) > 11 ||
                StringUtils.isEmpty(smsEntry.getContent())) {
            log.warn("短信非法被拦截, smsEntry: {}", smsEntry);

            return true;
        }

        return false;
    }
}
