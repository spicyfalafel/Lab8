package com.itmo.commands;

import com.itmo.app.Application;
import com.itmo.client.User;
import com.itmo.collection.Dragon;
import com.itmo.utils.FieldsScanner;

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
        if (application.getCollection().isMax(dr)) {
            dr.setCreationDate(new Date());
            dr.setOwnerName(user.getName());
            application.db.insertDragon(dr);
            application.syncWithDB();
            return application.getCollection().addIfMax(dr);
        } else return "не добавлен т.к. не больший";
    }

    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }
}
