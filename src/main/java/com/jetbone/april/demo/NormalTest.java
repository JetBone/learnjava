package com.jetbone.april.demo;

import java.awt.*;
import java.math.BigDecimal;

/**
 * Created by Chris on 2019-04-28 15:57.
 */
public class NormalTest {


    public static void main(String[] args) {
        BigDecimal num1 = new BigDecimal("0.01");

        if (num1.compareTo(BigDecimal.ZERO) == 1) {
            System.out.println("大");
        }

    }

    Desktop desktop;

}
