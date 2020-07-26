package com.itmo.app;

import com.itmo.app.controllers.MainWindowController;
import com.itmo.client.Client;
import com.itmo.utils.LocaleClass;
import com.itmo.utils.UIHelper;
import com.itmo.utils.UTF8Control;
import com.itmo.utils.WindowsCreator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/*
    this class starts UI (auth window and main window)
*/

public class UIApp extends Application {
    private static int port; // non-static not working :)
    private static String host; //
    public static ResourceBundle resourceBundle;
    @Setter
    private static boolean userLogged = false;
    public static Stage mainStage;
    public Client client;
    private WindowsCreator windowsCreator;
    public static Stage authorizationStage;
    public void run(String[] args) {
        host = args[0];
        port = Integer.parseInt(args[1]);
        launch();
    }

    @Override
    public void init() throws Exception {
        windowsCreator = new WindowsCreator();
        client = new Client(host, port);
        client.connect();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        resourceBundle = ResourceBundle.getBundle("locals", Locale.forLanguageTag("SE"), new UTF8Control());
        mainStage = primaryStage;
        authorizationStage = windowsCreator.createAuthorization(client);
        authorizationStage.show();
        initPrimaryStage(primaryStage);
        MainWindowController.setClient(client);
    }

    private void initPrimaryStage(Stage primaryStage) throws IOException {
        VBox root = (VBox) UIHelper.loadFxml("/fxml/main.fxml", getClass());
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle(LocaleClass.getString("lab8.text"));
        Image icon = UIHelper.getImage("/images/icons/icon_mortal_kombat.png", getClass());
        primaryStage.getIcons().add(icon);
    }
}