package com.example.keepcode.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ButtonUtil {

    private ButtonUtil() {
        throw new IllegalStateException("It's util class");
    }

    public static void openNewWindow(Button button, String path) {
        button.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ButtonUtil.class.getResource(path));

        try {
            loader.load();
        } catch (IOException ignored) {
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
