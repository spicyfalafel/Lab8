package com.itmo.app.controllers;

import com.itmo.app.UIApp;
import com.itmo.client.MyConsole;
import com.itmo.collection.DragonForTable;
import com.itmo.collection.MyDragonsCollection;
import com.itmo.collection.dragon.classes.*;
import com.itmo.commands.*;
import com.itmo.utils.Painter;
import com.itmo.utils.UIHelper;
import com.itmo.utils.WindowsCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;



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
    private MenuBar menuBar;
    @FXML
    private Menu helpMenu;
    @FXML
    private MenuItem languageRussianItem, languageEstonianItem,
            languageSwedishItem, languageEspanItem;
    @FXML
    private Text currentUserText;
    @FXML
    private Rectangle colorOfUserRectangle;
    @FXML
    private Canvas xOyCanvas;
    @FXML
    private Button addButton,
            addIfMaxButton, addIfMinButton, clearButton, filterNameButton,
            infoButton, descendingWingspanButton, sortValueButton,
            removeByIdButton, removeLowerThanButton, updateByIdButton,
            executeScriptButton, signOutButton, removeButton;
    @FXML
    private TableView<DragonForTable> dragonsTable;
    @FXML
    private TableColumn<DragonForTable, Long> idColumn;
    @FXML
    private TableColumn<DragonForTable, DragonCharacter> characterColumn;
    @FXML
    private TableColumn<DragonForTable, DragonType> typeColumn;
    @FXML
    private TableColumn<DragonForTable, Float> wingspanColumn;
    @FXML
    private TableColumn<DragonForTable, Integer> ageColumn;
    @FXML
    private TableColumn<DragonForTable, String> dragonNameColumn, creatorColumn, locationNameColumn;
    @FXML
    private TableColumn<DragonForTable, Date> creationDateColumn;

    @FXML
    private TableColumn<DragonForTable, Integer> killerXColumn, xColumn;

    @FXML
    private TableColumn<DragonForTable, Long> killerYColumn, yColumn;

    @FXML
    private TableColumn<DragonForTable, Float> killerZColumn;


    @FXML
    private TableColumn<DragonForTable, Country> killerNationalityColumn;
    @FXML
    private TableColumn<DragonForTable, Color> killerHairColorColumn;
    @FXML
    private TableColumn<DragonForTable, String> killerNameColumn;
    @FXML
    private TableColumn<DragonForTable, LocalDateTime> killerBirthDateColumn;
    @FXML
    private Label commandOutput;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handleCommandButtons();
        handleLanguageMenuItems();
        drawAxis();
        handleHelpItem();
        handleOnClose();
        showUserName();
    }


    public void setDragonsInTable(Set<Dragon> dragons){

    }


    public void handleTableView() {
        ObservableList<DragonForTable> dragons = FXCollections.observableArrayList();
        setUpColumns();
        //dragonsTable.setItems();
    }

    private void setUpColumns() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dragonNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        creationDateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        wingspanColumn.setCellValueFactory(new PropertyValueFactory<>("wingspan"));
        xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));

        characterColumn.setCellValueFactory(new PropertyValueFactory<>("character"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        killerNameColumn.setCellValueFactory(new PropertyValueFactory<>("killerName"));
        killerBirthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthdayInFormat"));
        killerHairColorColumn.setCellValueFactory(new PropertyValueFactory<>("hairColor"));
        killerNationalityColumn.setCellValueFactory(new PropertyValueFactory<>("nationality"));

        locationNameColumn.setCellValueFactory(new PropertyValueFactory<>("locationName"));
        killerXColumn.setCellValueFactory(new PropertyValueFactory<>("killerX"));
        killerYColumn.setCellValueFactory(new PropertyValueFactory<>("killerY"));
        killerZColumn.setCellValueFactory(new PropertyValueFactory<>("killerZ"));

        creatorColumn.setCellValueFactory(new PropertyValueFactory<>("creator"));
    }


    public void showUserName() {
        if (!UIApp.getClient().getUser().getName().equals("unregistered")) {
            currentUserText.setText(UIApp.getClient().getUser().getName());
        }
    }


    private void handleHelpItem() {
        helpMenu.getItems().get(0).setOnAction(e -> {
            //initialization of commands...
            MyConsole console = new MyConsole();
            String help = CommandsInvoker.getInstance().getHelp();
            WindowsCreator.createInfo(help).show();
        });
    }

    private void handleLanguageMenuItems() {
        languageRussianItem.setOnAction(e -> changeLanguageInUI("RU"));
        languageEstonianItem.setOnAction(e -> changeLanguageInUI("EE"));
        languageSwedishItem.setOnAction(e -> changeLanguageInUI("SE"));
        languageEspanItem.setOnAction(e -> changeLanguageInUI("SPA"));
    }

    private void handleOnClose() {
        UIApp.mainStage.setOnCloseRequest(e -> {
            UIApp.getClient().sendCommandToServer(new ExitCommand());
            UIApp.getClient().getAnswerFromServer();
        });
    }


    private void handleCommandButtons() {
        addButton.setOnAction(e -> {
            UIApp.addController.setType(AddController.TypeOfAdd.ADD);
            WindowsCreator.createAddForm().show();
        });

        addIfMaxButton.setOnAction(e -> {
            UIApp.addController.setType(AddController.TypeOfAdd.ADD_IF_MAX);
            WindowsCreator.createAddForm().show();
        });

        addIfMinButton.setOnAction(e -> {
            UIApp.addController.setType(AddController.TypeOfAdd.ADD_IF_MIN);
            WindowsCreator.createAddForm().show();
        });

        updateByIdButton.setOnAction(e -> {
            WindowsCreator.createUpdateById().show();
        });

        infoButton.setOnAction(e -> {
            UIApp.getClient().sendCommandToServer(new InfoCommand());
            String answer = UIApp.getClient().getAnswerFromServer();
            commandOutput.setText(answer);
        });

        removeByIdButton.setOnAction(e -> {
            WindowsCreator.createRemoveById().show();
        });

        clearButton.setOnAction(e -> {
            UIApp.getClient().sendCommandToServer(new ClearCommand());
            String answer = UIApp.getClient().getAnswerFromServer();
            commandOutput.setText(answer);
        });

        signOutButton.setOnAction(e -> {
            UIApp.getClient().sendCommandToServer(new SignOutCommand());
            String answer = UIApp.getClient().getAnswerFromServer();
            UIApp.authorizationStage.show();
            UIApp.mainStage.close();
        });

        removeButton.setOnAction(e -> {
            //todo should remove chosen element in table
        });

    }

    private void drawAxis() {
        GraphicsContext gc = xOyCanvas.getGraphicsContext2D();
        gc.setStroke(javafx.scene.paint.Color.BLACK);
        gc.setLineWidth(2);
        double w = xOyCanvas.getWidth();
        double h = xOyCanvas.getHeight();
        gc.strokeLine(w / 2, 0, w / 2, h);
        gc.strokeLine(0, h / 2, w, h / 2);
        Painter painter = new Painter(xOyCanvas);
        painter.setColor(javafx.scene.paint.Color.RED);
        painter.drawDragonOnCanvas(MyDragonsCollection.generateSimpleDragon());
        painter.drawDragonOnCanvas(200, 200, 40);
    }

    private void changeLanguageInUI(String TAG) {
        UIApp.localeClass.changeLocaleByTag(TAG);
        // TODO changeDateFormat();
        try {
            reloadMainStage();
            UIApp.getClient().sendCommandToServer(
                    new ChangeLanguageCommand(new String[]{TAG})
            );
            String ans = UIApp.getClient().getAnswerFromServer();
            commandOutput.setText(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadMainStage() throws IOException {
        Scene scene = UIApp.mainStage.getScene();
        scene.setRoot(UIHelper.loadFxmlWithController(
                "/fxml/main.fxml",
                UIApp.mainWindowController,
                getClass()));
    }
}