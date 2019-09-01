package com.jetbone.modifier.test;

import com.jetbone.modifier.Father;

/**
 * Created by Chris on 2019/9/1
 */
public class Daughter extends Father {

    @Override
    protected void protectedMethod() {
        System.out.println("daughter protected method");
    }

    @Override
    public void publicMethod() {
        System.out.println("daughter protected method");
    }
}
