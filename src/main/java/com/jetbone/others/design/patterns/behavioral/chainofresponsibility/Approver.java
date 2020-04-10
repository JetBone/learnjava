package com.jetbone.others.design.patterns.behavioral.chainofresponsibility;


/**
 * Created by Chris on 2019/9/3
 */
public abstract class Approver {

    /**
     * 链式调用的核心
     * 和HashMap中的Node差不多
     */
    protected Approver nextApprover;

    public void setNextApprover(Approver approver) {
        this.nextApprover = approver;
    }

    protected abstract void approve(Contract contract);
}
