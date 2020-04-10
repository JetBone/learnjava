package com.jetbone.others.design.patterns.structural.bridge;

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

        // 桥接模式核心是为了将实现和抽象分离？
        // 那么这里，账户类型是实现，银行是抽象
        // 但是为什么不能直接让银行直接实现接口呢，然后像装饰者模式一样，对继承的方法进行调用
        // 想到的一个原因是，也许只需要调用接口中的一个方法，这样直接实现接口就会多出不想实现的方法
        // 二是从业务上来讲，银行和账户本就是两个类型，让银行继承账户接口，岂不是再说银行也是账户类型了
        // 我们可以在桥接模式中体会到一点点装饰者模式的装饰感觉，也能体会到适配器的适配感觉
        // 是因为结构上与二者有部分相似，像是弱化版的装饰者和弱化版的适配器组合而成
        // 过多的解读桥接模式可能不是太好，因为在自己写代码的时候，也许不经意当中就会写成桥接模式
    }
}
