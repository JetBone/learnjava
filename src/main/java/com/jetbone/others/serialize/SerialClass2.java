package com.jetbone.others.serialize;

import java.io.Serializable;

/**
 * @author Chris
 * @date 2021-06-22
 */
public class SerialClass2 implements SerialInterface {

    private static final long serialVersionUID = 550308139021124769L;

    private String name;

    private int age;

    private String gender;

    @Override
    public String toString() {
        return "SerialClass2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
