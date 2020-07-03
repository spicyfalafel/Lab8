package com.itmo.client;

import com.itmo.app.UIApp;

/*
    Класс для проверки аргументов программы и её запуска
    в графическом режиме
 */
public class ClientMainUI {

    public static void main(String[] args) {
        UIApp uiApp = new UIApp();
        uiApp.run(checkArgs(args));
    }

    public static String[] checkArgs(String[] args){
        if(args.length!=2){
            System.out.println("Требуются 2 аргумента: хост и порт");
            System.exit(1);
        }else {
            try {
                Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Не целое число, использую 8080");
                args[1]="8080";
            }
        }
        return args;
    }
}
