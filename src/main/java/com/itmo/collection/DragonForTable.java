package com.itmo.collection;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

public class DragonForTable {

    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой


    private Integer x; //Поле не может быть null
    private long y;

    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int age; //Значение поля должно быть больше 0
    private float wingspan; //Значение поля должно быть больше 0
    private DragonType type; //Поле может быть null
    private DragonCharacter character; //Поле не может быть null

    private String killerName; //Поле не может быть null, Строка не может быть пустой
    private String birthdayInFormat; //Поле не может быть null
    private Color hairColor; //Поле может быть null
    private Country nationality; //Поле может быть null


    private int killerX;
    private Long killerY; //Поле не может быть null
    private Float killerZ; //Поле не может быть null
    private String locationName; //Поле не может быть null


    public DragonForTable(Dragon dragon) {
        this.id = dragon.getId();
        this.name = dragon.getName();
        this.x = dragon.getCoordinates().getX();

        this.y = dragon.getCoordinates().getY();
        this.creationDate = dragon.getCreationDate();
        this.age = dragon.getAge();
        this.wingspan = dragon.getWingspan();

        this.type = dragon.getType();
        this.character = dragon.getCharacter();
        this.killerName = dragon.getKiller().getName();
        this.birthdayInFormat = dragon.getKiller().getBirthdayInFormat();

        this.hairColor = dragon.getKiller().getHairColor();
        this.nationality = dragon.getKiller().getNationality();
        this.killerX = dragon.getKiller().getLocation().getX();
        this.killerY = dragon.getKiller().getLocation().getY();
        this.killerZ = dragon.getKiller().getLocation().getZ();
        this.locationName = dragon.getKiller().getLocation().getName();

    }
}
