package com.itmo.commands;

import com.itmo.app.Application;
import com.itmo.client.User;
import com.itmo.collection.Dragon;
import com.itmo.utils.FieldsScanner;
import com.itmo.utils.LocaleClass;

import java.util.Date;

public class AddIfMaxCommand extends Command {
    private Dragon dr;

    @Override
    public void clientInsertionFromConsole() {
        FieldsScanner helper = FieldsScanner.getInstance();
        dr = helper.scanDragon();
    }


    public AddIfMaxCommand(String[] args) {
        super(args);
    }

    @Override
    public int getNumberOfRequiredArgs() {
        return 0;
    }

    @Override
    public String execute(Application application, User user) {
        dr.setCreationDate(new Date());
        dr.setOwnerName(user.getName());
        if(application.getCollection().isMax(dr)){
            application.db.insertDragon(dr);
            application.syncWithDB();
        }
        return application.getCollection().addIfMax(dr);
    }

    @Override
    public String getDescription() {
        return LocaleClass.getString("add_if_max_description.text");
    }
}
