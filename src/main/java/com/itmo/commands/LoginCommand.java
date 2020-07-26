package com.itmo.commands;

import com.itmo.app.Application;
import com.itmo.client.User;
import com.itmo.utils.FieldsScanner;
import com.itmo.utils.LocaleClass;
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
        login = FieldsScanner.getInstance().scanStringNotEmpty("логин").trim();
        pass = FieldsScanner.getInstance().scanLine("пароль (нет пароля - Enter)").trim();
    }

    @Override
    public String execute(Application application, User user) {
        User u;
        if(!application.activeUsers.containsUserName(login)){
            String hashPassword = new PassEncoder().getHash(pass, null);
            u = new User(login, hashPassword);
            if(application.db.containsUser(u)){
                application.activeUsers.removeUser(user);
                user.setName(login);
                user.setHashPass(hashPassword);
                application.activeUsers.addUser(user);
                return  LocaleClass.getString("hello.text") + user.getName();
            }else{
                return LocaleClass.getString("already_registered.text");
            }
        }else{
            return LocaleClass.getString("already_on_server.text");
        }
    }

    @Override
    public String getDescription() {
        return "аутенфикация пользователя, требуется для доступа к командам";
    }

    @Override
    public int getNumberOfRequiredArgs() {
        return 0;
    }
}