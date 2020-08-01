package com.itmo.collection;

import com.itmo.collection.dragon.classes.*;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Data;

import java.util.Date;
@Data
public class DragonForTable {


    private SimpleLongProperty id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private SimpleStringProperty name; //Поле не может быть null, Строка не может быть пустой


    private SimpleIntegerProperty x; //Поле не может быть null
    private SimpleLongProperty y;

    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private SimpleIntegerProperty age; //Значение поля должно быть больше 0
    private SimpleFloatProperty wingspan; //Значение поля должно быть больше 0
    private DragonType type; //Поле может быть null
    private DragonCharacter character; //Поле не может быть null

    private SimpleStringProperty  killerName; //Поле не может быть null, Строка не может быть пустой
    private SimpleStringProperty birthdayInFormat; //Поле не может быть null
    private Color hairColor; //Поле может быть null
    private Country nationality; //Поле может быть null


    private SimpleIntegerProperty killerX;
    private SimpleLongProperty killerY; //Поле не может быть null
    private SimpleFloatProperty killerZ; //Поле не может быть null
    private SimpleStringProperty locationName; //Поле не может быть null

    private SimpleStringProperty creator;

    public DragonForTable(Dragon dragon) {
        this.id = new SimpleLongProperty(dragon.getId());
        this.name = new SimpleStringProperty(dragon.getName());
        this.x = new SimpleIntegerProperty(dragon.getCoordinates().getX());

        this.y = new SimpleLongProperty(dragon.getCoordinates().getY());
        this.creationDate = dragon.getCreationDate();
        this.age = new SimpleIntegerProperty(dragon.getAge());
        this.wingspan = new SimpleFloatProperty(dragon.getWingspan());

        this.type = dragon.getType();
        this.character = dragon.getCharacter();
        this.killerName = new SimpleStringProperty(dragon.getKiller().getName());
        this.birthdayInFormat = new SimpleStringProperty(dragon.getKiller().getBirthdayInFormat());

        this.hairColor = dragon.getKiller().getHairColor();
        this.nationality = dragon.getKiller().getNationality();
        this.killerX = new SimpleIntegerProperty(dragon.getKiller().getLocation().getX());
        this.killerY = new SimpleLongProperty(dragon.getKiller().getLocation().getY());
        this.killerZ = new SimpleFloatProperty(dragon.getKiller().getLocation().getZ());
        this.locationName = new SimpleStringProperty(dragon.getKiller().getLocation().getName());

        this.creator = new SimpleStringProperty(dragon.getOwnerName());
    }
}
