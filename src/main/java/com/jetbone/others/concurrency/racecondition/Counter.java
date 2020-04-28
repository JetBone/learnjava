package com.jetbone.others.concurrency.racecondition;

/**
 * Created by Chris on 2020/4/27
 */
public class Counter {

    protected long count = 0;

    public void count(long value) {
        this.count = this.count + value;
        System.out.println(this.count);
    }

    public Counter() {
    }
}
