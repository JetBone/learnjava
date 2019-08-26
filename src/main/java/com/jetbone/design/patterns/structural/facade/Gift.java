package com.jetbone.design.patterns.structural.facade;

import java.math.BigDecimal;

/**
 * Created by Chris on 2019/8/24
 */
public class Gift {
    private String name;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
