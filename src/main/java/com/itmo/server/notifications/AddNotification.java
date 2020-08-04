package com.itmo.server.notifications;

import com.itmo.app.UIApp;
import com.itmo.collection.DragonForTable;
import com.itmo.collection.dragon.classes.Dragon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
public class AddNotification implements Serializable, Notification{
    @Setter @Getter
    private Dragon dragon;
    @Override
    public void updateData() {
        //todo
        UIApp.mainWindowController.addDragonToColl(dragon);
        UIApp.mainWindowController.painter.drawDragonOnCanvas(dragon, dragon.getUser().getColor());
    }
}
