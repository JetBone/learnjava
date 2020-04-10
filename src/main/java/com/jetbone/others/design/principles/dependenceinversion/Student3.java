package com.jetbone.others.design.principles.dependenceinversion;

/**
 * Created by Chris on 2019-07-17 21:50.
 */
public class Student3 {
    private Study study;

    public Student3() {
    }

    public Student3(Study study) {
        this.study = study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public void study() {
        study.study();
    }
}
