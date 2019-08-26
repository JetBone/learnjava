package com.jetbone.design.patterns.structural.facade;

/**
 * Created by Chris on 2019/8/26
 */
public class ShippingService {
    public String shipGift(Gift gift) {
        System.out.println(gift.getName() + " shipping");
        String shippingNo = "123456";
        return shippingNo;
    }
}
