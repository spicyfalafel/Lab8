package com.itmo.commands;


import com.itmo.app.Application;
import com.itmo.client.User;
import com.itmo.utils.LocaleClass;

/**
 * The type Info command.
 */
public class InfoCommand extends Command {
    /**
     * Instantiates a new Command.
     *
     */
    public InfoCommand(String[] args) {
        super(args);
    }

    @Override
    public int getNumberOfRequiredArgs() {
        return 0;
    }




    @Override
    public String getDescription() {
        return LocaleClass.getString("print_info_description.text");
    }

    @Override
    public String execute(Application application, User user) {
            return application.getCollection().getCollectionInfo();
    }
}
