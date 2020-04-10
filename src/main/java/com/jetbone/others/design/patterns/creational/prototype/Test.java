package com.jetbone.others.design.patterns.creational.prototype;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by Chris on 2019-08-19
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        Mail mail = new Mail();
        mail.setContent("Origin Content");
        mail.setDate(Date.valueOf(LocalDate.now().minusDays(1)));

        for (int i = 0; i < 5; i++) {

            // 为保证下述需求，循环内部需要一个新的mail对象
            // 假设实例化一个新的Mail有很多重复的工作，并且内部需要初始化大量的类
            // 那么使用new去初始化一个新的实例是不明智的选择
            // 使用clone()并不会调用mail的构造方法，而是直接从内存进行二进制拷贝
            // 并且拷贝出的实例是一个全新的实例，速度也要比new快很多
            Mail mailTemp = (Mail) mail.clone();

            mailTemp.setName("Recipient No " + i);
            mailTemp.setMailAddress("mail" + i + "@mail.com");
            mailTemp.setContent("This is content.");

            // 这里在重新设置了clone的mailTemp.date后，发现原型实例中的date也发生了改变
            // 主要原因是clone方法是浅克隆，只对当层克隆，如果对象内部有引用类型的参数，那么该引用类型的参数指向并不会发生变化
            // 解决浅克隆的方式是在重写的clone方法内调用引用类型的clone方法，保证每一层都进行克隆
            mailTemp.setDate(Date.valueOf(LocalDate.now()));

            System.out.println(mailTemp);
            MailUtil.sendMail(mailTemp);
        }

        System.out.println(mail);

        // 假设我们要保存原始模版内容
        // 并且处于某种业务需求，还要保存其他各种信息，比如执行过程产生的一些数据
        // 导致本行必须放到最后执行
        // 那么导致的就是在for循环中，每次都要使用一个新的mail类
        MailUtil.saveOriginContent(mail);

    }
}
