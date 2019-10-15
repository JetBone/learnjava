package com.jetbone.common;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.Instant;
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
