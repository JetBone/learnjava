package com.jetbone.others.design.patterns.behavioral.command;

/**
 * Created by Chris on 2019/9/3
 */
public class Test {
    public static void main(String[] args) {
        Staff staff = new Staff();
        Video javaVideo = new Video("Java Video");
        Video pythonVideo = new Video("Python Video");
        Command openCommand1 = new OpenVideoCommand(javaVideo);
        Command openCommand2 = new OpenVideoCommand(pythonVideo);
        Command closeCommand1 = new CloseVideoCommand(javaVideo);
        Command closeCommand2 = new CloseVideoCommand(pythonVideo);

        staff.addCommand(openCommand1);
        staff.addCommand(openCommand2);
        staff.addCommand(closeCommand1);
        staff.addCommand(closeCommand2);

        staff.executeCommand();

        // 正常应该是员工直接调用video的open或者close
        // 但是这里将执行调用的操作抽象成一个接口，分离出一个操作层
        // 解耦了调用者和实际被调用者
        // 如果Video类新增了方法，直接新增Command的实现类即可完成水平扩展而不用修改Staff

    }
}
