package com.jetbone.design.principles.interfacesegregation;

/**
 * Created by Chris on 2019-07-23 21:50.
 */
public class Bird implements IAnimal {
    @Override
    public void eat() {

    }

    @Override
    public void fly() {

    }

    // 该接口无用
    @Override
    public void swim() {

    }
}
