package com.jetbone.generics;

import java.io.Serializable;

/**
 * Created by Chris on 2019/9/25
 */
public class Container {

    private Serializable info;

    public Serializable getInfo() {
        return info;
    }

    public void setInfo(Serializable info) {
        this.info = info;
    }
}
