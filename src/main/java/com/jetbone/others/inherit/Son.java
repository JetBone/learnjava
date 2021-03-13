package com.jetbone.others.inherit;

/**
 * Created by Chris on 2019-05-13 10:49.
 */
public class Son extends Father {
    private String hello;

    public Son () {
        super("father");
        System.out.println("Son 构造方法");
    }

    @Override
    public void hello() {
        doHello();
    }

    public void yo() {
        System.out.println("yooooo");
    }

    public void setHello() {
        hello = "son";
    }

    public String getHello() {
        return hello;
    }

    private void doHello() {
        System.out.println(hello);
    }
}
