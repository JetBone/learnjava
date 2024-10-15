package com.jetbone.others.regex;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author Chris
 * @date 2021-11-03
 */
public class Test {

    private static Pattern typePattern = Pattern.compile("([a-zA-Z0-9]+)(?:\\((([0-9]+)|(([0-9]+),([0-9]+)))\\))?");
    private static Pattern lengthPattern = Pattern.compile("[0-9]+");
    private static Pattern splitPattern = Pattern.compile("\\(|\\)|,");

    public static void main(String[] args) {


        String type1 = "VARCHAR(64)";
        String type2 = "BIGINT";
        String type3 = "TINYINT(1)";
        String type4 = "DECIMAL(20,8)";
        String type5 = "DATETIME";

        Arrays.asList(type1, type2, type3, type4, type5, "(12312)").forEach(type -> {
            System.out.println("type: " + type);

            var s = type.split("\\(|\\)|,");

            System.out.println(s);
//            var r = typePattern.matcher(type);
//            if (r.matches()) {
//                for(int i = 1; i < r.groupCount(); i++) {
//                    var s = r.group(i);
//                    System.out.println(s);
//                }
//            } else {
//                System.out.println("not match");
//            }
//            System.out.println("==================");
        });

    }
}
