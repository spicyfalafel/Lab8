package com.itmo.app.controllers;

import com.itmo.app.UIApp;
import com.itmo.commands.RemoveByIdCommand;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RemoveByIdController implements Initializable {

    @FXML
    private Label commandOutput;
    @FXML
    private TextField idField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UIApp.getClient().sendCommandToServer(new RemoveByIdCommand(new String[]{idField.getText()}));
        String answer = UIApp.getClient().getAnswerFromServer();
        commandOutput.setText(answer);
    }
}
