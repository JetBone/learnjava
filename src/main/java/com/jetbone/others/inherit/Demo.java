package com.jetbone.others.inherit;

/**
 * Created by Chris on 2019-05-13 10:30.
 */
public class Demo {
    private Person person;

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }


    public static void main(String[] args) throws Exception {

        Father1 father1 = new Father1();

        Son1 son1  = new Son1();

        System.out.println();

        // 属性有继承，但是没有重写这个说法，
        // 不管是用private修饰还是用public修饰都会被继承，换句话说和修饰符没关系
        // 如果属性名字相同并不会有覆盖这种操作，而是共存，具体打断点即可发现

        // 一般情况下，我们是可以当作覆盖看待的，
        // 如果子类同名属性用private修饰(父类随意什么修饰)，我们一般也会调用子类的get和set(如果子类的get和set是继承父类的，那么实际调用的是父类的属性)，即使父类有相同的属性，也不会被用到，所以没感觉
        // 如果子类同名属性用public修饰(父类随意什么修饰)，最终使用的是谁的属性是根据引用类型来决定
        // 说了这么多一般都是不会遇到的，我们可以看作是覆盖操作即可(当然实际上不是覆盖操作)
        // 但是在某些情况，比如父类与子类相同的属性上，都增加了@NotNull注解，并且该子类用于接收RequestBody的信息，此时，相同属性只会赋值给子类，父类相同名称的属性就为null了
        // 但是@NotNull既会校验子类属性，也会校验父类同名属性，此时就会报错。表现就是前端已经传了参数，但是就是校验不通过的奇怪问题

    }
}
