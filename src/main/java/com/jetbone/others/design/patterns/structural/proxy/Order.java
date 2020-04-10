package com.jetbone.others.design.patterns.structural.proxy;

/**
 * Created by Chris on 2019/8/31
 */
public class Order {
    private Object orderInfo;
    private Long userId;

    public Object getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(Object orderInfo) {
        this.orderInfo = orderInfo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
