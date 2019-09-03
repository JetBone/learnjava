package com.jetbone.design.patterns.behavioral.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 2019/9/3
 */
public class Staff {
    private List<Command> commands = new ArrayList<>();

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void executeCommand() {
        commands.forEach(Command::execute);
    }
}
