package com.jetbone.design.patterns.behavioral.chainofresponsibility;

import org.springframework.util.StringUtils;

/**
 * Created by Chris on 2019/9/3
 */
public class ManagerApprover extends Approver {
    @Override
    protected void approve(Contract contract) {
        if (StringUtils.hasText(contract.getContent())) {
            System.out.println("contract content: " + contract.getContent() + " manager approved");
            if (nextApprover != null) {
                nextApprover.approve(contract);
            }
        } else {
            System.out.println("contract content is empty, manager rejected");
        }
    }
}
