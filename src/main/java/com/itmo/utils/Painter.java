package com.itmo.utils;

import com.itmo.collection.dragon.classes.Dragon;
import com.itmo.collection.dragon.classes.DragonType;
import com.itmo.server.notifications.Notification;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.concurrent.ArrayBlockingQueue;


public class Painter {

    final double HEIGHT_LESS_THAN_WIDTH_KF = 0.7;
    final int MAX_WIDTH_OVAL = 200;
    final int MIN_WIDTH_OVAL = 30;
    final int MAX_HEIGHT_OVAL = (int) Math.round(MAX_WIDTH_OVAL*HEIGHT_LESS_THAN_WIDTH_KF);
    final int MIN_HEIGHT_OVAL = (int) Math.round(MIN_WIDTH_OVAL*HEIGHT_LESS_THAN_WIDTH_KF);

    private Color colorOfDragon = Color.ORANGE;
    private Canvas canvas;
    private GraphicsContext gc;

    private ArrayBlockingQueue<Notification> notificationsQueue = new ArrayBlockingQueue<>(1000);

    public void addNotification(Notification notification){
        notificationsQueue.add(notification);
    }

    public Painter(Canvas canvas){
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
    }



    public void drawAxis() {
        gc.setStroke(javafx.scene.paint.Color.BLACK);
        gc.setLineWidth(2);
        double w = canvas.getWidth();
        double h = canvas.getHeight();
        gc.strokeLine(w / 2, 0, w / 2, h);
        gc.strokeLine(0, h / 2, w, h / 2);

        //y arrow
        gc.strokeLine(w/2, 0, w/2-5,5);
        gc.strokeLine(w/2, 0, w/2+5,5);
        //x arrow
        gc.strokeLine(w, h/2, w-5, h/2+5);
        gc.strokeLine(w, h/2, w-5, h/2-5);

        drawStepsX((int)w/15);
        drawStepsY((int)h/9);
    }

    public void drawStepsX(int step){
        double w = canvas.getWidth(); //1200
        int currentX = (int) w;
        double h = canvas.getHeight();
        int label = ((int) w/2); //600
        while(currentX>0){
            gc.strokeLine(currentX, h/2-5, currentX, h/2+5);
            gc.fillText(Integer.toString(label), currentX, h/2+15);
            currentX-=step;
            label-=step;
        }
    }
    public void drawStepsY(int step){
        double w = canvas.getWidth();
        double h = canvas.getHeight();
        int currentY = 0;
        int label = ((int) h/2);
        while(currentY<w){
            gc.strokeLine(w/2-5, currentY, w/2+5, currentY);
            gc.fillText(
                    Integer.toString(label),
                    w/2+5, currentY-3
            );
            currentY+=step;
            label-=step;
        }
    }



    public void drawDragonOnCanvas(Dragon d, Color userColor){
        drawDragonOnCanvas(d.getCoordinates().getX(), d.getCoordinates().getY(), d.getValue(), userColor);
        int x = dragonXToCanvasX(d.getCoordinates().getX());
        long y = dragonYToCanvasY(d.getCoordinates().getY());
        drawFire(x, y, d.getType(), d.getValue());
        System.out.println("drawed in " + d.getCoordinates().getY() + " " + d.getCoordinates().getY());
    }

    // xCenter, yCenter холста отличаются от xCenter, yCenter драконьих
    public void drawDragonOnCanvas(int xCenter, long yCenter, float value, Color userColor){
        System.out.println("drawing dragon with value "+value);
        int x = dragonXToCanvasX(xCenter);
        long y = dragonYToCanvasY(yCenter);
        drawOvalHead(x, y, value, userColor); // body
        drawUshki(x,y,value, userColor.brighter());
    }

    private void drawUshki(int x, long y, float value, Color userColor) {
        double x1, y1, x2, y2, x3, y3;
        int height = valueToHeight(value);
        int width = valueToWidth(value);
        x1 = x;
        y1 =  y - height/2;

        x2 =  Math.round(x +  width*0.15);
        y2 =  Math.round(y - height*0.8);

        x3 =  Math.round(x + width*0.1);
        y3 =  Math.round(y- height*0.4);

        gc.setFill(userColor);
        gc.fillPolygon(
                new double[]{x1, x2, x3},
                new double[]{y1, y2, y3},
                3
        );

        gc.fillPolygon(
                new double[]{
                        x1 + width*0.2,
                        x2 + width*0.2,
                        x3 + width*0.2
                },
                new double[]{
                        y1+height*0.1,
                        y2+height*0.1,
                        y3+height*0.1
                },
                3
        );
    }


    public void drawFire(int x, long y, DragonType type, float value){
        Color fireColor = getColorOfType(type);
        int pastbX = x - valueToWidth(value)/2;
        drawOval(pastbX, y, (int) Math.round(valueToHeight(value)*0.66),
                (int) Math.round(valueToHeight(value)*0.66), fireColor);
        drawOval((int) Math.round(pastbX-valueToWidth(value)*0.2),
                y, (int) Math.round(valueToHeight(value)*0.8), (int) Math.round(valueToHeight(value)*0.8),
                fireColor.brighter());
    }

    public void drawOval(int centerX, long centerY, int xDiameter, int yDiameter, Color color){
        gc.setFill(color);
        gc.fillOval(centerX-xDiameter/2, centerY-yDiameter/2, xDiameter, yDiameter);
    }

    public int valueToHeight(float value){
        int height = Math.round(value*0.007f);
        if(height<=MAX_HEIGHT_OVAL && height>=MIN_HEIGHT_OVAL) return height;
        else{
            if(height<=MIN_HEIGHT_OVAL) return MIN_HEIGHT_OVAL;
            else return MAX_HEIGHT_OVAL;
        }
    }
    public int valueToWidth(float value){
        int width = Math.round(value*0.01f);
        if(width<=MAX_WIDTH_OVAL && width>=MIN_WIDTH_OVAL) return width;
        else{
            if(width<=MIN_WIDTH_OVAL) return MIN_WIDTH_OVAL;
            else return MAX_WIDTH_OVAL;
        }
    }
    public Color getColorOfType(DragonType type){
        if(type==null) return Color.BLACK;
        switch (type){
            case AIR:
                return Color.LIGHTSKYBLUE;
            case FIRE:
                return Color.ORANGE;
            case WATER:
                return Color.BLUE;
            case UNDERGROUND:
                return Color.BROWN;
            default:
                return Color.BLACK;
        }
    }

    public void drawOvalHead(int centerX, long centerY, float value, Color userColor){
        drawOval(centerX, centerY, valueToWidth(value), valueToHeight(value), userColor);
        drawOval((int)Math.round(centerX-valueToWidth(value)*0.2),
                (int)Math.round(centerY-valueToHeight(value)*0.2),
                (int)Math.round(valueToWidth(value)*0.2),
                (int)Math.round(valueToWidth(value)*0.2),
                        userColor.invert());
    }

    public int dragonXToCanvasX(int xCenter){
        return ((int) canvas.getWidth())/2 + xCenter;
    }

    public long dragonYToCanvasY(long yCenter){
        return ((long) canvas.getHeight())/2 -yCenter;
    }
}
