package com.itmo.client;

import javafx.scene.paint.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {
    private String name;
    private String hashPass;

    private double red, green, blue;

    public User(String name, String hashPass){
        this.name = name;
        this.hashPass = hashPass;
    }

    public User(String name){
        this.name = name;
    }

    public void setColor(double red, double green, double blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Color getColor(){
        return Color.color(red, green, blue);
    }

}