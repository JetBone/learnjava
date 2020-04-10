package com.jetbone.others.design.patterns.structural.proxy.dynamicproxy;

import com.jetbone.others.design.patterns.structural.proxy.Order;
import com.jetbone.others.design.patterns.structural.proxy.db.DataSourceContextHolder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Chris on 2019/8/31
 *
 * 与其说这是动态代理类
 * 不如说这是动态生成代理类的类，简称：动态代理生成类
 * 动态代理类是在代码执行当中动态生成的
 * 这里定义了bind方法，只有在调用这个方法的时候，才会动态生成一个代理类并返回
 *
 */
public class DynamicProxy implements InvocationHandler {

    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    /**
     * 这个是调用被代理方法的方法
     * 由于是动态生成的类，并且这个method是属于proxy的
     * 所以想调用动态生成的，类的，method方法，就必须传入proxy对象
     * 具体参照invoke注释
     *
     * @param proxy 动态生成的代理类，即bind方法返回的类
     * @param method 代理的方法
     * @param args 代理的方法的入参
     * @return 代理后的返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object argObject = args[0];

        beforeMethod(argObject);
        Object result = method.invoke(target, args);
        afterMethod();

        return result;
    }

    /**
     * 这个是真正获取代理类的方法
     * @return
     */
    public Object bind() {
        Class clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    /**
     * 在before中进行调用实际业务接口前的操作
     * @param object
     */
    private void beforeMethod(Object object) {
        System.out.println("static proxy, before method");

        int dbRouter = 0;

        // 由于是动态代理，所以要针对传入的类型进行判断和转换
        if (object instanceof Order) {
            Order order = (Order) object;
            // 这里模拟分库操作
            // 在静态代理类当中进行分库，然后调用service层的实际业务操作
            Long userId = order.getUserId();
            dbRouter = userId.intValue() % 2;
        }

        // 模拟设置DB
        DataSourceContextHolder.setDBType(String.valueOf(dbRouter));
        System.out.println("static proxy delegate to db: " + dbRouter + " to resolve data");

    }

    private void afterMethod() {
        System.out.println("static proxy, after method");
    }

}
