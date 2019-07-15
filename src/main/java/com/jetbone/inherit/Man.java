package com.jetbone.inherit;

/**
 * Created by Chris on 2019-05-13 10:12.
 */
public class Man implements Person {

    Man() {
        System.out.println("Man 构造方法");
    }

    @Override
    public void hello() {
        System.out.println("man");
    }

}
