package com.jetbone.design.patterns.structural.decorator.v1;

/**
 * Created by Chris on 2019/8/26
 */
public class BatterCakeWithEggSausage extends BatterCakeWithEgg {
    @Override
    public String getDesc() {
        return super.getDesc() + " 加一根肠";
    }

    @Override
    public int cost() {
        return super.cost() + 3;
    }
}
