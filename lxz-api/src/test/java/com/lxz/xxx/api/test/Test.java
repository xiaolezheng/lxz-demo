package com.lxz.xxx.api.test;

import com.lxz.xxx.api.sms.client.SmsClient;
import com.lxz.xxx.api.sms.model.SmsEntry;
import com.lxz.xxx.api.support.Environment;
import com.lxz.xxx.api.support.ServiceFactory;

import java.util.ArrayList;

/**
 * Created by xiaolezheng on 16/9/7.
 */
public class Test {
    public static void main(String[] args) {

        SmsEntry smsEntry = new SmsEntry.Builder().setMobile("15810957053").setContent("测试").build();
        SmsEntry smsEntry1 = new SmsEntry.Builder().setMobile("15810957053").setContent("测试1").build();
        //client.send(smsEntry);

        ArrayList<SmsEntry> list = new ArrayList<>();
        list.add(smsEntry);
        list.add(smsEntry1);


        SmsClient client = ServiceFactory.find(Environment.TEST, SmsClient.class);
        client.send(smsEntry);
        ServiceFactory.find(Environment.TEST, SmsClient.class).send(smsEntry1);
    }
}
