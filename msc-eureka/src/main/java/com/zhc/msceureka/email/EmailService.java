package com.zhc.msceureka.email;

import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by jingxian on 2018/6/19.
 */
@Service
public class EmailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${mail.fromMail.sender}")
    private String sender;

    @Value("${mail.fromMail.receiver}")
    private String receiver;

    @Resource
    private JavaMailSender javaMailSender;

    private Long lastTime;

    public String sendMail(String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(receiver);
        message.setSubject(subject);
        message.setText(text);

        if (lastTime == null || System.currentTimeMillis() - lastTime > TimeUnit.MINUTES.toMillis(5)){// 间隔5分钟才能发送一次
            try {
                javaMailSender.send(message);
                lastTime = System.currentTimeMillis();
                logger.info("邮件已经发送。");
            } catch (Exception e) {
                logger.error("发送邮件时发生异常！", e);
            }
        }
        return "success";
    }

}
