package com.itmo.client;

import com.itmo.commands.SubscribeForNotificationsCommand;
import com.itmo.server.notifications.Notification;
import com.itmo.utils.SerializationManager;
import lombok.AllArgsConstructor;

import java.io.IOException;
@AllArgsConstructor
public class ListenerForNotifications extends Thread {
    String host;
    int port;
    @Override
    public void run() {
        Client c = new Client(host, port);
        c.connect();
        byte[] notificationInBytes;
        c.sendCommandToServer(new SubscribeForNotificationsCommand());
        while(true){
            try {
                notificationInBytes = c.getBytes();
                Notification notification = SerializationManager.readObject(notificationInBytes);
                notification.updateData();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
