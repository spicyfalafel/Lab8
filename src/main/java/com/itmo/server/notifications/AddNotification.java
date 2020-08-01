package com.itmo.server.notifications;

import com.itmo.collection.DragonForTable;
import com.itmo.collection.dragon.classes.Dragon;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public class AddNotification implements Notification, Serializable {
    private Dragon dragon;

    @Override
    public void updateData() {
        //todo
        //UIApp.mainWindowController
        /*mainController.getPainter().drawWithAdding(dragonForTable, mainController.getStudyGroups());
        mainController.addWithCheckingFormat(dragonForTable);*/
    }
}
