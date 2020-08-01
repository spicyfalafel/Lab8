package com.itmo.server.notifications;

import com.itmo.app.controllers.MainWindowController;

import java.io.Serializable;

public abstract class Notification implements Serializable {
    public abstract void updateData();
}
