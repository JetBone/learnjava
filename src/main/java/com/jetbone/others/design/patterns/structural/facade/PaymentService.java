package com.jetbone.others.design.patterns.structural.facade;

/**
 * Created by Chris on 2019/8/24
 */
public class PaymentService {
    public boolean pay(Gift gift) {
        System.out.println("pay for " + gift.getName() + ", price is " + gift.getPrice());
        return true;
    }
}
