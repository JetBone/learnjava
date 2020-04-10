package com.jetbone.others.design.patterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 2019/8/28
 *
 * 目录本身没有大小
 * 假设目录不支持获取大小，则不需要重写父类的获取大小的方法
 */
public class Dictionary extends CatalogComponent {

    /**
     * 目录名字
     */
    private String name;

    /**
     * 目录级别
     */
    private Integer level;

    private List<CatalogComponent> components = new ArrayList<>();

    public Dictionary(String name, Integer level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public void add(CatalogComponent component) {
        components.add(component);
    }

    @Override
    public void remove(CatalogComponent component) {
        components.remove(component);
    }

    @Override
    public String getName(CatalogComponent component) {
        return this.name;
    }

    /**
     * 如果是目录，则循环输出当前目录下的所有文件
     */
    @Override
    public void print() {
        System.out.println("目录：" + this.name);
        for (CatalogComponent component : components) {

            // 这里加空格想让二级目录具有层次感，但是却区分不了三级目录了
//            System.out.print("  ");

            // 在增加了新的属性level之后，通过对level的判断来增加目录层次感
            if (level != null) {
                for (int i = 0; i < this.level; i++) {
                    System.out.print("  ");
                }
            }



            component.print();
        }
    }
}
