package com.world.producer.rabbitmq;


import com.world.common.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by Administrator on 2017/7/9.
 */
@Configuration
@RabbitListener(queues = "email")
public class RabbitmqEmailReceive {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.fromMail.address}")
    private String from;

    @RabbitHandler
    public void sendEmailToUser(User user) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(user.getEmail());
            helper.setSubject("world");
            String text = "<h1>此邮件为官方激活邮件！请点击下面链接完成激活操作！</h1><h3><a href='http://www.lankezhou.com/web/user/active?activeCode="+
                    user.getActiveCode() +"'>http://www.lankezhou.com/web/user/active</a></h3>";
            helper.setText(text, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            System.out.println("发送html邮件时发生异常");
        }
    }

}
