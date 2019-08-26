package com.jetbone.design.patterns.structural.facade;

/**
 * Created by Chris on 2019/8/24
 */
public class QualifyService {

    /**
     * 业务层用于校验是否有购买能力
     * @param gift
     * @return
     */
    public boolean isAvailable(Gift gift) {
        System.out.println("costumer can buy : " + gift.getName());

        return true;
    }
}
