package com.jetbone.design.patterns.structural.proxy;

/**
 * Created by Chris on 2019/8/31
 */
public class OrderDaoImpl implements IOrderDao {
    @Override
    public int insertOrder(Order order) {
        // 模拟dao层插入order成功
        System.out.println("dao insert order successful.");
        return 1;
    }
}
