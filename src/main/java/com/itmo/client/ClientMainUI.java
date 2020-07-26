package com.itmo.client;

import com.itmo.app.UIApp;
import com.itmo.app.controllers.AuthorizationController;

/*
    Класс для проверки аргументов программы и её запуска
    в графическом режиме
 */
public class ClientMainUI {

    public static void main(String[] args) {
        UIApp uiApp = new UIApp();
        String[] hostAndPort = checkArgs(args);
        String host = hostAndPort[0];
        int port = Integer.parseInt(hostAndPort[1]);
        AuthorizationController.setClient(new Client(host, port));
        uiApp.run();
    }

    public static String[] checkArgs(String[] args){
        if(args.length==0){
            return new String[]{"localhost", "8080"};
        }
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
