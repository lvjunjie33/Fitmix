package com.business.core.utils;

import com.business.core.constants.ApplicationConfig;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

/**
 * Created by Administrator on 2015/5/7.
 */
public class MailUtil implements Runnable{

    private String recipient;

    private String subject;

    private Object content;

    /**
     * 邮件服务器登录验证
     */
    protected transient MailAuth mailAuth;
    /**
     * 邮箱session
     */
    private Session session;

    public void build() {
        mailAuth = new MailAuth(ApplicationConfig.MAIL_USERNAME, ApplicationConfig.MAIL_PASSWORD);

        Properties props = System.getProperties();
        // 初始化props
//        props.put("mail.smtp.auth", "true");
        props.setProperty("mail.transport.protocol", "smtp"); // smtp协议
        props.put("mail.smtp.host", "smtp.qq.com");
        props.put("mail.smtp.port", "25");

        // 创建session
        session = Session.getInstance(props, mailAuth);
    }

    public void buildCL() {
        mailAuth = new MailAuth(ApplicationConfig.MAIL_USERNAME_CL, ApplicationConfig.MAIL_PASSWORD_CL);

        Properties propsCL = System.getProperties();
        // 初始化props
        propsCL.setProperty("mail.smtp.host", "smtp.office365.com");
        propsCL.setProperty("mail.smtp.auth", "true");
        propsCL.setProperty("mail.transport.protocol", "smtp"); // smtp协议
        propsCL.setProperty("mail.smtp.port", "587");
        propsCL.put("mail.smtp.starttls.enable", "true");

        // 创建session
        session = Session.getInstance(propsCL, mailAuth);
    }

    public void build(String username, String password, String host, String port) {
        mailAuth = new MailAuth(username, password);
        // 初始化props
        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.setProperty("mail.transport.protocol", "smtp"); // smtp协议
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", port);
        properties.put("mail.smtp.starttls.enable", "true");

        // 创建session
        session = Session.getInstance(properties, mailAuth);
    }

    /**
     * @param recipient 收件人邮箱地址
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    public MailUtil(String recipient, String subject, Object content) {
        //通过邮箱地址解析出smtp服务器，对大多数邮箱都管用
        this.recipient = recipient;
        this.subject = subject;
        this.content = content;
    }

    @Override
    public void run() {
        send(recipient, subject, content);
    }

    /**
     * 发送邮件
     * @param recipient 收件人邮箱地址
     * @param subject 邮件主题
     * @param content 邮件内容
     * @throws AddressException
     * @throws javax.mail.MessagingException
     */
    public void send(String recipient, String subject, Object content) {
        try {
            System.out.println("sendMail --> " + recipient);
            // 创建mime类型邮件
            final MimeMessage message = new MimeMessage(session);
            // 设置发信人
            message.setFrom(new InternetAddress(mailAuth.getUserName()));
            // 设置收件人
            message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
            // 抄信人邮件
//            message.setRecipient(Message.RecipientType.CC, new InternetAddress(mailAuth.getUserName()));
            // 设置主题
            message.setSubject(subject);
            // 设置邮件内容
            message.setContent(content.toString(), "text/html;charset=utf-8");
            // 发送
            Transport trans = session.getTransport();
            trans.connect(session.getProperty("mail.smtp.host"), mailAuth.getUserName(), mailAuth.getPassword());
            trans.sendMessage(message, InternetAddress.parse(recipient));
            trans.close();
            System.out.println("sendMail --> Success");
        }
        catch (Exception e) {
            System.out.println("sendMail --> Error");
            e.printStackTrace();
        }
    }
    
    /**
     * 群发邮件
     * @param recipients 收件人们
     * @param subject 主题
     * @param content 内容
     * @throws AddressException
     * @throws javax.mail.MessagingException
     */
    public void send(List<String> recipients, String subject, Object content) {
        try {
            // 创建mime类型邮件
            final MimeMessage message = new MimeMessage(session);
            // 设置发信人
            message.setFrom(new InternetAddress(mailAuth.getUserName()));

            // 设置收件人们
            final int num = recipients.size();
            InternetAddress[] addresses = new InternetAddress[num];
            for (int i = 0; i < num; i++) {
                addresses[i] = new InternetAddress(recipients.get(i));
            }
            message.setRecipients(RecipientType.TO, addresses);
            // 设置主题
            message.setSubject(subject);
            // 设置邮件内容
            message.setContent(content.toString(), "text/html;charset=utf-8");
            // 发送
            Transport trans = session.getTransport();
            trans.connect(session.getProperty("mail.smtp.host"), mailAuth.getUserName(), mailAuth.getPassword());
            trans.sendMessage(message, addresses);
            trans.close();
        } catch (Exception e) {
                System.out.println("sendMail --> Error");
                e.printStackTrace();
        }
    }

    public static void main (String args[]) throws MessagingException, UnsupportedEncodingException {
//        {
//            MailUtil mailUtil = new MailUtil("754521437@qq.com", "啪啪啪", "aadddda");
//            mailUtil.build("fitmix@igeekery.com", "Lanchou888", "smtp.qq.com", "25");
//
//            mailUtil.send("754521437@qq.com", "啪啪啪", "aadddda");
//        }
        if (true) {
            return;
        }

//        {
//            MailUtil mailUtil = new MailUtil("754521437@qq.com", "啪啪啪", "aadddda");
//            mailUtil.build("support01@roclivelifeloud.cn", "ROCsup01", "smtp.office365.com", "587");
//
//            mailUtil.send("754521437@qq.com", "啪啪啪", "aadddda");
//        }
    }
}

class MailAuth extends Authenticator {

    private String userName;

    private String password;

    public MailAuth(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return super.getPasswordAuthentication();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
