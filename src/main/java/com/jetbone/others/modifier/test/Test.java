package com.jetbone.others.modifier.test;

import com.jetbone.others.modifier.Father;
import com.jetbone.others.modifier.Son;

/**
 * Created by Chris on 2019/9/1
 */
public class Test {
    public static void main(String[] args) {
        Father father = new Father();
        Father son = new Son();
        Father daughter = new Daughter();

        // father fields
        System.out.println(father.publicString);
        // father methods
        father.publicMethod();

        System.out.println("------------------------------------------");

        // son fields
        System.out.println(son.publicString);
        // son methods
        son.publicMethod();

        System.out.println("------------------------------------------");

        // daughter fields
        System.out.println(daughter.publicString);
        // daughter methods
        daughter.publicMethod();

        //---------------------------------------------------//
        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");

        Father father1 = new Father();
        Son son1  = new Son();
        Daughter daughter1 = new Daughter();


        // father fields
        System.out.println(father1.publicString);
        // father methods
        father1.publicMethod();

        System.out.println("------------------------------------------");

        // son fields
        System.out.println(son1.publicString);
        // son methods
        son1.publicMethod();

        System.out.println("------------------------------------------");

        // daughter fields
        System.out.println(daughter1.publicString);
        // daughter methods
        daughter1.protectedMethod();
        daughter1.publicMethod();

    }
}
