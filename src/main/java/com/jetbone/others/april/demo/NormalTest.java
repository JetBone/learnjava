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

        String pwd = "idiskdsk0&80";

        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,16}$";

        var pattern = Pattern.compile(regex);

        System.out.println(pattern.matcher(pwd).matches());

        Thread thread = new Thread();

    }

}
