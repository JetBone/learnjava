package com.jetbone.design.patterns.structural.decorator.v2;

/**
 * Created by Chris on 2019/8/26
 *
 * 如果没有需要做的其他抽象方法，可以直接写为实体装饰类
 * 装饰类与被装饰类继承/实现相同的抽象类/接口，
 */
public abstract class AbstractDecorator implements IBatterCake {

    // 根据v1版本的装饰方式，可知装饰操作是需要继承父类，来对父类进行扩充
    // 如果需要反复动态的装饰，那么要保证装饰后还能出来的类型和最开始的类型要保持一致
    // 就像是建造者模式的链式调用一样，每次装饰后返回的类型都是被装饰的类型，才能保证动态装饰
    // 为了保证上述的功能，我们将被装饰者抽象出来，然后装饰者和被装饰者都实现/继承该抽象类
    // 该参数是装饰器核心，为被装饰者
    private IBatterCake batterCake;

    /**
     * 抽象类，用于做一些其他的业务逻辑
     */
    public abstract void doSomethings();

    /**
     * 装饰者模式中的核心
     * 一种传入类型和返回类型都是被包装的类型的构造器
     * @param batterCake
     */
    public AbstractDecorator(IBatterCake batterCake) {
        this.batterCake = batterCake;
    }

    @Override
    public String getDesc() {
        return batterCake.getDesc();
    }

    @Override
    public int cost() {
        return batterCake.cost();
    }
}
