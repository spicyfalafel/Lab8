package com.itmo.commands;

import com.itmo.app.Application;
import com.itmo.client.User;
import com.itmo.collection.Dragon;
import com.itmo.utils.FieldsScanner;

import java.util.Date;

public class AddElementCommand extends Command {

    private Dragon dr=null;

    public AddElementCommand(String[] args) {
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
        application.manager.insertDragon(dr);
        application.syncWithDB();
        return "Дракон добавлен успешно!";
    }

    /**
     *
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию";
    }
}
