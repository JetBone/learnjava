package com.jetbone.others.april.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chris on 2019-04-28 15:57.
 */
public class NormalTest {


    public static void main(String[] args) {

//        List<Long> qty = Arrays.asList(12L, 323L, 422L, 13L, null, 323L, null);
//
//        qty.stream().map(qt -> Objects.requireNonNullElse(qt, 0L)).reduce(0L, Long::sum);
//
//        Map<String, String> map = new HashMap<>();
//        map.put("123", "12321");
//        map.put("1223", "12321");
//        map.put("1213", "12321");
//        map.put("1423", "12321");
//
//        System.out.println("结果：" + map);

        HashMap<String, String> map = new HashMap<>();
        Map<String, String> map2 = map;


    }

    public enum Demo {
        MAIL,
        SMS,
        SITE,
        ;
    }

}
