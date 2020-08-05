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
public class AddIfMaxCommand extends Command {
    private Dragon dr;

    @Override
    public void clientInsertionFromConsole() {
        FieldsScanner helper = FieldsScanner.getInstance();
        dr = helper.scanDragon();
    }

    public AddIfMaxCommand(Dragon d){
        dr = d;
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
            application.getCollectionFromDB();
            application.notificationProducer.sendAddNotificationToAll(
                    new AddNotification(dr)
            );
        }
        return application.getCollection().addIfMax(dr);
    }

    @Override
    public String getDescription() {
        return UIApp.localeClass.getString("add_if_max_description.text");
    }
}
