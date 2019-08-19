package com.jetbone.design.patterns.creational.prototype;

import java.util.Date;

/**
 * Created by Chris on 2019-08-19
 */
public class Mail implements Cloneable {
    private String name;
    private String mailAddress;
    private String content;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Mail() {
        System.out.println("Mail Constructor");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("Mail Clone Method");

        Mail mail = (Mail) super.clone();

        // 深克隆，对象内部的引用类型需要执行clone方法，否则引用类型是不会被克隆的
        // 这里使用不当容易造成BUG
        mail.date = (Date) mail.date.clone();

        return super.clone();
    }

    @Override
    public String toString() {
        return "Mail: {name: " + name
                + ", address: " + mailAddress
                + ", content: " + content
                + ", date: " + date
                + ", hexString: " + Integer.toHexString(hashCode())
                + "}";
    }
}
