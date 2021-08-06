package com.jetbone.others.april.demo;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Created by Chris on 2019-04-28 15:57.
 */
public class NormalTest {


    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        list.add("123");
//        list.add("123");
//        list.add("412");
//        list.add("314");
//        list.add("232");
//        list.add("s42");
//        list.add("g31");
//        list.add("s41");
//
//        var iterator = list.iterator();
//
//        while (iterator.hasNext()) {
//            var next = iterator.next();
//            if ("412".equals(next)) {
//                iterator.remove();
//                System.out.println("remove: " + next);
//            } else {
//                System.out.println(next);
//            }
//        }
//
//        System.out.println("final: " + list);

//        BigDecimal num = new BigDecimal("0.00000000");
//        System.out.println(num.toPlainString());
//
//        Map<String, String> map = new HashMap();
//        map.put("a", "string");
//        System.out.println(map.get("b"));


        var list = Arrays.asList(null, null, "12321", "d1dwqd", null, null);
//        var list = buildList();
        System.out.println(list);
        System.out.println(list.contains("123"));

        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        List<String> list2 = new ArrayList<>();
        list2.add("3");
        list2.add("5");
        list2.add("6");

        list1.retainAll(list2);

        System.out.println("list1:" + list1);
        System.out.println("list2:" + list2);

    }

    private static List<String> buildList(String... value) {
        return Arrays.asList(value);
    }

}
