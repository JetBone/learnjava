package com.jetbone.others.design.patterns.behavioral.strategy;

/**
 * Created by Chris on 2019/9/1
 */
public class PromotionStrategy1 implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("do promotion strategy 1");
    }
}
