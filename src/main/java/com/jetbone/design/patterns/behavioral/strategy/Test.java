package com.jetbone.design.patterns.behavioral.strategy;

/**
 * Created by Chris on 2019/9/1
 */
public class Test {
    public static void main(String[] args) {

        // 方式一，看起来像是个非常普通的桥接模式
        // 这样做并不能消除大量的if else 语句
        PromotionActivity activity = null;
        String strategyName = "strategy1";
        if (strategyName.equals("strategy1")) {
            activity = new PromotionActivity(new PromotionStrategy1());
        } else if (strategyName.equals("strategy2")) {
            activity = new PromotionActivity(new PromotionStrategy2());
        }
        activity.doPromotionStrategy();

        // 方式二，结合工厂模式，消除if...else...
        String strategyStr = PromotionStrategyFactory.Strategy.STRATEGY1;
        PromotionStrategy strategy = PromotionStrategyFactory.getPromotionStrategy(strategyStr);
        PromotionActivity activity1 = new PromotionActivity(strategy);
        activity1.doPromotionStrategy();

        // 策略模式看起来像是概念上不同的桥接模式

    }
}
