package com.jetbone.design.patterns.structural.proxy.staticproxy;

import com.jetbone.design.patterns.structural.proxy.Order;
import com.jetbone.design.patterns.structural.proxy.OrderDaoImpl;
import com.jetbone.design.patterns.structural.proxy.OrderServiceImpl;

/**
 * Created by Chris on 2019/8/31
 */
public class Test {
    public static void main(String[] args) {

        Order order = new Order();
        OrderServiceStaticProxy orderServiceStaticProxy = new OrderServiceStaticProxy(new OrderServiceImpl(new OrderDaoImpl()));

        // 通过静态代理类使用0号数据库
        order.setUserId(0L);
        orderServiceStaticProxy.saveOrder(order);

        System.out.println("------------------------"); // 分隔符

        // 通过静态代理类使用1号数据库
        order.setUserId(1L);
        orderServiceStaticProxy.saveOrder(order);

        // 如果让代理类实现service接口，并添加一个传入类型和返回类型都是service的构造器
        // 就变成了所谓的装饰者模式，当然从结构上看是这样的
        // 但是真的这么做，最终调用的数据库应该由最里面那层的包装决定了吧
        // 按照目前的操作，就算真的改造成装饰者模式，但是数据库是根据UserID决定的，所以多层装饰都是一个结果
        // 总之，可以说是从逻辑上来讲，抽象到这个层次就已经完美实现业务了
        //
        // 其次，非常像桥接模式，service的操作委托给proxy去执行了，感觉上几乎一样了
        // 我可以将proxy抽象出来，然后实现多个proxy，然后也可以写多个service
        // 这样，就是所谓的"抽象与实现分离"？结果就是个桥接模式了吗
        // 当然从设计上，还是有点区别，比如service一般是不会进行水平扩张多个实现类的
        // proxy也许会，但是也是很少
        // 所谓的代理和桥接，获取只是名字上的区别
        // 我可说：桥接到另外一个数据库 替代 代理到另外一个数据库 好像也没什么区别
        // 毕竟使用静态代理的话，基本上就没区别了

    }
}
