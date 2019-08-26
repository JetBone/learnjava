package com.jetbone.design.patterns.structural.decorator.v2;

/**
 * Created by Chris on 2019/8/26
 */
public class Test {
    public static void main(String[] args) {

        // 这里的batterCake就像是元数据一样，没有它，则后面的装饰者也都没法执行
        // 毕竟没有被装饰者，如何装饰
        // 没有煎饼果子，还加啥蛋和肠啊
        IBatterCake batterCake = new BatterCake();
        batterCake = new EggDecorator(batterCake); // 加一个蛋
        batterCake = new EggDecorator(batterCake); // 再加一个蛋
        batterCake = new SausageDecorator(batterCake); // 再加一根肠
        System.out.println(batterCake.getDesc() + " 价格：" + batterCake.cost());
    }
}
