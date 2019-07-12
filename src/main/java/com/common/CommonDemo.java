package com.common;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 2019-06-03.
 */
public class CommonDemo {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.stream();

        BigDecimal a = null;

        System.out.println(a != null && a.compareTo(BigDecimal.ZERO) == 1);
    }
}
