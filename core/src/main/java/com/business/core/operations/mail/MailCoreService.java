package com.business.core.operations.mail;

import com.business.core.constants.ApplicationConfig;
import com.business.core.entity.SysParam;
import com.business.core.utils.DateUtil;
import com.business.core.utils.MailUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by sin on 2015/5/7.
 */
@Service
public class MailCoreService {
    /**
     * 用户找回密码
     * @param recipient 发送邮件
     */
    public void sendMail1 (String recipient, String name, String mail) {
        MailUtil mailUtil = new MailUtil(recipient, SysParam.INSTANCE.MAIL_USER_RECOVERY_PASSWORD_SUBJECT,
                String.format(SysParam.INSTANCE.MAIL_USER_RECOVERY_PASSWORD_TEMPLATE, name, mail, DateUtil.format(new Date(), "yyyy-MM-dd HH:mm")));

        mailUtil.build();

        new Thread(mailUtil).start();
    }

    public void sendMailVerificationCode (String mail, String code, Long currentTime) {
        MailUtil mailUtil = new MailUtil(mail, SysParam.INSTANCE.MAIL_USER_RECOVERY_PASSWORD_SUBJECT,
                String.format(SysParam.INSTANCE.MAIL_USER_RECOVERY_VERIFICATION_CODE_TEMPLATE, code,code, DateUtil.format(new Date(currentTime), "yyyy-MM-dd HH:mm")));
        mailUtil.build();
        new Thread(mailUtil).start();
    }

    public void sendMailVerificationCodeCL(String mail, String code, Long currentTime) {
        MailUtil mailUtil = new MailUtil(mail, "ROC睿响邮件验证码",
                String.format(SysParam.INSTANCE.MAIL_USER_RECOVERY_VERIFICATION_CODE_TEMPLATE_ROC, code,code, DateUtil.format(new Date(currentTime), "yyyy-MM-dd HH:mm")));
        mailUtil.buildCL();
        new Thread(mailUtil).start();
    }
}
