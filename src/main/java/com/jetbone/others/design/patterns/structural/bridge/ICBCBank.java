package com.jetbone.others.design.patterns.structural.bridge;

/**
 * Created by Chris on 2019/8/29
 */
public class ICBCBank extends Bank {

    public ICBCBank(Account account) {
        super(account);
    }

    @Override
    protected void openAccount() {
        System.out.println("open ICBC bank account");
        // 委托给account的实现者
        account.openAccount();
    }
}
