package com.jetbone.modifier;

/**
 * Created by Chris on 2019/9/1
 */
public class Son extends Father {
    @Override
    void defaultMethod() {
        System.out.println("son default(package-private) method");
    }

    @Override
    protected void protectedMethod() {
        System.out.println("son protected method");
    }

    @Override
    public void publicMethod() {
        System.out.println("son public method");
    }
}
