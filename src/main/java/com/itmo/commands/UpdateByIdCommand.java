package com.itmo.commands;

import com.itmo.app.UIApp;
import com.itmo.exceptions.NotYourPropertyException;
import com.itmo.app.Application;
import com.itmo.client.User;
import com.itmo.collection.Dragon;
import com.itmo.utils.FieldsScanner;

public class UpdateByIdCommand extends Command{

    public UpdateByIdCommand(String[] args) {
        super(args);
    }

    @Override
    public int getNumberOfRequiredArgs() {
        return 1;
    }
    private Dragon dr;

    @Override
    public void clientInsertionFromConsole() {
        FieldsScanner fieldsScanner = FieldsScanner.getInstance();
        dr = fieldsScanner.scanDragon();
    }

    /**
     * апдейтит дракона по указанному id.
     * */
    @Override
    public String execute(Application application, User user) {
        try{
            long id = Long.parseLong(args[0].trim());
            Dragon prev = application.getCollection().findById(id);
            if(prev!=null){
                application.getCollection().remove(prev, user);
                dr.setId(id);
                application.getCollection().add(dr);
                return UIApp.localeClass.getString("dragon_was_added.text");
            }else{
                return UIApp.localeClass.getString("dragon_with_id.text")
                        +" " + id + UIApp.localeClass.getString("not_found.text");
            }
        }catch (NumberFormatException e){
            return UIApp.localeClass.getString("id_is_a_number.text");
        }catch (NotYourPropertyException e){
            return UIApp.localeClass.getString("propriety.text") + e.getMessage();
        }
    }

    @Override
    public String getDescription() {
        return UIApp.localeClass.getString("update_element_by_its_id.text");
    }
}