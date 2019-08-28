package com.jetbone.design.patterns.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chris on 2019/8/28
 */
public class ManagerFactory {
    private static final Map<String, Employee> EMPLOYEE_MAP = new HashMap<>();

    public static Employee getManager(String department) {
        Manager manager = (Manager) EMPLOYEE_MAP.get(department);
        if (manager == null) {
            manager = new Manager("department");
            System.out.println("创建部门经理，部门：" + department);
            String reportContent = "部门：" + department + " 报告内容：.........";
            System.out.println("创建报告：" + reportContent);
            manager.setReportContent(reportContent);
            EMPLOYEE_MAP.put(department, manager);
        }

        return manager;
    }
}
