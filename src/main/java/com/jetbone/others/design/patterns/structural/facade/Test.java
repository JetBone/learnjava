package com.jetbone.others.design.patterns.structural.facade;

import java.math.BigDecimal;

/**
 * Created by Chris on 2019/8/26
 */
public class Test {
    public static void main(String[] args) {
        Gift gift = new Gift() {{
           setName("computer");
           setPrice(BigDecimal.valueOf(20));
        }};

        // 对外暴露的服务
        GiftExchangeService exchangeService = new GiftExchangeService();

        // 应用层调用对外暴露的一个接口就能完成兑换礼物的功能
        exchangeService.exchangeGift(gift);
    }
}
