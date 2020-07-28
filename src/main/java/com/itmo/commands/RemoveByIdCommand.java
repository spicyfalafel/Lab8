package com.itmo.commands;

import com.itmo.app.UIApp;
import com.itmo.exceptions.NotYourPropertyException;
import com.itmo.app.Application;
import com.itmo.client.User;
public class RemoveByIdCommand extends Command {


    public RemoveByIdCommand(String[] args) {
        super(args);
    }

    @Override
    public int getNumberOfRequiredArgs() {
        return 1;
    }


    @Override
    public String execute(Application application, User user) {
        try{
            long id = Long.parseLong(args[0]);
            if(application.getCollection().removeById(id, user)){
                return UIApp.localeClass.getString("dragon_with_id.text")
                                + args[0] +
                        UIApp.localeClass.getString("was_removed.text");
            }else{
                return UIApp.localeClass.getString("no_such_dragon_with_id.text")
                        + " " + args[0];
            }
        }catch (NumberFormatException e){
            return UIApp.localeClass.getString("id_must_be_more_than_zero.text");
        }catch (NotYourPropertyException e){
            return UIApp.localeClass.getString("propriety.text") + " "+ e.getMessage();
        }
    }

    @Override
    public String getDescription() {
        return UIApp.localeClass.getString("remove_by_id.text");
    }
}
