package com.jetbone.design.patterns.behavioral.chainofresponsibility;

import org.springframework.util.StringUtils;

/**
 * Created by Chris on 2019/9/3
 */
public class HRApprover extends Approver {
    @Override
    protected void approve(Contract contract) {
        if (StringUtils.hasText(contract.getName())) {
            System.out.println("contract name: " + contract.getName() + " HR approved");
            if (nextApprover != null) {
                nextApprover.approve(contract);
            }
        } else {
            System.out.println("contract name is empty, HR rejected");
        }
    }
}
