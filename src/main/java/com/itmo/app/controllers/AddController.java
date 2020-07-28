package com.itmo.app.controllers;

import com.itmo.app.UIApp;
import com.itmo.client.Client;
import com.itmo.collection.*;
import com.itmo.commands.AddElementCommand;
import com.itmo.commands.AddIfMaxCommand;
import com.itmo.commands.AddIfMinCommand;
import com.itmo.commands.Command;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;


public class AddController implements Initializable {
    @FXML
    private TextField dragonNameField, xField,
            yField, wingspanField, ageField, killerNameField,
            birthdateField, locationNameField,
            locationXField, locationYField, locationZField;
    @FXML
    private ChoiceBox<DragonType> typeBox;
    @FXML
    private ChoiceBox<DragonCharacter> characterBox;
    @FXML
    private ChoiceBox<Color> hairColorBox;
    @FXML
    private ChoiceBox<Country> nationalityBox;
    @FXML
    private Button addElementButton;
    @FXML
    private Text stateText;
    @Setter
    private TypeOfAdd type;
    public enum TypeOfAdd{
        ADD,
        ADD_IF_MIN,
        ADD_IF_MAX,
        UPDATE
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeChoiceBoxes();
        initializeButtons();
    }

    void initializeButtons() {
        addElementButton.setOnAction(e -> {
            Dragon dragonFromForm = getDragonFromForm();
            Command command;
            switch (type){
                case ADD_IF_MAX:
                    command = new AddIfMaxCommand(dragonFromForm);
                    break;
                case ADD_IF_MIN:
                    command = new AddIfMinCommand(dragonFromForm);
                    break;
                default:
                case ADD:
                    command = new AddElementCommand(dragonFromForm);
                    break;
                case UPDATE:
                    //TODO
                    command = new AddElementCommand(dragonFromForm);
                    break;
            }
            UIApp.getClient().sendCommandToServer(command);
            String ans = UIApp.getClient().getAnswerFromServer();
            stateText.setText(ans);
        });
    }

    void initializeChoiceBoxes() {
        ObservableList<DragonType> types = FXCollections.observableArrayList(DragonType.values());
        typeBox.setItems(types);
        ObservableList<DragonCharacter> characters = FXCollections.observableArrayList(DragonCharacter.values());
        characterBox.setItems(characters);
        ObservableList<Color> colors = FXCollections.observableArrayList(Color.values());
        hairColorBox.setItems(colors);
        ObservableList<Country> countries = FXCollections.observableArrayList(Country.values());
        nationalityBox.setItems(countries);
    }

    private Dragon getDragonFromForm() {
        String name = dragonNameField.getText();
        int x = Integer.parseInt(xField.getText());
        long y = Long.parseLong(yField.getText());
        float wingspan = Float.parseFloat(wingspanField.getText());
        int age = Integer.parseInt(ageField.getText());
        String killerName = killerNameField.getText();
        String birthdate = birthdateField.getText();
        String locationName = locationNameField.getText();
        int locationX = Integer.parseInt(locationXField.getText());
        Long locationY = Long.parseLong(locationYField.getText());
        Float locationZ = Float.parseFloat(locationZField.getText());

        DragonType type = typeBox.getValue();
        DragonCharacter character = characterBox.getValue();
        Color hairColor = hairColorBox.getValue();
        Country nationality = nationalityBox.getValue();

        Person killer = new Person(killerName, hairColor, nationality,
                new Location(locationX, locationY, locationZ, locationName));
        killer.setBirthdayInFormat(birthdate);
        return new Dragon(name, new Coordinates(x, y),
                age, wingspan, type, character,
                killer);
    }
}