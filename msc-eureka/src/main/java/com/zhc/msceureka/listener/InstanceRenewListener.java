package com.zhc.msceureka.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jingxian on 2018/6/19.
 * 服务续约事件（心跳检测）
 */
@Configuration
public class InstanceRenewListener implements ApplicationListener<EurekaInstanceRenewedEvent> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(EurekaInstanceRenewedEvent event) {
        logger.info("服务：" + event.getAppName() + "心跳续约");
    }
}
