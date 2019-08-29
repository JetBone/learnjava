package com.jetbone.design.patterns.structural.bridge;

/**
 * Created by Chris on 2019/8/29
 */
public class DepositAccount implements Account {
    @Override
    public void openAccount() {
        System.out.println("open deposit account");
    }

    @Override
    public void accountType() {
        System.out.println("account type is deposit");
    }
}
