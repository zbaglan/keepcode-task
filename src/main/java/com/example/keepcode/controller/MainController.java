package com.example.keepcode.controller;

import com.example.keepcode.util.CommonUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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

        priceListButton.setOnAction(actionEvent -> CommonUtil.openNewWindow(priceListButton, "/com/example/keepcode/services.fxml"));
        availableNumbersButton.setOnAction(actionEvent -> CommonUtil.openNewWindow(availableNumbersButton, "/com/example/keepcode/available-numbers.fxml"));

    }

}