package com.itmo.commands;

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
                return "Дракон добавлен успешно!";
            }else{
                return "Дракона с id " + id + " в коллекции не нашлось.";
            }
        }catch (NumberFormatException e){
            return "ID - это число";
        }catch (NotYourPropertyException e){
            return "этот дракон принадлежит " + e.getMessage();
        }
    }

    @Override
    public String getDescription() {
        return "обновить значение элемента коллекции, id которого равен заданному";
    }
}