package com.itmo.commands;

import com.itmo.app.Application;
import com.itmo.app.UIApp;
import com.itmo.client.User;
import com.itmo.server.ServerMain;
import com.itmo.utils.LocaleClass;

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
        return ServerMain.localeClass.getString("print_in_descending_order.text");
    }
}
