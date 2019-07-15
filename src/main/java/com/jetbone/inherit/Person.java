package com.jetbone.inherit;

/**
 * Created by Chris on 2019-05-13 10:11.
 */
public interface Person {
    void hello();

    static void staticHello() {
        System.out.println("Interface Person Static Hello");
    }
}
