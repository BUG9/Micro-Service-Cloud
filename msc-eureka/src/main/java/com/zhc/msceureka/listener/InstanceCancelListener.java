package com.zhc.msceureka.listener;

import javax.annotation.Resource;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import com.zhc.msceureka.email.EmailService;

/**
 * Created by jingxian on 2018/6/19.
 * 服务下线事件
 */
@Configuration
public class InstanceCancelListener implements ApplicationListener<EurekaInstanceCanceledEvent> {

    @Resource
    private EmailService emailService;

    @Override
    public void onApplicationEvent(EurekaInstanceCanceledEvent event) {
        emailService.sendMail("服务：" + event.getAppName() + "断开",
                "服务：" + event.getAppName() + ", IP地址：" + event.getServerId() + "与Eureka断开心跳");
    }

}