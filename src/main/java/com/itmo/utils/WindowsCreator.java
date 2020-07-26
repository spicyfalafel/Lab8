package com.itmo.utils;

import com.itmo.app.UIApp;
import com.itmo.app.controllers.AddController;
import com.itmo.app.controllers.AuthorizationController;
import com.itmo.app.controllers.InformationController;
import com.itmo.client.Client;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowsCreator {

    public static Stage createAuthorization() throws IOException {
        StackPane root = (StackPane) UIHelper.loadFxml("/fxml/authorization.fxml", WindowsCreator.class);
        Stage stage = createBlankStage(root, LocaleClass.getString("auth_title.text"), 745, 380);
        setIconToStage(stage);
        stage.setResizable(false);
        return stage;
    }

    public static Stage createAddForm() throws IOException {
        AnchorPane root = (AnchorPane) UIHelper.loadFxml("/fxml/add.fxml", WindowsCreator.class);
        Stage stage = createBlankStage(root, LocaleClass.getString("add_title.text"));
        stage.setResizable(false);
        setIconToStage(stage);
        return stage;
    }

    public static Stage createError() throws IOException {
        VBox root = (VBox) UIHelper.loadFxml("/fxml/error.fxml", UIApp.class);
        Stage stage = createBlankStage(root, LocaleClass.getString("error.text"));
        setIconToStage(stage);
        stage.setResizable(false);
        return stage;
    }

    public static Stage createInfo(String information) throws  IOException{
        InformationController.setText(information);
        VBox root = (VBox) UIHelper.loadFxml("/fxml/info.fxml", UIApp.class);
        Stage stage = createBlankStage(root, LocaleClass.getString("information.text"));
        setIconToStage(stage);
        stage.setResizable(false);
        return stage;
    }

    public static Stage createBlankStage(Parent layout, String title){
        Scene scene = new Scene(layout);
        Stage newWindow = new Stage();
        newWindow.setTitle(title);
        scene.getStylesheets().add("css/Auth.css");
        newWindow.setScene(scene);
        return newWindow;
    }

    public static Stage createBlankStage(Parent layout, String title, int w, int h){
        Stage newWindow = createBlankStage(layout, title);
        newWindow.setWidth(w);
        newWindow.setHeight(h);
        return newWindow;
    }

    public static void setIconToStage(Stage stage){
        Image icon = UIHelper.getImage("/images/icons/icon_mortal_kombat.png", WindowsCreator.class);
        stage.getIcons().add(icon);
    }
}
