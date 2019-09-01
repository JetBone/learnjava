package com.jetbone.design.patterns.behavioral.templatemethod;

/**
 * Created by Chris on 2019/9/1
 */
public class PythonCourse extends Course {

    private boolean needNote = false;

    /**
     * 应用层可以通过该构造器来控制isNeedNote的方法返回值
     * @param needNote
     */
    public PythonCourse(boolean needNote) {
        this.needNote = needNote;
    }

    @Override
    protected void packageCourse() {
        System.out.println("package python source code");
        System.out.println("package python dependencies");
    }

    /**
     * 原本父类的isNeedNote是没法通过外部进行设置的
     * 假设在某种情况下，该值需要对外开放，则这里进行了改造
     * 将isNeedNote的方法返回值交由应用层进行控制
     * @return
     */
    @Override
    protected boolean isNeedNote() {
        return this.needNote;
    }
}
