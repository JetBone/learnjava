package com.jetbone.others.design.principles.openclose;

/**
 * Created by Chris on 2019-07-15 22:13.
 */
public class JavaDiscountCourse extends JavaCourse  {

    public JavaDiscountCourse(Integer id, String name, Double price) {
        super(id, name, price);
    }

    /**
     * 新需求，在原有的基础中，需要增加打折后的价格
     * 开闭原则，不能修改架构接口，也不能直接修改已经实现的类
     * 第一种方式：
     * 使用新类来继承已经实现的类，进行扩展开发，重写getPrice方法，或者新增方法等完成扩展实现功能，即为开闭原则
     * @return
     */
    @Override
    public Double getPrice() {
        return super.getPrice();
    }

    /**
     * 第一种方式
     * @return
     */
    public Double getOriginPrice() {
        return super.getPrice();
    }

    /**
     * 第二种方式，
     * 不重写getPrice方法，新增getDiscountPrice方法
     * @return
     */
    public Double getDiscountPrice() {
        return super.getPrice()*0.8;
    }
}
