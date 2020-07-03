package com.itmo.app.controllers;

import com.itmo.collection.MyDragonsCollection;
import com.itmo.utils.Painter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

/*
Класс должен контроллировать, что происходит в главном окне:
1) при нажатии на кнопки вызывать соответствующие функции, кнопки делятся на:
    а) команды: влияют на таблицу, могут вызывать другие окошки
    б) команда выход: то же самое, только переход к окно авторизации
    в) сменить язык
    д) что-то еще...
2) как-то обновлять таблицу
3) как-то рисовать драконов
 */

public class MainWindowController implements Initializable {
    @FXML
    private Canvas xOyCanvas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawAxis();
    }

    private void drawAxis(){
        GraphicsContext gc = xOyCanvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        double w = xOyCanvas.getWidth();
        double h = xOyCanvas.getHeight();
        gc.strokeLine(w/2, 0, w/2, h);
        gc.strokeLine(0, h/2, w, h/2);
        Painter painter = new Painter(xOyCanvas);
        painter.setColor(Color.RED);
        painter.drawDragonOnCanvas(new MyDragonsCollection().generateSimpleDragon());
        painter.drawDragonOnCanvas(200, 200, 40);
    }
}