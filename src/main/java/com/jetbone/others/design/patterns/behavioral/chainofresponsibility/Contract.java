package com.jetbone.others.design.patterns.behavioral.chainofresponsibility;

/**
 * Created by Chris on 2019/9/3
 */
public class Contract {
    private String name;
    private String content;
    private String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
