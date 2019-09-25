package com.jetbone.generics;

/**
 * Created by Chris on 2019/9/25
 */
public class Test {
    public static void main(String[] args) {

        Info info = new Info();
        Container container = new Container();
        container.setInfo(info);
        Info newInfo = GenericsUtil.getInfo(container);
        newInfo.print();

        Info subInfo1 = new SubInfo1();
        container.setInfo(subInfo1);
        Info newInfo1 = GenericsUtil.getInfo(container);
        newInfo1.print();

        Info subInfo2 = new SubInfo2();
        container.setInfo(subInfo2);
        Info newInfo2 = GenericsUtil.getInfo(container);
        newInfo2.print();

        container.setInfo(null);
        Info newInfo3 = GenericsUtil.getInfo(container);
        System.out.println(newInfo3);
    }
}
