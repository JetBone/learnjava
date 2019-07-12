package com.common;

import java.time.LocalDate;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Chris on 2019-05-22.
 */
public class DateTest {

    public static void main(String[] args) {
        TimeZone timeZone = TimeZone.getDefault();
        Date date = new Date();
        LocalDate localDate = LocalDate.now();
        System.out.println(timeZone.getDisplayName());
    }
}
