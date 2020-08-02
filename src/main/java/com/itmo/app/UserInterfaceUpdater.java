package com.itmo.app;

import com.itmo.server.notifications.Notification;

import java.util.concurrent.ArrayBlockingQueue;

public class UserInterfaceUpdater extends Thread {

    private ArrayBlockingQueue<Notification> notificationsQueue = new ArrayBlockingQueue<>(1000);

    @Override
    public void run() {
        while (true) {
            try {
                Notification serverNotification = notificationsQueue.take();
                serverNotification.updateData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }    }
}
