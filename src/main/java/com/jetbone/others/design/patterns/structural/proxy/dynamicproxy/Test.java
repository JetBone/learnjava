package com.jetbone.others.design.patterns.structural.proxy.dynamicproxy;

import com.jetbone.others.design.patterns.structural.proxy.IOrderService;
import com.jetbone.others.design.patterns.structural.proxy.Order;
import com.jetbone.others.design.patterns.structural.proxy.OrderDaoImpl;
import com.jetbone.others.design.patterns.structural.proxy.OrderServiceImpl;

/**
 * Created by Chris on 2019/8/31
 */
public class Test {
    public static void main(String[] args) {
        Order order = new Order();

        // 使用动态代理生成类的bind方法动态的生成指定类的代理类
        IOrderService orderService = (IOrderService) new DynamicProxy(new OrderServiceImpl(new OrderDaoImpl())).bind();

        order.setUserId(0L);
        orderService.saveOrder(order);

        System.out.println("------------------------"); // 分隔符

        order.setUserId(1L);
        orderService.saveOrder(order);

        // 动态代理可以代理任何想代理的类而无需新创建代理类
        // 动态代理可以说是用一个invoke方法代理了被代理类中所有实现的接口方法
        // 而静态代理只代理了显式指定的代理方法

    }
}
