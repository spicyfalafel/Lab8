package com.itmo.commands;

import com.itmo.app.Application;
import com.itmo.app.UIApp;
import com.itmo.client.User;
import com.itmo.server.ServerMain;

public class ShowCommand extends Command {

    public ShowCommand(String[] args) {
        super(args);
    }

    @Override
    public int getNumberOfRequiredArgs() {
        return 0;
    }

    public String getDescription() {
        return ServerMain.localeClass.getString("print_all_elements.text");
    }

    @Override
    public String execute(Application application, User user) {
        return application.getCollection().show();
    }

}
