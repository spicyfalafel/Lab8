package com.itmo.commands;

import com.itmo.app.Application;
import com.itmo.app.UIApp;
import com.itmo.app.controllers.AuthorizationController;
import com.itmo.app.controllers.MainWindowController;
import com.itmo.client.Client;
import com.itmo.client.User;
import com.itmo.server.ServerMain;
import com.itmo.utils.FieldsScanner;
import com.itmo.utils.PassEncoder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
public class LoginCommand extends Command{
    @Getter @Setter
    private String login = null;
    @Getter @Setter
    private String pass = null;

    @Override
    public void clientInsertionFromConsole() {
        login = FieldsScanner.getInstance().scanStringNotEmpty(
                ServerMain.localeClass.getString("login.text")
        ).trim();
        pass = FieldsScanner.getInstance().scanLine(
                ServerMain.localeClass.getString("password.text")
                + ", " + ServerMain.localeClass.getString("enter_else.text")
        ).trim();
    }

    @Override
    public String execute(Application application, User user) {
        User u;
        if(!application.activeUsers.containsUserName(login)){
            String hashPassword = new PassEncoder().getHash(pass, null);
            u = new User(login, hashPassword);
            if(application.db.containsUser(u)){
                logUser(application, user, hashPassword);
                return  ServerMain.localeClass.getString("hello.text") + ", " + user.getName();
            }else{
                return ServerMain.localeClass.getString("already_registered.text");
            }
        }else{
            return ServerMain.localeClass.getString("already_on_server.text");
        }
    }

    private void logUser(Application application, User user, String hashPassword){
        application.activeUsers.removeUser(user);
        user.setName(login);
        user.setHashPass(hashPassword);
        application.activeUsers.addUser(user);
    }

    @Override
    public String getDescription() {
        return UIApp.localeClass.getString("login_description.text");
    }

    @Override
    public int getNumberOfRequiredArgs() {
        return 0;
    }
}