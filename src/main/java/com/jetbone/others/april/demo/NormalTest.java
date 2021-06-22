package com.jetbone.others.april.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        BigDecimal num = new BigDecimal("0.00000000");
        System.out.println(num.toPlainString());

        Map<String, String> map = new HashMap();
        map.put("a", "string");
        System.out.println(map.get("b"));

    }

}
