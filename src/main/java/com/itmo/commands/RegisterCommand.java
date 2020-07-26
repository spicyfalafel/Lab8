package com.itmo.commands;

import com.itmo.app.Application;
import com.itmo.client.User;
import com.itmo.utils.FieldsScanner;
import com.itmo.utils.LocaleClass;
import com.itmo.utils.PassEncoder;
import com.itmo.utils.SimplePasswordGenerator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor @AllArgsConstructor
public class RegisterCommand extends Command {

    @Setter
    private String login = null;
    @Setter
    private String pass = null;
    @Override
    public int getNumberOfRequiredArgs() {
        return 0;
    }

    @Override
    public void clientInsertionFromConsole() {
        login = FieldsScanner.getInstance().scanStringNotEmpty(
                LocaleClass.getString("login_for_registration.text")
        );
        System.out.println(
                LocaleClass.getString("your_login.text") + login + ". " +
                LocaleClass.getString("do_you_need_password.text"));
        pass = registerPassword();
    }

    @Override
    public String execute(Application application, User user) {
        if(!application.db.containsUserName(login)){
            pass = new PassEncoder().getHash(pass, null);
            user.setName(login);
            user.setHashPass(pass);
            application.db.insertUser(user);
            return
                    LocaleClass.getString("registration_is_completed.text")
            + LocaleClass.getString("your_login.text") + ": " + user.getName();
        }else return LocaleClass.getString("this_user_already_exists.text");
    }


    private String registerPassword() {
        FieldsScanner fs = FieldsScanner.getInstance();
        boolean yes = fs.scanYN();
        if(yes){
            String passw = fs.scanStringNotEmpty(
                    LocaleClass.getString("password.text") + " " +
                            LocaleClass.getString("or_write_generate_for_autogeneration.text")
            );
            passw = passw.trim().equals("generate") ?
                    new SimplePasswordGenerator(true, true, true, false ).generate(10,10)
                    : passw;
            System.out.println(LocaleClass.getString("your_password.text")
                    +": "+ passw);
            return passw;
        }else{
            return "";
        }
    }
}
