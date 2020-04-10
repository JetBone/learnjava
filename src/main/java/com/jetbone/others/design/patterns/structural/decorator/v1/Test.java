package com.jetbone.others.design.patterns.structural.decorator.v1;

/**
 * Created by Chris on 2019/8/26
 */
public class Test {
    public static void main(String[] args) {
        BatterCake batterCake = new BatterCake();
        System.out.println(batterCake.getDesc() + " 售价：" + batterCake.cost());

        BatterCake batterCakeWithEgg = new BatterCakeWithEgg();
        System.out.println(batterCakeWithEgg.getDesc() + " 售价：" + batterCakeWithEgg.cost());

        BatterCake batterCakeWithEggSausage = new BatterCakeWithEggSausage();
        System.out.println(batterCakeWithEggSausage.getDesc() + " 售价：" + batterCakeWithEggSausage.cost());

        // 很显然上述类的实现是非常差的一个例子
        // 如果有人要加两个蛋两个肠的煎饼果子，那么必须新增新类才能完成功能
        // 如此依赖，面对无尽的需求，将会难以开发维护
    }
}
