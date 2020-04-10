package com.jetbone.others.design.patterns.behavioral.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chris on 2019/9/1
 */
public class PromotionStrategyFactory {

    private static Map<String, PromotionStrategy> PROMOTION_STRATEGY_MAP = new HashMap<>();

    /**
     * 内部保存一个空的策略，在没有找到合适的策略的时候返回
     */
    private static final PromotionStrategy EMPTY_PROMOTION_STRATEGY = new EmptyPromotionStrategy();

    static {
        PROMOTION_STRATEGY_MAP.put(Strategy.STRATEGY1, new PromotionStrategy1());
        PROMOTION_STRATEGY_MAP.put(Strategy.STRATEGY2, new PromotionStrategy2());
    }

    /**
     * 因为内部的方法和参数都是static，所以直接私有构造器，避免创建工厂实例
     */
    private PromotionStrategyFactory() {
    }

    public static PromotionStrategy getPromotionStrategy(String name) {
        PromotionStrategy strategy = PROMOTION_STRATEGY_MAP.get(name);
        return strategy == null ? EMPTY_PROMOTION_STRATEGY : strategy;
    }

    /**
     * 使用内部的interface只是想要两个static final类型的String
     * 使用public修饰是为了让外部也可以获取到名字
     * 直接定义到工厂里面也是可以的
     * 或者定义一个枚举类类更好
     */
    public interface Strategy {
        String STRATEGY1 = "strategy1";
        String STRATEGY2 = "strategy2";
    }

}
