package com.itmo.app.controllers;

import com.itmo.app.UIApp;
import com.itmo.client.MyConsole;
import com.itmo.collection.*;
import com.itmo.commands.*;
import com.itmo.utils.Painter;
import com.itmo.utils.UIHelper;
import com.itmo.utils.WindowsCreator;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;
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
    private MenuBar menuBar;
    @FXML
    private Menu helpMenu;
    @FXML
    private MenuItem languageRussianItem,languageEstonianItem,
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
    private TableColumn<DragonForTable, String> dragonNameColumn, creatorColumn;
    @FXML
    private TableColumn<DragonForTable, Date> creationDateColumn;

    @FXML
    private TableColumn<DragonForTable, String> locationColumn;
    @FXML
    private TableColumn<DragonForTable, Country> killerNationalityColumn;
    @FXML
    private TableColumn<DragonForTable, com.itmo.collection.Color> killerHairColorColumn;
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
        if (!UIApp.getClient().getUser().getName().equals("unregistered")) showUserName();
    }

    public void showUserName(){
        currentUserText.setText(UIApp.getClient().getUser().getName());
    }


    private void handleHelpItem(){
        helpMenu.getItems().get(0).setOnAction(e ->{
            try {
                //initialization of commands...
                MyConsole console = new MyConsole();
                String help = CommandsInvoker.getInstance().getHelp();
                WindowsCreator.createInfo(help).show();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }
    private void handleLanguageMenuItems(){
        languageRussianItem.setOnAction(e -> changeLanguageInUI("RU"));
        languageEstonianItem.setOnAction(e -> changeLanguageInUI("EE"));
        languageSwedishItem.setOnAction(e -> changeLanguageInUI("SE"));
        languageEspanItem.setOnAction(e -> changeLanguageInUI("SPA"));
    }
    private void handleCommandButtons(){
        addButton.setOnAction(e -> {
            try {
                UIApp.addController.setType(AddController.TypeOfAdd.ADD);
                WindowsCreator.createAddForm().show();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        addIfMaxButton.setOnAction(e -> {
            try {
                UIApp.addController.setType(AddController.TypeOfAdd.ADD_IF_MAX);
                WindowsCreator.createAddForm().show();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        addIfMinButton.setOnAction(e -> {
            try {
                UIApp.addController.setType(AddController.TypeOfAdd.ADD_IF_MIN);
                WindowsCreator.createAddForm().show();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        updateByIdButton.setOnAction(e -> {
            try {
                UIApp.addController.setType(AddController.TypeOfAdd.UPDATE);
                WindowsCreator.createAddForm().show();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        infoButton.setOnAction(e -> {
            UIApp.getClient().sendCommandToServer(new InfoCommand());
            String answer = UIApp.getClient().getAnswerFromServer();
            commandOutput.setText(answer);
        });

        removeByIdButton.setOnAction(e -> {
            try{
                WindowsCreator.createRemoveById().show();
            }catch (IOException ex){
                ex.printStackTrace();
            }
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
        painter.drawDragonOnCanvas(new MyDragonsCollection().generateSimpleDragon());
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