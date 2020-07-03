package com.itmo.app.controllers;

import com.itmo.app.UIApp;
import com.itmo.client.Client;
import com.itmo.commands.LoginCommand;
import com.itmo.commands.RegisterCommand;
import com.itmo.utils.LocaleClass;
import com.itmo.utils.UIHelper;
import com.itmo.utils.UTF8Control;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class AuthorizationController implements Initializable {
    @FXML
    private ImageView dragon_good;
    @FXML
    private ImageView dragon_bad;
    @FXML
    private Button buttonLogin;
    @FXML
    private Button buttonRegister;
    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private SplitMenuButton languageSplitMenu;
    @FXML
    private MenuItem russianLanguageMenuItem;
    @FXML
    private MenuItem estonianLanguageMenuItem;
    @FXML
    private MenuItem swedenLanguageMenuItem;
    @FXML
    private MenuItem spanishLanguageMenuItem;
    @FXML
    private StackPane stackPane;
    @FXML
    private Label labelMessage;

    private String login;
    private String password;
    @Setter
    private static Client client;

    private final EventHandler<ActionEvent> loginButtonHandler = event -> {
        login = loginTextField.getCharacters().toString();
        password = passwordField.getText();
        LoginCommand loginCommand = new LoginCommand(login, password);
        client.sendCommandToServer(loginCommand);
        String ans = client.getAnswerFromServer();
        labelMessage.setText(ans);
        if(ans.startsWith(LocaleClass.getString("hello.text"))){
            UIApp.mainStage.show();
        }
        event.consume();
    };

    private final EventHandler<ActionEvent> registerButtonHandler = event -> {
        login = loginTextField.getCharacters().toString();
        password = passwordField.getText();
        RegisterCommand registerCommand = new RegisterCommand(login, password);
        client.sendCommandToServer(registerCommand);
        String ans = client.getAnswerFromServer();
        labelMessage.setText(ans);
    };

    private final EventHandler<ActionEvent> changeLangToRussian = event -> {
        changeLanguageInUI("RU");
    };

    private final EventHandler<ActionEvent> changeLangToEstonian = event -> {
        changeLanguageInUI("EE");
    };

    private final EventHandler<ActionEvent> changeLangToSweden = event -> {
        changeLanguageInUI("SE");
    };

    private final EventHandler<ActionEvent> changeLangToSpanish = event -> {
        changeLanguageInUI("SPA");
    };

    public void initialize(URL location, ResourceBundle resources) {
        setImages();
        buttonLogin.setOnAction(loginButtonHandler);
        buttonRegister.setOnAction(registerButtonHandler);
        StackPane.setAlignment(languageSplitMenu, Pos.TOP_RIGHT);
        initializeLanguageMenuItems();
    }

    private void changeLanguageInUI(String TAG){
        UIApp.resourceBundle = ResourceBundle
                .getBundle("locals", Locale.forLanguageTag(TAG), new UTF8Control());
        Scene scene = UIApp.authorizationStage.getScene();
        // TODO changeDateFormat();
        try {
            scene.setRoot(FXMLLoader.load(getClass().getResource("/fxml/authorization.fxml"),
                    UIApp.resourceBundle));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeLanguageMenuItems(){
        russianLanguageMenuItem.setOnAction(changeLangToRussian);
        estonianLanguageMenuItem.setOnAction(changeLangToEstonian);
        swedenLanguageMenuItem.setOnAction(changeLangToSweden);
        spanishLanguageMenuItem.setOnAction(changeLangToSpanish);
    }
    private void setImages(){
        Image good = UIHelper.getImage("/images/dragon_good.png", getClass());
        dragon_good.setImage(good);
        Image bad = UIHelper.getImage("/images/dragon_bad11.png", getClass());
        dragon_bad.setImage(bad);
    }
}