package com.core.common.utills;

import com.sys.entity.mail.Mail;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

public class MailUtil {
    static Logger log = Logger.getLogger(MailUtil.class);

    public static boolean send(Mail mail) {

        // TODO
        String from = "";
        int port = 25;
        String host = "";
        String pass = "";
        String nickname = "";

        HtmlEmail email = new HtmlEmail();
        try {
            email.setHostName(host);
            email.setCharset("UTF-8");
            for (String str : mail.getReceivers()) {
                email.addTo(str);
            }
            email.setFrom(from, nickname);
            email.setSmtpPort(port);
            email.setAuthentication(from, pass);
            email.setSubject(mail.getSubject());
            email.setMsg(mail.getMessage());
            email.send();
            log.info(from + "发送邮件到 " + mail.getReceivers() + "成功");
            return true;
        } catch (EmailException e) {
            log.error(from + "发送邮件到" + mail.getReceivers() + "失败", e);
            return false;
        }
    }
}
