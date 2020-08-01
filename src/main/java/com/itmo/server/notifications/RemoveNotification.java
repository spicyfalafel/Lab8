package com.itmo.server.notifications;

import com.itmo.app.controllers.MainWindowController;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public class RemoveNotification extends Notification {
    private Long id;

    @Override
    public void updateData() {
        /*mainController.getStudyGroups().remove(studyGroupForUITable);
        mainController.getPainter().drawWithRemoving(studyGroupForUITable, mainController.getStudyGroups());*/
    }
}
