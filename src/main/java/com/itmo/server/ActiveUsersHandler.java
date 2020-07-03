package com.itmo.server;

import com.itmo.client.User;
import lombok.Getter;

import java.util.HashSet;

public class ActiveUsersHandler {
    @Getter
    private HashSet<User> activeUsers = new HashSet<>();
    private static ActiveUsersHandler handler=null;

    public static ActiveUsersHandler getInstance(){
        if(handler==null) return new ActiveUsersHandler();
        else return handler;
    }
    private ActiveUsersHandler(){
    }

    public boolean containsUserName(String name) {
        return activeUsers.stream().anyMatch(user -> user.getName().equals(name));
    }

    //добавляем пользователя в активные
    public void addUser(User user) {
        activeUsers.add(user);
    }
    //удаление пользователя из активных
    public void removeUser(User user) {
        activeUsers.remove(user);
    }
}