package com.zjl.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class MailService {
    @Resource
    private JavaMailSender mailSender;

    public boolean sendSimpleMail(Mail mail) throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("263046851@qq.com");
        message.setTo(mail.getReceiver());
        message.setSubject(mail.getSubject());
        message.setText(mail.getText());
        mailSender.send(message);
        log.info("发送成功!");
        return true;
    }
}
