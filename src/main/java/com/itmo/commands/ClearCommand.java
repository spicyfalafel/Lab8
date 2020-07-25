package com.itmo.commands;

import com.itmo.app.Application;
import com.itmo.client.User;

public class ClearCommand extends Command {

    public ClearCommand(String[] args) {
        super(args);
    }

    @Override
    public int getNumberOfRequiredArgs() {
        return 0;
    }

    @Override
    public String execute(Application application, User user) {
        return application.getCollection().clear(user);
    }

    @Override
    public String getDescription() {
        return "очистить коллекцию";
    }
}
