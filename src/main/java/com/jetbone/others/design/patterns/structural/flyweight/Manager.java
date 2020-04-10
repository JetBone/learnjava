package com.jetbone.others.design.patterns.structural.flyweight;

/**
 * Created by Chris on 2019/8/28
 */
public class Manager implements Employee {

    private String department;
    private String reportContent;

    @Override
    public void report() {
        System.out.println(reportContent);
    }

    /**
     * 设置报告内容
     * @param reportContent 报告内容
     */
    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    /**
     * 建造一个经理需要一个部门
     * @param department
     */
    public Manager(String department) {
        this.department = department;
    }
}
