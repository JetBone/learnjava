package com.jetbone.design.patterns.behavioral.chainofresponsibility;

import org.springframework.util.StringUtils;

/**
 * Created by Chris on 2019/9/3
 */
public class SupervisorApprover extends Approver {
    @Override
    protected void approve(Contract contract) {
        if (StringUtils.hasText(contract.getPrice())) {
            System.out.println("contract price: " + contract.getPrice() + " supervisor approved");
            if (nextApprover != null) {
                nextApprover.approve(contract);
            }
        } else {
            System.out.println("contract price is empty, supervisor rejected");
        }
    }
}
