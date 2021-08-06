package com.jetbone.others.serialize;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Chris
 * @date 2021-06-22
 */
@Data
//@AllArgsConstructor
public class SerialClass implements SerialInterface {

    private static final long serialVersionUID = 550308139021124769L;

    private String name;

    private int age;

    private String gender;

    private String email;

    private String address;

    @Override
    public String toString() {
        return "SerialClass{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
