package com.itmo.commands;

import com.itmo.app.Application;
import com.itmo.app.UIApp;
import com.itmo.client.User;
import com.itmo.collection.Dragon;
import com.itmo.server.ServerMain;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;


public class FilterStartsWithNameCommand extends Command {

    public FilterStartsWithNameCommand(String[] args) {
        super(args);
    }

    @Override
    public int getNumberOfRequiredArgs() {
        return 1;
    }

    @Override
    public String execute(Application application, User user) {
        Set<Dragon> res =
                application.getCollection().filterStartsWithName(args[0]);
        if(res.size()!=0){
            StringBuilder builder = new StringBuilder(
                    ServerMain.localeClass.getString("dragons_with_names_start_with.text")
                            + args[0] + ": " + res.size() +"\n");
            res.stream().map(Dragon::getName).forEach(d ->builder.append(d).append("\n"));
            return builder.toString();
        }else{
            return ServerMain.localeClass.getString("dragons_with_names_start_with.text") + args[0]
                    + ServerMain.localeClass.getString("no.text");
        }
    }
    @Override
    public String getDescription() {
        return ServerMain.localeClass.getString("print_elements_which_name_starts_with.text");
    }
}
