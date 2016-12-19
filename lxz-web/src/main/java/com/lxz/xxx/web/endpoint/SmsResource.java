package com.lxz.xxx.web.endpoint;

import com.google.common.base.Preconditions;
import com.lxz.xxx.api.sms.model.SmsEntry;
import com.lxz.xxx.api.support.Response;
import com.lxz.xxx.service.sms.SmsSender;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by xiaolezheng on 16/9/7.
 */
@Path("sms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
@Slf4j
public class SmsResource {

    @Path("async")
    @POST
    public Object asyncSend(List<SmsEntry> smsEntryList) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(smsEntryList), "参数不能为空");
        SmsSender.getInstance().asyncSend(smsEntryList);

        return new Response.Builder().succ().build();
    }

    @POST
    public Object send(SmsEntry smsEntry) {
        Preconditions.checkNotNull(smsEntry, "参数不能为空");
        SmsSender.getInstance().send(smsEntry);

        return new Response.Builder().succ().build();
    }
}
