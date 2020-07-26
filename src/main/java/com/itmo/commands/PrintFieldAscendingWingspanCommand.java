package com.itmo.commands;

import com.itmo.app.Application;
import com.itmo.client.User;
import com.itmo.utils.LocaleClass;

public class PrintFieldAscendingWingspanCommand extends Command {

    public PrintFieldAscendingWingspanCommand(String[] args) {
        super(args);
    }

    @Override
    public int getNumberOfRequiredArgs() {
        return 0;
    }

    @Override
    public String execute(Application application, User user) {
        return application.getCollection().printFieldAscendingWingspan();
    }

    @Override
    public String getDescription() {
        return
                LocaleClass.getString("print_wingspan_ordered_by.text");
    }
}
