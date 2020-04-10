package com.jetbone.others.design.patterns.structural.adapter.demo;

/**
 * Created by Chris on 2019/8/27
 */
public class PowerAdapter implements DC5 {

    private AC220 ac220 = new AC220();

    @Override
    public int outputDC5() {

        // 假设这里有变压器进行了转换
        int input = ac220.outputAC220();
        int output = input/44;

        System.out.println("input: ac" + input + " convert to : dc" + output);

        return output;
    }
}
