package com.jetbone.others.design.principles.liskovsubstitution;


/**
 * Created by Chris on 2019-07-29 09:18.
 */
public class Test {

    // 业务层代码，对于开发者来说可能是黑盒
    private static void resize(Ranctangle ranctangle) {
        while (ranctangle.getWidth() <= ranctangle.getLength()) {
            ranctangle.setWidth(ranctangle.getWidth() + 1);
            System.out.println("width:" + ranctangle.getWidth() + " length:" + ranctangle.getLength());
        }
        System.out.println("==============================");
    }

    // 重新设计之后的业务层代码，对于开发者来说可能是黑盒
    private static void resize(Ranctangle1 ranctangle) {
        while (ranctangle.getWidth() <= ranctangle.getLength()) {
            ranctangle.setWidth(ranctangle.getWidth() + 1);
            System.out.println("width:" + ranctangle.getWidth() + " length:" + ranctangle.getLength());
        }
        System.out.println("==============================");
    }

    public static void main(String[] args) {

        // 标准的应用层处理
        Ranctangle ranctangle = new Ranctangle();
        ranctangle.setWidth(10);
        ranctangle.setLength(20);
        resize(ranctangle);

        // 这里出现了问题，由于正方形继承自长方形，并且覆盖重写了getter和setter
        // 使用resize会导致死循环，开发者在开发过程当中，也许并不清楚具体业务层的实习那个方式
        // 所以对长方形进行了继承，最终导致了死循环
        // 说明该类的设计思想不正确
        Ranctangle ranctangle1 = new Square();
        ranctangle1.setLength(10);
        resize(ranctangle1);

        // 重新设计之后的标准应用层处理
        Ranctangle1 ranctangle11 = new Ranctangle1();
        ranctangle11.setWidth(10);
        ranctangle11.setLength(20);
        resize(ranctangle11);

        // 重新设计之后，正方形实现了四边形，没有继承长方形
        // 所以无法使用已经有的业务层resize方法，实际上业务层代码只是为长方形准备的
        // 可以避免开发者在不知道业务层具体实现的情况下，写出错误的代码
        // 该设计符合liskov设计思想
        Square1 square = new Square1();
        square.setSideLength(10);
    }
}
