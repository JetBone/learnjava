package com.jetbone.others.design.patterns.structural.decorator.v1;

/**
 * Created by Chris on 2019/8/26
 */
public class BatterCakeWithEgg extends BatterCake {
    @Override
    public String getDesc() {
        return super.getDesc() + " 加一个蛋";
    }

    @Override
    public int cost() {
        return super.cost() + 2;
    }
}
