package com.itmo.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;

import java.io.IOException;

public class UIHelper {

    public static Image getImage(String path, Class c) {
        return new Image(c.getResourceAsStream(path));
    }

    public static Parent loadFxml(String path, Class c) throws IOException {
        return FXMLLoader.load(c.getResource(path), LocaleClass.resourceBundle);
    }
}
