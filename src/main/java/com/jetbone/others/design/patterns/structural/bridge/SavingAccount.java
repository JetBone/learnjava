package com.jetbone.others.design.patterns.structural.bridge;

/**
 * Created by Chris on 2019/8/29
 */
public class SavingAccount implements Account {
    @Override
    public void openAccount() {
        System.out.println("open saving account");
    }

    @Override
    public void accountType() {
        System.out.println("account type is saving");
    }
}
