package com.itmo.server;

import com.itmo.client.User;
import lombok.Getter;

import java.util.HashSet;

public class ActiveUsersHandler {

    @Getter
    private HashSet<String> activeUsers = new HashSet<>();
    private static ActiveUsersHandler handler=null;

    public static ActiveUsersHandler getInstance(){
        if(handler==null) return new ActiveUsersHandler();
        else return handler;
    }
    private ActiveUsersHandler(){
    }

    public void printActiveUsers(){
        activeUsers.forEach(System.out::println);
    }


    public boolean containsUserName(String name) {
        return activeUsers.stream().anyMatch(user -> user.equals(name));
    }

    //добавляем пользователя в активные
    public void addUserName(String username) {
        activeUsers.add(username);
    }
    //удаление пользователя из активных
    public void removeUserByName(String userName) {
        activeUsers.remove(userName);
    }
}