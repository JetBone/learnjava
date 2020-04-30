package com.jetbone.others.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

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

        //===========================

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date first = calendar.getTime();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date second = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(sdf.format(first));
        System.out.println(sdf.format(second));



    }
}
