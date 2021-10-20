package com.jetbone.others.april.demo;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by Chris on 2019-04-28 15:57.
 */
public class NormalTest {


    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        System.out.println(map.containsKey(null));

    }

    public enum Demo {
        MAIL,
        SMS,
        SITE,
        ;
    }

}
