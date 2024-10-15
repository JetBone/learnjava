package com.jetbone.others.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Chris
 * @date 2021-11-23
 */
public class Test {

    public static void main(String[] args) {
        Map<String, String> maps = new HashMap<>();

        maps.put("1", "1");
        maps.put("2", "2");
        maps.put("3", "3");
        maps.put("4", "4");
        maps.put("5", "5");
        maps.put("6", "6");

        String path = "/2/";

        if (path.startsWith("/")) {
            path = path.substring(1);
        }

        System.out.println(path.startsWith("/"));

        System.out.println(path.split("/").length);
    }
}
