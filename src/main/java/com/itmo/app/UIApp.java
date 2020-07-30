package com.itmo.app;

import com.itmo.app.controllers.*;
import com.itmo.client.Client;
import com.itmo.client.User;
import com.itmo.utils.LocaleClass;
import com.itmo.utils.UIHelper;
import com.itmo.utils.UTF8Control;
import com.itmo.utils.WindowsCreator;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/*
    this class starts UI (auth window and main window)
*/

public class UIApp extends Application {
    @Setter @Getter
    private static Client client;
    @Getter
    public static MainWindowController mainWindowController;
    @Getter
    public static AuthorizationController authorizationController;
    public static ErrorController errorController;
    public static InformationController informationController;
    public static AddController addController;
    public static RemoveByIdController removeByIdController;
    public static UpdateByIdController updateByIdController;

    public static Stage mainStage;
    public static Stage authorizationStage;

    public static LocaleClass localeClass;
    public static ResourceBundle resourceBundle;

    public void run(){
        launch();
    }


    @Override
    public void init()  {
        authorizationController = new AuthorizationController();
        mainWindowController = new MainWindowController();
        addController = new AddController();
        errorController = new ErrorController();
        informationController = new InformationController();
        removeByIdController = new RemoveByIdController();
        updateByIdController = new UpdateByIdController();
        localeClass = new LocaleClass();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        resourceBundle = ResourceBundle.getBundle("locals", Locale.forLanguageTag("SE"), new UTF8Control());
        mainStage = primaryStage;
        authorizationStage = WindowsCreator.createAuthorization();
        authorizationStage.show();
        initPrimaryStage(primaryStage);
    }


    private void initPrimaryStage(Stage primaryStage) throws IOException {
        VBox root = (VBox) UIHelper
                .loadFxmlWithController("/fxml/main.fxml", mainWindowController, getClass());
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle(localeClass.getString("lab8.text"));
        WindowsCreator.setIconToStage(primaryStage);
    }

    @Override
    public void stop()  {
        client.closeEverything();
    }
}