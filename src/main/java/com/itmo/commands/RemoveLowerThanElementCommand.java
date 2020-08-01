package com.itmo.commands;

import com.itmo.server.Application;
import com.itmo.app.UIApp;
import com.itmo.client.User;
import com.itmo.collection.dragon.classes.Dragon;
import com.itmo.utils.FieldsScanner;

public class RemoveLowerThanElementCommand extends Command {

    public RemoveLowerThanElementCommand(String[] args) {
        super(args);
    }

    @Override
    public int getNumberOfRequiredArgs() {
        return 0;
    }

    private Dragon dr;
    @Override
    public void clientInsertionFromConsole() {
        FieldsScanner helper = FieldsScanner.getInstance();
        dr = helper.scanDragon();
    }

    @Override
    public String execute(Application application, User user) {
        // todo notifications
        return application.getCollection().removeLower(dr, user);
    }

    @Override
    public String getDescription() {
        return UIApp.localeClass.getString("remove_all_elements_less_than.text");
    }
}
