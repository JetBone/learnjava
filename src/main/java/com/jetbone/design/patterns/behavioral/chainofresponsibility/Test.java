package com.jetbone.design.patterns.behavioral.chainofresponsibility;


/**
 * Created by Chris on 2019/9/3
 */
public class Test {
    public static void main(String[] args) {

        Approver HRApprover = new HRApprover();
        Approver managerApprover = new ManagerApprover();
        Approver SupervisorApprover = new SupervisorApprover();

        // 应用层创建责任链，可以根据需求决定顺序
        managerApprover.setNextApprover(SupervisorApprover);
        HRApprover.setNextApprover(managerApprover);

        Contract contract = new Contract();

        HRApprover.approve(contract);
        System.out.println("-------------------------------");
        contract.setName("Tom");
        HRApprover.approve(contract);
        System.out.println("-------------------------------");
        contract.setContent("Java");
        HRApprover.approve(contract);
        System.out.println("-------------------------------");
        contract.setPrice("20K");
        HRApprover.approve(contract);
    }
}
