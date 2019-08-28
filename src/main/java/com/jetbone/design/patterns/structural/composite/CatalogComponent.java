package com.jetbone.design.patterns.structural.composite;

/**
 * Created by Chris on 2019/8/28
 */
public abstract class CatalogComponent {
    public void add(CatalogComponent component) {
        throw new UnsupportedOperationException("不支持添加操作");
    }

    public void remove(CatalogComponent component) {
        throw new UnsupportedOperationException("不支持删除操作");
    }

    public String getName(CatalogComponent component) {
        throw new UnsupportedOperationException("不支持获取名字操作");
    }

    public double getSize(CatalogComponent component) {
        throw new UnsupportedOperationException("不支持获取价格操作");
    }
    public void print() {
        throw new UnsupportedOperationException("不支持打印操作");
    }

}
