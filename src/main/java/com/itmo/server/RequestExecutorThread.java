package com.itmo.server;

import com.itmo.app.Application;
import com.itmo.client.User;
import com.itmo.commands.Command;
import com.itmo.commands.SubscribeForNotificationsCommand;
import lombok.AllArgsConstructor;

import java.net.Socket;
import java.nio.channels.SocketChannel;

public class RequestExecutorThread extends Thread {
    private final Command command;
    private final SocketChannel channel;
    private final Application application;
    private final User user;

    public RequestExecutorThread(Command command, SocketChannel channel, Application application) {
        this.command = command;
        this.channel = channel;
        this.application = application;
        user = command.getUser();
    }


    @Override
    public void run() {
        if (command != null) {
            if(command instanceof SubscribeForNotificationsCommand){
                application.sendCollectionToClient(channel);

                return;
            }
            String res = ServerMain.localeClass.getString("not_registered.text");
            boolean userIsRegistered = (!user.getName().equals("unregistered") &&
                    application.activeUsers.containsUserName(user.getName()));

            if(command.isNoRightsToExecute() || userIsRegistered){
                res = command.execute(application, user);
            }
            ServerWithThreads.executorService.execute(new GiveResponseTask(channel, application, user, res));
        }
    }
}

