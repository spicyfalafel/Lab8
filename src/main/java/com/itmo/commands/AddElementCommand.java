package com.itmo.commands;

import com.itmo.app.Application;
import com.itmo.client.User;
import com.itmo.collection.Dragon;
import com.itmo.utils.FieldsScanner;
import com.itmo.utils.LocaleClass;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class AddElementCommand extends Command {

    private Dragon dr=null;

    public AddElementCommand(String[] args) {
    }
    public AddElementCommand(Dragon d){
        dr = d;
    }

    @Override
    public void clientInsertionFromConsole() {
        FieldsScanner fieldsScanner = FieldsScanner.getInstance();
        this.dr = fieldsScanner.scanDragon();
    }

    @Override
    public int getNumberOfRequiredArgs() {
        return 0;
    }


    @Override
    public String execute(Application application, User user){
        dr.setCreationDate(new Date());
        dr.setOwnerName(user.getName());
        application.db.insertDragon(dr);
        application.syncWithDB();
        return LocaleClass.getString("dragon_was_added.text");
    }

    /**
     *
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return LocaleClass.getString("add_new_element_into_collection.text");
    }
}
