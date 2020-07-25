package com.itmo.commands;

import com.itmo.app.Application;
import com.itmo.client.User;

public class HelpCommand extends Command {

    public HelpCommand(String[] args) {
        super(args);
    }

    @Override
    public int getNumberOfRequiredArgs() {
        return 0;
    }

    @Override
    public void clientInsertionFromConsole() {
        System.out.println(CommandsInvoker.getInstance().printHelp());
    }

    @Override
    public String execute(Application application, User user) {
        return "";
    }

    @Override
    public String getDescription() {
        return "вывести справку по доступным командам";
    }
}
