package com.jetbone.design.principles.interfacesegregation;

/**
 * Created by Chris on 2019-07-23 21:48.
 * 接口过多，不便于实现，容易出现部分接口无用现象
 */
public interface IAnimal {
    void eat();
    void fly();
    void swim();
}
