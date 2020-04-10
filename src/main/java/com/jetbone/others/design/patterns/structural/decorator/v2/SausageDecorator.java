package com.jetbone.others.design.patterns.structural.decorator.v2;

/**
 * Created by Chris on 2019/8/26
 */
public class SausageDecorator extends AbstractDecorator {
    @Override
    public void doSomethings() {
    }

    public SausageDecorator(IBatterCake batterCake) {
        super(batterCake);
    }

    @Override
    public String getDesc() {
        return super.getDesc() + " 加一根肠";
    }

    @Override
    public int cost() {
        return super.cost() + 3;
    }
}
