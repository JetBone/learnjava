package com.jetbone.design.principles.liskovsubstitution;


/**
 * Created by Chris on 2019-07-29 09:32.
 */
public class Ranctangle1 implements Quadrangle {
    private long width;
    private long length;

    @Override
    public long getWidth() {
        return 0;
    }

    @Override
    public long getLength() {
        return 0;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public void setLength(long length) {
        this.length = length;
    }
}
