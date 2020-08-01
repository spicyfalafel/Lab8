package com.itmo.server.notifications;

import com.itmo.app.UIApp;
import com.itmo.collection.DragonForTable;
import com.itmo.collection.dragon.classes.Dragon;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public class AddNotification extends Notification {
    private final Dragon dragon;

    @Override
    public void updateData() {
        //todo
        UIApp.mainWindowController.addDragonToTable(dragon);
        //UIApp.mainWindowController
        /*mainController.getPainter().drawWithAdding(dragonForTable, mainController.getStudyGroups());
        mainController.addWithCheckingFormat(dragonForTable);*/
    }
}
