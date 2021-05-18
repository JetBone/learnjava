package com.jetbone.others.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Function;
import java.util.regex.Pattern;

import static java.time.format.DateTimeFormatter.*;

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

//        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
//        System.out.println(zoneId);
//
//        DateTest dateTest = new DateTest();
//        Class clazz = dateTest.getClass();
//
//        Field field = clazz.getDeclaredField("dateTime");
//        Method method = clazz.getDeclaredMethod("getDateTime");
//        Object val = method.invoke(dateTest);
//        Instant now = (Instant) val;
//        LocalDateTime localDateTime = LocalDateTime.ofInstant(now, zoneId);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String dateTimeStr = localDateTime.format(formatter);
//
//        System.out.println(now);
//        System.out.println(localDateTime.toString());
//        System.out.println(dateTimeStr);

        //===========================

//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        calendar.setFirstDayOfWeek(Calendar.MONDAY);
//        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//        Date first = calendar.getTime();
//        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
//        Date second = calendar.getTime();
//        calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
//        calendar.add(Calendar.DATE, -1);
//        calendar.set(Calendar.HOUR_OF_DAY, 23);
//        calendar.set(Calendar.MINUTE, 59);
//        calendar.set(Calendar.SECOND, 59);
//        calendar.set(Calendar.MILLISECOND, 999);
//        Date second = calendar.getTime();
//
//        calendar.add(Calendar.DATE, -6);
////        calendar.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        Date first = calendar.getTime();
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        System.out.println(sdf.format(first));
//        System.out.println(sdf.format(second));


        //===========================

//        String REGEX_TIMESTAMP =  "^(\\d{10,13})$";
        String REGEX_DATETIME = "^(\\d{10,13}|\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z)$";
        String REGEX_LOCAL_DATE = "^(\\d{4}-\\d{2}-\\d{2})$";
        String REGEX_LOCAL_DATETIME = "^(\\d{10,13}|\\d{4}-\\d{2}-\\d{2}(T|\\s)\\d{2}:\\d{2}:\\d{2})$";

        String dateStr1 = "2021-11-10";
        String dateStr2 = "2021-11-10 11:00:00";
        String dateStr3 = "2021-11-10T11:00:00";
        String dateStr4 = "2021-11-10T11:00:00Z";
        String dateStr5 = "2021-11-10T11:00:00+08:00";

        System.out.println(Pattern.matches(REGEX_LOCAL_DATE, dateStr1));
        System.out.println(Pattern.matches(REGEX_LOCAL_DATETIME, dateStr2));
        System.out.println(Pattern.matches(REGEX_LOCAL_DATETIME, dateStr3));
        System.out.println(Pattern.matches(REGEX_DATETIME, dateStr4));
//        System.out.println(Pattern.matches(REGEX_LOCAL_DATE, dateStr5));

//        DateTimeFormatter dateTimeFormatter = ISO_LOCAL_DATE;

//        LocalDate date = LocalDate.parse(dateStr, ISO_LOCAL_DATE);
//        LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.of(0,0, 0, 0));

//        LocalDate dateTime1 = LocalDate.parse(dateStr1, new DateTimeFormatterBuilder()
//                .parseCaseInsensitive()
//                .append(ISO_LOCAL_DATE)
//                .toFormatter());
//
//        LocalDateTime dateTime2 = LocalDateTime.parse(dateStr2, new DateTimeFormatterBuilder()
//                .parseCaseInsensitive()
//                .append(ISO_LOCAL_DATE)
//                .appendLiteral(' ')
//                .append(ISO_LOCAL_TIME)
//                .toFormatter());

        LocalDateTime dateTime3 = LocalDateTime.parse(dateStr3, ISO_LOCAL_DATE_TIME);

        TemporalQuery temporalQuery = LocalDateTime::from;
        TemporalQuery temporalQuery2 = a -> a;
        Function x = a -> a;

//        ISO_LOCAL_DATE_TIME.parse(dateStr3, temporalQuery2);

//        Instant dateTime4 = Instant.parse(dateStr4);
//
//        Instant dateTime5 = Instant.parse(dateStr5);
//
//
//        System.out.println(dateTime1);
//        System.out.println(dateTime2);
//        System.out.println(dateTime3);
//        System.out.println(dateTime4);
//        System.out.println(dateTime5);



    }
}
