package com.jetbone.design.patterns.creational.singleton;

/**
 * Created by Chris on 2019-08-14
 */
public enum  EnumInstance {

    INSTANCE;

    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    /**
     * 使用枚举类型完成单例模式
     * 枚举类型在序列化和反序列化中，会自动获取其本身的枚举实例
     * 反射方法内部本身(获取构造器的部分)对枚举类型进行了判断和异常抛出，所以也不会破坏单例模式
     * 如果针对枚举类型进行反编译，可以看到实际代码和饿汉式单例模式基本相同，构造方法也是private
     * 通过反编译可以看出，枚举类中的实例都是在加载Class的时候，通过静态代码块儿实例化的
     * 使用枚举类实现单例模式是非常好的一个选择
     * @return
     */
    public EnumInstance getInstance() {
        return INSTANCE;
    }
}
