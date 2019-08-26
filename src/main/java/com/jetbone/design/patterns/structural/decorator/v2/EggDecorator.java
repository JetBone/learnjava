package com.jetbone.design.patterns.structural.decorator.v2;

/**
 * Created by Chris on 2019/8/26
 */
public class EggDecorator extends AbstractDecorator {
    @Override
    public void doSomethings() {
    }

    public EggDecorator(IBatterCake batterCake) {
        super(batterCake);
    }

    @Override
    public String getDesc() {
        return super.getDesc() + " 加一个蛋";
    }

    @Override
    public int cost() {
        return super.cost() + 2;
    }
}
