package com.jetbone.others.generics;

/**
 * Created by Chris on 2019/9/25
 */
public class GenericsUtil {

//    @SuppressWarnings("unchecked")
    public static <T extends Info> T getInfo(Container container) {
        return (T) container.getInfo();
    }
}
