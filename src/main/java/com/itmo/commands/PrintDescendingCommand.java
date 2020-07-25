package com.itmo.commands;

import com.itmo.app.Application;
import com.itmo.client.User;

public class PrintDescendingCommand extends Command {

    public PrintDescendingCommand(String[] args) {
        super(args);
    }

    @Override
    public int getNumberOfRequiredArgs() {
        return 0;
    }

    @Override
    public String execute(Application application, User user) {
        return application.getCollection().printDescending();
    }

    @Override
    public String getDescription() {
        return "вывести элементы коллекции в порядке убывания";
    }
}
