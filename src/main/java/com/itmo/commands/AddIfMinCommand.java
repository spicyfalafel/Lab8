package com.itmo.commands;

import com.itmo.app.Application;
import com.itmo.client.User;
import com.itmo.collection.Dragon;
import com.itmo.server.ServerMain;
import com.itmo.utils.FieldsScanner;

import java.util.Date;

public class AddIfMinCommand extends Command {
    private Dragon dr;

    public AddIfMinCommand(String[] args) {
        super(args);
    }

    @Override
    public int getNumberOfRequiredArgs() {
        return 0;
    }

    @Override
    public void clientInsertionFromConsole() {
        FieldsScanner helper = FieldsScanner.getInstance();
        dr = helper.scanDragon();
    }

    @Override
    public String execute(Application application, User user) {
        dr.setCreationDate(new Date());
        dr.setOwnerName(user.getName());
        if (application.getCollection().isMin(dr)) {
            application.db.insertDragon(dr);
            application.syncWithDB();
        }
        return application.getCollection().addIfMin(dr);
    }

    @Override
    public String getDescription() {
        return ServerMain.localeClass.getString("add_if_min_description.text");
    }
}
