package com.itmo.utils;

import com.itmo.collection.dragon.classes.Dragon;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import lombok.Setter;


public class Painter {

    private Canvas canvas;
    @Setter
    private Color color;

    public Painter(Canvas canvas){
        this.canvas = canvas;
    }
    public void drawDragonOnCanvas(Dragon d){
        drawDragonOnCanvas(d.getCoordinates().getX()+ (int) (canvas.getWidth()/2)
                ,(int) (canvas.getHeight()/2)-d.getCoordinates().getY(), d.getValue()*0.001f);
    }

    // x, y холста отличаются от x, y драконьих
    public void drawDragonOnCanvas(int x, long y, float radius){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillOval(x-3*radius, y-3*radius, 6*radius, 6*radius);

    }
}
