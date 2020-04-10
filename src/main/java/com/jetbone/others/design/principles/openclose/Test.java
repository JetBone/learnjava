package com.jetbone.others.design.principles.openclose;

/**
 * Created by Chris on 2019-07-15 21:50.
 */
public class Test {

    public static void main(String[] args) {
        /**
         * 非常简单的一个接口实现，由ICourse进行框架构建，又JavaCourse进行具体实现
         */
        ICourse javaCourse = new JavaCourse(11, "JavaCourse", 100d);

        /**
         * 在新增需求之后，扩展之后的类，使用当中需要注意类型转换，保证调用新增的方法
         */
        ICourse javaCourse2 = new JavaDiscountCourse(12, "JavaDisCountCourse", 100d);

    }
}
