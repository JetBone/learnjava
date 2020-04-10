package com.jetbone.others.design.patterns.structural.composite;

/**
 * Created by Chris on 2019/8/28
 *
 * 文件不能在进行添加或者删除，所以不需要重写父类的add和remove方法
 */
public class File extends CatalogComponent {

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件大小
     */
    private double size;

    public File(String name, double price) {
        this.name = name;
        this.size = price;
    }

    @Override
    public String getName(CatalogComponent component) {
        return this.name;
    }

    @Override
    public double getSize(CatalogComponent component) {
        return this.size;
    }

    /**
     * 如果是文件，则直接输出文件名和大小
     */
    @Override
    public void print() {
        System.out.println("文件：" + this.name + " 大小：" + size);
    }
}
