package com.tangenta.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
public class MailService {
    public void send_email(String Recipient, String link) throws MessagingException {
        Properties properties = new Properties();
        //设置属性
        //代理邮箱
        properties.put("mail.smtp.host","smtp.163.com");
        //端口
        properties.put("mail.smtp.post","25");
        //验证模式
        properties.put("mail.smtp.auth","true");
        Session session = Session.getInstance(properties);

        Message message = new MimeMessage(session);
        //设置日期
        message.setSentDate(new Date());
        //发件人邮箱
        message.setFrom(new InternetAddress("CS2016BCJY@163.com"));
        //收件人邮箱
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(Recipient));
        //邮件主题
        message.setSubject("注册邮箱验证");
        //设置邮件正文  第二个参数是邮件发送的类型
        String emailContent = "您已经注册成功：<span style=\"color: #F3750F;font-weight: bold;font-size: larger;font-family: cursive;\">" +
                "<a href=\"" + link +
                "\">" +
                "点击此链接跳转" +
                "</a>" +
                "</span><br/>" +
                "此链接只能使用一次，验证成功自动失效；<br/>" +
                "<div style=\"font-size: small;color: gray;\">" +
                "(请在10分钟内完成验证，10分钟后验证码失效，您需要重新进行验证。感谢您的支持。)<br/>" +
                "如果您错误地收到了本电子邮件，请您忽略上述内容<br/>" + "</div>";
        message.setContent(emailContent,"text/html;charset=UTF-8");
        //发送一封邮件
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.163.com","CS2016BCJY@163.com","5138YUHN");
        transport.sendMessage(message,message.getAllRecipients());
        transport.close();
    }
}
