package com.itmo.commands;

import com.itmo.collection.dragon.classes.Dragon;
import com.itmo.server.Application;
import com.itmo.app.UIApp;
import com.itmo.client.User;
import com.itmo.server.notifications.RemoveNotification;
import lombok.NoArgsConstructor;

import java.util.Set;

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
        Set<Dragon> dragons = application.getCollection().filterOwnDragon(user);
        dragons.forEach(d -> {
            application.notificationProducer.sendRemoveNotificationToAll(new RemoveNotification(d.getId()));
        });
        String ans = application.getCollection().clear(user);
        application.syncWithDB();
        return ans;
    }

    @Override
    public String getDescription() {
        return UIApp.localeClass.getString("clear_collection.text");
    }
}
