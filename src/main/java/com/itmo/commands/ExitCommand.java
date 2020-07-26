package com.itmo.commands;

import com.itmo.app.Application;
import com.itmo.client.User;
import com.itmo.utils.LocaleClass;

public class ExitCommand extends Command {

    public ExitCommand(String[] args) {
        super(args);
    }

    @Override
    public int getNumberOfRequiredArgs() {
        return 0;
    }

    @Override
    public String getDescription() {
        return LocaleClass.getString("exit_program.text");
    }

    @Override
    public String execute(Application application, User user) {
        application.activeUsers.removeUser(user);
        return "byebye";
    }
}