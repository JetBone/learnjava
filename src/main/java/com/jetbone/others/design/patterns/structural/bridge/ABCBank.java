package com.jetbone.others.design.patterns.structural.bridge;

/**
 * Created by Chris on 2019/8/29
 */
public class ABCBank extends Bank {

    public ABCBank(Account account) {
        super(account);
    }

    @Override
    protected void openAccount() {
        System.out.println("open ABC bank account");
        // 委托给account的实现者
        account.openAccount();
    }
}
