package com.jetbone.others.design.principles.liskovsubstitution;

/**
 * Created by Chris on 2019-07-29 09:17.
 */
public class Square extends Ranctangle {
    private long sideLength;

    public long getSideLength() {
        return sideLength;
    }

    public void setSideLength(long sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public long getLength() {
        return sideLength;
    }

    @Override
    public void setLength(long length) {
        this.sideLength = length;
    }

    @Override
    public long getWidth() {
        return sideLength;
    }

    @Override
    public void setWidth(long width) {
        this.sideLength = width;
    }
}
