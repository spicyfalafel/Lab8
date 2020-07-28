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
        StackPane root = (StackPane) UIHelper
                .loadFxmlWithController(
                        "/fxml/authorization.fxml",
                        UIApp.authorizationController,
                        WindowsCreator.class
                );
        Stage stage = createBlankStage(root, UIApp.localeClass.getString("auth_title.text"), 745, 380);
        setIconToStage(stage);
        stage.setResizable(false);
        return stage;
    }

    public static Stage createAddForm() throws IOException {
        AnchorPane root = (AnchorPane) UIHelper.loadFxmlWithController(
                "/fxml/add.fxml",
                UIApp.addController,
                WindowsCreator.class
        );
        Stage stage = createBlankStage(root, UIApp.localeClass.getString("add_title.text"));
        stage.setResizable(false);
        setIconToStage(stage);
        return stage;
    }

    public static Stage createRemoveById() throws IOException{

        VBox root = (VBox) UIHelper.loadFxmlWithController(
                "/fxml/remove_by_id.fxml",
                UIApp.removeByIdController,
                UIApp.class);
        Stage stage = createBlankStage(root, UIApp.localeClass.getString("remove_by_id.text"));
        setIconToStage(stage);
        stage.setResizable(false);
        return stage;
    }

    public static Stage createError() throws IOException {
        VBox root = (VBox) UIHelper.loadFxmlWithController(
                "/fxml/error.fxml",
                UIApp.errorController,
                UIApp.class);
        Stage stage = createBlankStage(root, UIApp.localeClass.getString("error.text"));
        setIconToStage(stage);
        stage.setResizable(false);
        return stage;
    }

    public static Stage createInfo(String information) throws  IOException{
        VBox root = (VBox) UIHelper.loadFxmlWithController(
                "/fxml/info.fxml",
                UIApp.informationController,
                UIApp.class);
        UIApp.informationController.infoLabel.setText(information);
        Stage stage = createBlankStage(root, UIApp.localeClass.getString("information.text"));
        setIconToStage(stage);
        stage.setResizable(false);
        return stage;
    }

    public static Stage createBlankStage(Parent layout, String title){
        Scene scene = new Scene(layout);
        Stage newWindow = new Stage();
        newWindow.setTitle(title);
        scene.getStylesheets().add("css/every.css");
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
