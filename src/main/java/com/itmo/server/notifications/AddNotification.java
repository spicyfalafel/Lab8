package com.itmo.server.notifications;

import com.itmo.app.controllers.MainWindowController;
import com.itmo.collection.DragonForTable;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public class AddNotification implements Notification, Serializable {
    private DragonForTable dragonForTable;

    @Override
    public void updateData() {
        /*mainController.getPainter().drawWithAdding(dragonForTable, mainController.getStudyGroups());
        mainController.addWithCheckingFormat(dragonForTable);*/
    }
}
