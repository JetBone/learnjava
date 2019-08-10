package com.jetbone.design.patterns.creational.abstractfactory;


/**
 * Created by Chris on 2019-08-10 19:25.
 */
public class PythonArticle implements Article {
    @Override
    public void produce() {
        System.out.println("Produce Python Article.");
    }
}
