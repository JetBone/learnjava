package com.jetbone.others.design.patterns.structural.decorator.v2;

/**
 * Created by Chris on 2019/8/26
 */
public class BatterCake implements IBatterCake {
    @Override
    public String getDesc() {
        return "煎饼果子";
    }

    @Override
    public int cost() {
        return 6;
    }
}
