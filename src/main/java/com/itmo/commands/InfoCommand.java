package com.itmo.commands;


import com.itmo.app.Application;
import com.itmo.app.UIApp;
import com.itmo.client.User;
import com.itmo.server.ServerMain;
import com.itmo.utils.LocaleClass;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InfoCommand extends Command {

    public InfoCommand(String[] args) {
        super(args);
    }

    @Override
    public int getNumberOfRequiredArgs() {
        return 0;
    }




    @Override
    public String getDescription() {
        return ServerMain.localeClass.getString("print_info_description.text");
    }

    @Override
    public String execute(Application application, User user) {
            return application.getCollection().getCollectionInfo();
    }
}
