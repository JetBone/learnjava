package com.jetbone.design.patterns.creational.prototype;

import java.text.MessageFormat;

/**
 * Created by Chris on 2019-08-19
 */
public class MailUtil {
    public static void sendMail(Mail mail) {
        String msg = "Send email to {0}, address: {1}, content: {2}, date: {3}";
        System.out.println(MessageFormat.format(msg,
                mail.getName(),
                mail.getMailAddress(),
                mail.getContent(),
                mail.getDate()
        ));
    }

    public static void saveOriginContent(Mail mail) {
        System.out.println("Save origin content: " + mail.getContent());
    }
}
