package com.jetbone.pdf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Chris on 2020/3/27
 */
public class Demo {

    public static void main(String[] args) {

        List<String> list = new ArrayList();

        list.add("string1");
        list.add("string2");
        list.add("string3");

        PDFUtil util = PDFUtil.of(list, String.class);

        System.out.println(util.getDataList());
        System.out.println(util.getType());

    }

}
