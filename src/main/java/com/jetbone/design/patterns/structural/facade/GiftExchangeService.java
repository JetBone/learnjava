package com.jetbone.design.patterns.structural.facade;

/**
 * Created by Chris on 2019/8/26
 */
public class GiftExchangeService {

    // 这三个service应该是由Spring负责注入的
    // 用于保证应用层无需关心实际服务层的代码
    // 这里直接实例化来模拟spring注入
    private QualifyService qualifyService = new QualifyService();
    private PaymentService paymentService = new PaymentService();
    private ShippingService shippingService = new ShippingService();

    /**
     * 服务层实际对外暴露的兑换礼物接口
     * 实际是需要使用三个service才能完成礼物兑换
     * 但是这里对三个接口进行封装，使得应用层无需知道太多兑换逻辑
     * @param gift
     * @return
     */
    public void exchangeGift(Gift gift) {
        if (qualifyService.isAvailable(gift)) {
            if (paymentService.pay(gift)) {
                String shippingNo = shippingService.shipGift(gift);
                System.out.println("exchange gift successful, shipping no is " + shippingNo);
            }
        }
    }
}
