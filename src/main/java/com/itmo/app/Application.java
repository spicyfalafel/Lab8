package com.itmo.app;

import com.itmo.collection.MyDragonsCollection;
import com.itmo.server.ActiveUsersHandler;
import com.itmo.database.DatabaseManager;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.nio.channels.SocketChannel;
import java.sql.SQLException;
import java.util.Date;

/*
    the purpose of this class is storing collection
    and handling active users
 */

public class Application implements Serializable {
    @Setter
    @Getter
    private MyDragonsCollection collection;
    private Date date;
    public DatabaseManager db;
    public ActiveUsersHandler activeUsers;

    public Application() throws SQLException {
        db = new DatabaseManager();
        collection = new MyDragonsCollection(db.getCollectionFromDatabase());
        date = new Date();
        activeUsers = ActiveUsersHandler.getInstance();
    }

    public void syncWithDB() {
        try {
            this.collection = new MyDragonsCollection(db.getCollectionFromDatabase());
        } catch (SQLException e) {
            System.out.println("ошибка в db");
        }
    }

    public Application(MyDragonsCollection collection) {
        this.collection = collection;
    }

    public void sendCollectionToClient(SocketChannel channel) {
        //todo
    }
}
