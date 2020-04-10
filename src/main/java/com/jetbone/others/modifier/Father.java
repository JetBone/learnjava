package com.jetbone.others.modifier;

/**
 * Created by Chris on 2019/9/1
 */
public class Father {
    private String privateString = "father private field";
    String defaultString = "father default(package-private) field";
    protected String protectedString = "father protected field";
    public String publicString = "father public field";

    private void privateMethod() {
        System.out.println("father private method");
    }

    void defaultMethod() {
        System.out.println("father default(package-private) method");
    }

    protected void protectedMethod() {
        System.out.println("father protected method");
    }

    public void publicMethod() {
        System.out.println("father public method");
    }

}
