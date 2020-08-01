package com.itmo.commands;

import com.itmo.server.Application;
import com.itmo.app.UIApp;
import com.itmo.client.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
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
        return UIApp.localeClass.getString("clear_collection.text");
    }
}
