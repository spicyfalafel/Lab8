package com.itmo.app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lombok.Setter;
import java.net.URL;
import java.util.ResourceBundle;

public class InformationController implements Initializable {
    @FXML
    private Label infoLabel;

    @Setter
    private static String text="aaaaaaaa";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        infoLabel.setText(text);
    }
}
