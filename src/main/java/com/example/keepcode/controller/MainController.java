package com.example.keepcode.controller;

import com.example.keepcode.client.KeepCodeHttpClient;
import com.example.keepcode.util.ButtonUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button availableNumbersButton;

    @FXML
    private Button priceListButton;

    @FXML
    void initialize() {

        availableNumbersButton.setOnAction(actionEvent -> ButtonUtil.openNewWindow(availableNumbersButton, "/com/example/keepcode/available-numbers.fxml"));

    }

}