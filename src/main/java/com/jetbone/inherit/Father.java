package com.jetbone.inherit;

/**
 * Created by Chris on 2019-05-13 10:49.
 */
public class Father extends Man {
    private String hello;

    Father (String hello) {
        this.hello = hello;
        System.out.println("Father(String hello) 构造方法");
    }

    @Override
    public void hello() {
        System.out.println(hello);
    }

    public static void staticHello() {
        System.out.println("Father static hello");
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public String getHello() {
        return hello;
    }

}
