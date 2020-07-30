package com.itmo.server;

import com.itmo.app.Application;
import com.itmo.client.User;
import com.itmo.commands.Command;
import lombok.AllArgsConstructor;
import java.nio.channels.SocketChannel;
@AllArgsConstructor
public class RequestExecutorThread extends Thread {
    private final Command command;
    private final SocketChannel channel;
    private final Application application;
    private final User user;

    @Override
    public void run() {
        if (command != null) {
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

