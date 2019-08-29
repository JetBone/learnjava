package com.jetbone.design.patterns.structural.bridge;

/**
 * Created by Chris on 2019/8/29
 */
public class Test {
    public static void main(String[] args) {

        Bank icbcBank1 = new ICBCBank(new DepositAccount());
        Bank icbcBank2 = new ICBCBank(new SavingAccount());

        Bank abcBank1 = new ABCBank(new DepositAccount());
        Bank abcBank2 = new ABCBank(new SavingAccount());

        icbcBank1.openAccount();
        icbcBank2.openAccount();
        abcBank1.openAccount();
        abcBank2.openAccount();

        // 有点像是类适配器模式，将需要被适配的类组合到适配器里，然后调用被适配类的方法
        // 等于说是在适配器里，将方法的实际调用委托给被适配的类的方法
        // 从这个角度来看，都是一种通过组合的方式变相委托方法调用
        // 但是从概念上讲还是有点区别
        // 比如适配器必须要实现被适配的目标接口，目的是为了最终效果看起来是为了"适配"
        // 桥接模式没有所谓的适配目标，只有单纯的委托的感觉
        // 其实感觉，如果自己在不知道桥接模式的情况下，单纯从业务逻辑的角度考虑，也会自然而然的设计出桥接模式的代码
    }
}
