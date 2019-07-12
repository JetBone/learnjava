package com.common;

/**
 * Created by Chris on 2019-06-04.
 */
public class ClassLoaderTest {

    public static void main(String[] args) {

        ClassLoaderTest clazz = new ClassLoaderTest();
        ClassLoader classLoader = clazz.getClass().getClassLoader();
        System.out.println(classLoader.toString());
        System.out.println(clazz.getClass().getName());

    }
}
