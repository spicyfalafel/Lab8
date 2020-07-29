package com.itmo.utils;

import com.itmo.collection.Dragon;
import com.itmo.collection.DragonWithStringFields;

import java.sql.Date;
import java.util.Arrays;

public class FieldsValidator {

    public boolean isPositive(Number n) {
        return (n.intValue() > 0);
    }

    public boolean isPositive(String n) {
        if (isInt(n)) {
            return isPositive(Integer.parseInt(n));
        } else if (isLong(n)) {
            return isPositive(Long.parseLong(n));
        } else if (isFloat(n)) {
            return isPositive(Float.parseFloat(n));
        }
        return false;
    }

    public boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isFloat(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isDate(String date) {
        try {
            Date.valueOf(date);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean stringsAreNullOrEmpty(String... str) {
        return Arrays.stream(str).allMatch(this::stringIsNullOrEmpty);
    }

    public boolean stringsAreNotNullOrEmpty(String... strs) {
        return Arrays.stream(strs).noneMatch(this::stringIsNullOrEmpty);
    }

    public boolean stringIsNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }


    public boolean stringIsNotNullOrEmpty(String str) {
        return str != null && !str.isEmpty();
    }

    // killer = null only if every field of killer and location is empty
    public boolean dragonFieldsAreAllFine(DragonWithStringFields d) {
        return stringIsNotNullOrEmpty(d.getName()) &&
                isInt(d.getX()) &&
                isLong(d.getY()) &&
                isPositive(d.getWingspan()) &&
                isInt(d.getAge()) &&
                isPositive(d.getAge()) &&
                ((
                        stringsAreNullOrEmpty(
                                d.getKillerName(),
                                d.getBirthday(),
                                d.getLocName(),
                                d.getKillerX(),
                                d.getKillerY(),
                                d.getKilllerZ()
                        ) &&
                                d.getNationality() == null &&
                                d.getHairColor() == null
                ) ||
                        (
                                stringsAreNotNullOrEmpty(d.getName(), d.getLocName(),
                                        d.getKillerX(), d.getKillerY(), d.getKilllerZ()) &&
                                        isDate(d.getCreationDate()) &&
                                        isInt(d.getKillerX()) && isLong(d.getKillerY()) && isFloat(d.getKilllerZ())

                        ));
    }
}
