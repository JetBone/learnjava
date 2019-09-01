package com.jetbone.modifier;

import com.jetbone.modifier.test.Daughter;

/**
 * Created by Chris on 2019/9/1
 */
public class Test {
    public static void main(String[] args) {
        Father father = new Father();
        Father son = new Son();
        Father daughter = new Daughter();

        // father fields
        System.out.println(father.defaultString);
        System.out.println(father.protectedString);
        System.out.println(father.publicString);
        // father methods
        father.defaultMethod();
        father.protectedMethod();
        father.publicMethod();

        System.out.println("------------------------------------------");

        // son fields
        System.out.println(son.defaultString);
        System.out.println(son.protectedString);
        System.out.println(son.publicString);
        // son methods
        son.defaultMethod();
        son.protectedMethod();
        son.publicMethod();

        System.out.println("------------------------------------------");

        // daughter fields
        System.out.println(daughter.defaultString);
        System.out.println(daughter.protectedString);
        System.out.println(daughter.publicString);
        // daughter methods
        daughter.defaultMethod();
        daughter.protectedMethod();
        daughter.publicMethod();

        //---------------------------------------------------//
        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");

        Father father1 = new Father();
        Son son1  = new Son();
        Daughter daughter1 = new Daughter();


        // father fields
        System.out.println(father1.defaultString);
        System.out.println(father1.protectedString);
        System.out.println(father1.publicString);
        // father methods
        father1.defaultMethod();
        father1.protectedMethod();
        father1.publicMethod();

        System.out.println("------------------------------------------");

        // son fields
        System.out.println(son1.defaultString);
        System.out.println(son1.protectedString);
        System.out.println(son1.publicString);
        // son methods
        son1.defaultMethod();
        son1.protectedMethod();
        son1.publicMethod();

        System.out.println("------------------------------------------");

        // daughter fields
        System.out.println(daughter1.protectedString);
        System.out.println(daughter1.publicString);
        // daughter methods
//        daughter1.protectedMethod();
        daughter1.publicMethod();


        // 测试结果：
        // 1.private = 只允许自己访问/调用
        // 2.default = package-private 只允许同一个包内的类访问/调用
        // 3.protected = 同一个包下则等于default, 不同包下只有子类能访问/调用
        // 4.public = 谁都可以访问
        //
        // 扩展：
        // 1.如果所有的类都在同一个包下，那么protected和default效果一样
        // 2.访问权限根据引用类型判断，而不是实际类型
        // 3.根据第2条，假设我们想调用另外一个包中的类的一个protected方法，可以在当前包中新建一个子类去继承protected方法，
        //   方法内通过super调用父类的protected方法，然后创建一个引用类型是新建的类的实例，
        //   即可在当前包下调用另外一个包中父类的protected方法
        // 4.有权限访问=有权限调用=可以继承
        // 5.继承下来的访问修饰符仍然是一样的


    }
}
