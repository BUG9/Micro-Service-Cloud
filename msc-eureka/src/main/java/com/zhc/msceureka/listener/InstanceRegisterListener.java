package com.zhc.msceureka.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jingxian on 2018/6/19.
 * 服务注册事件
 */
@Configuration
public class InstanceRegisterListener implements ApplicationListener<EurekaInstanceRegisteredEvent> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(EurekaInstanceRegisteredEvent event) {

        logger.info("服务：" + event.getInstanceInfo().getAppName() + "心跳续约");

    }
}
