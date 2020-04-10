package com.jetbone.others.design.patterns.behavioral.strategy;

/**
 * Created by Chris on 2019/9/1
 */
public class PromotionActivity {
    private PromotionStrategy strategy;

    public PromotionActivity(PromotionStrategy strategy) {
        this.strategy = strategy;
    }

    public void doPromotionStrategy() {
        strategy.doPromotion();
    }
}
