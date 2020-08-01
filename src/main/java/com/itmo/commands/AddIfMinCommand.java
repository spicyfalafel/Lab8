package com.itmo.commands;

import com.itmo.server.Application;
import com.itmo.app.UIApp;
import com.itmo.client.User;
import com.itmo.collection.dragon.classes.Dragon;
import com.itmo.server.notifications.AddNotification;
import com.itmo.utils.FieldsScanner;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class AddIfMinCommand extends Command {
    private Dragon dr;

    public AddIfMinCommand(String[] args) {
        super(args);
    }
    public AddIfMinCommand(Dragon d){
        dr = d;
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
            application.notificationProducer.sendAddNotificationToAll(
                    new AddNotification(dr)
            );
        }
        return application.getCollection().addIfMin(dr);
    }

    @Override
    public String getDescription() {
        return UIApp.localeClass.getString("add_if_min_description.text");
    }
}
