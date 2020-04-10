package com.jetbone.others.design.patterns.structural.bridge;

/**
 * Created by Chris on 2019/8/29
 */
public abstract class Bank {

    /**
     * 桥接模式的核心，修饰为protected为了让子类也可以继承
     */
    protected Account account;

    public Bank(Account account) {
        this.account = account;
    }

    /**
     * 交给子类去实现，声明成与Account中的接口名字一样，并不是强制的，这里只是为了表示一种桥接关系
     */
    protected abstract void openAccount();

}
