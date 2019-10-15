package com.jetbone.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Chris on 2019-05-22.
 */
public class DateTest {

    private Instant dateTime = Instant.now();

    public Instant getDateTime() {
        return this.dateTime;
    }

    public static void main(String[] args) throws Exception {
//        TimeZone timeZone = TimeZone.getDefault();
//        Date date = new Date();
//        LocalDate localDate = LocalDate.now();
//        System.out.println(timeZone.getDisplayName());

        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        System.out.println(zoneId);

        DateTest dateTest = new DateTest();
        Class clazz = dateTest.getClass();

        Field field = clazz.getDeclaredField("dateTime");
        Method method = clazz.getDeclaredMethod("getDateTime");
        Object val = method.invoke(dateTest);
        Instant now = (Instant) val;
        LocalDateTime localDateTime = LocalDateTime.ofInstant(now, zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeStr = localDateTime.format(formatter);

        System.out.println(now);
        System.out.println(localDateTime.toString());
        System.out.println(dateTimeStr);

    }
}
