package com.jetbone.others.design.patterns.structural.proxy;

/**
 * Created by Chris on 2019/8/31
 */
public class OrderServiceImpl implements IOrderService {
    private IOrderDao orderDao;

    /**
     * 模拟构造器注入dao
     * @param orderDao
     */
    public OrderServiceImpl(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }


    @Override
    public int saveOrder(Order order) {
        System.out.println("service save order");
        return orderDao.insertOrder(order);
    }
}
