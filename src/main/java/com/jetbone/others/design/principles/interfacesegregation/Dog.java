package com.jetbone.others.design.principles.interfacesegregation;


/**
 * Created by Chris on 2019-07-23 21:50.
 */
public class Dog implements IAnimal {
    @Override
    public void eat() {

    }

    // 该接口无用
    @Override
    public void fly() {

    }

    @Override
    public void swim() {

    }
}
