package com.jetbone.others.april.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Chris on 2019/3/21.
 */
public class Test {

    public static void main(String[] args) throws Exception {



        String lowStr = "abcdefghijklmnopqrstuvwxyz";
        String upperStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String num = "0123456789";

        Random random = new Random();
        for (int n = 0; n < 100; n++) {
            var pwd = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                var ran = random.nextInt(3);
                switch (ran) {
                    case 0:
                        pwd.append(lowStr.charAt(random.nextInt(lowStr.length())));
                        break;
                    case 1:
                        pwd.append(upperStr.charAt(random.nextInt(upperStr.length())));
                        break;
                    case 2:
                        pwd.append(num.charAt(random.nextInt(num.length())));
                        break;
                    default:
                        pwd.append(num.charAt(random.nextInt(num.length())));
                }
            }
            System.out.println("" + n + " : " +pwd);
        }


    }

}
