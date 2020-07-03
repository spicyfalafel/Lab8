package com.itmo.utils;

import lombok.Getter;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleClass {
    @Getter
    public static ResourceBundle resourceBundle = ResourceBundle
            .getBundle("locals", Locale.forLanguageTag("RU"), new UTF8Control());



    public static void changeLocale(Locale locale){
        resourceBundle = ResourceBundle
                .getBundle("locals", locale, new UTF8Control());
        System.out.println(resourceBundle.getLocale().toString() + "!!!!!!!");
    }

    //RU
    // EE = ЭСТОНСКИЙ
    // SE = ШВЕДСКИЙ
    // ES = ИСПАНСКИЙ
    public static void changeLocaleByTag(String TAG){
        changeLocale(Locale.forLanguageTag(TAG));
    }

    public static String getString(String text){
        return resourceBundle.getString(text);
    }
}
