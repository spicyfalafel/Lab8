package com.itmo.commands;

import com.itmo.app.Application;
import com.itmo.app.UIApp;
import com.itmo.client.User;
import com.itmo.server.ServerMain;
import com.itmo.utils.LocaleClass;

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
        return ServerMain.localeClass.getString("clear_collection.text");
    }
}
