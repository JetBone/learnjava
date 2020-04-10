package com.jetbone.others.design.principles.liskovsubstitution;

/**
 * Created by Chris on 2019-07-29 09:33.
 */
public class Square1 implements Quadrangle {
    private long sideLength;

    @Override
    public long getWidth() {
        return sideLength;
    }

    @Override
    public long getLength() {
        return sideLength;
    }

    public long getSideLength() {
        return sideLength;
    }

    public void setSideLength(long sideLength) {
        this.sideLength = sideLength;
    }
}
