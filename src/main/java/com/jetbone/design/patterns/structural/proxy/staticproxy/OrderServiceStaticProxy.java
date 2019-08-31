package com.jetbone.design.patterns.structural.proxy.staticproxy;

import com.jetbone.design.patterns.structural.proxy.IOrderService;
import com.jetbone.design.patterns.structural.proxy.Order;
import com.jetbone.design.patterns.structural.proxy.db.DataSourceContextHolder;

/**
 * Created by Chris on 2019/8/31
 */
public class OrderServiceStaticProxy {

    private IOrderService orderService;

    /**
     * 模拟Spring构造器注入
     * @param orderService
     */
    public OrderServiceStaticProxy(IOrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 代理方法
     * @param order
     * @return
     */
    public int saveOrder(Order order) {
        beforeMethod();

        Long userId = order.getUserId();

        // 这里模拟分库操作
        // 在静态代理类当中进行分库，然后调用service层的实际业务操作
        int dbRouter = userId.intValue() % 2;
        // 模拟设置DB
        DataSourceContextHolder.setDBType(String.valueOf(dbRouter));
        System.out.println("static proxy delegate to db: " + dbRouter + " to resolve data");

        int result = orderService.saveOrder(order);

        afterMethod();
        return result;
    }

    private void beforeMethod() {
        System.out.println("static proxy, before method");
    }

    private void afterMethod() {
        System.out.println("static proxy, after method");
    }
}
