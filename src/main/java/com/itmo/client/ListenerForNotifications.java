package com.itmo.client;

import com.itmo.commands.SubscribeForNotificationsCommand;
import com.itmo.server.notifications.AddNotification;
import com.itmo.server.notifications.Notification;
import com.itmo.utils.SerializationManager;
import com.itmo.utils.SerializationManager2;
import javafx.concurrent.Task;
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
        SerializationManager2<AddNotification> sm = new SerializationManager2<>();
        while(true){
            try {
                notificationInBytes = c.getBytes();
                if (notificationInBytes!=null){
                    System.out.println("----- получил байты, количество: " + notificationInBytes.length);
                    AddNotification notification = sm.readObject(notificationInBytes);
                    System.out.println("-------вот дракон из этого оповещения: \n" + notification.getDragon());
                    notification.updateData();
                }else { System.out.println("----------notificationInBytes был равен null");}

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
