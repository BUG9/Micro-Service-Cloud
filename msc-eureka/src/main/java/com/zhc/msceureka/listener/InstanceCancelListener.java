package com.zhc.msceureka.listener;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private EmailService emailService;

    @Override
    public void onApplicationEvent(EurekaInstanceCanceledEvent event) {

        logger.info("服务：" + event.getAppName() + "断开");

        emailService.sendMail("服务：" + event.getAppName() + "断开",
                "服务：" + event.getAppName() + ", IP地址：" + event.getServerId() + "与Eureka断开心跳");
    }

}