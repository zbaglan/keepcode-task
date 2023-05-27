package com.example.keepcode.controller;

import com.example.keepcode.client.KeepCodeHttpClient;
import com.example.keepcode.entity.Country;
import com.example.keepcode.entity.NumberDetail;
import com.example.keepcode.util.ButtonUtil;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class AvailableNumbersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Country> availableNumbersList;

    @FXML
    private ListView<NumberDetail> numberDetailList;

    @FXML
    private Label fullNumberLabel;

    @FXML
    private Label numberLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label updatedDateLabel;

    @FXML
    private Button mainMenuButton;

    private final KeepCodeHttpClient client = KeepCodeHttpClient.getInstance();

    @FXML
    void initialize() {
        List<Country> countries = client.getCountries();
        ObservableList<Country> countryObservableList = FXCollections.observableArrayList(countries);
        availableNumbersList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Country country, boolean empty) {
                super.updateItem(country, empty);
                if (empty || country == null || country.getName() == null) {
                    setText(null);
                } else {
                    setText(country.getName());
                }
            }
        });
        availableNumbersList.setItems(countryObservableList);

        availableNumbersList.getSelectionModel().selectedItemProperty().addListener(this::selectionChanged);

        numberDetailList.getSelectionModel().selectedItemProperty().addListener(observable -> {
            NumberDetail selectedItem = numberDetailList.getSelectionModel().getSelectedItem();
            if (Objects.nonNull(selectedItem)) {
                fullNumberLabel.setText(selectedItem.getFullNumber());
                numberLabel.setText(selectedItem.getNumber());
                statusLabel.setText(selectedItem.getStatus());
                updatedDateLabel.setText(selectedItem.getUpdatedAt());
            }
        });

        mainMenuButton.setOnAction(actionEvent -> ButtonUtil.openNewWindow(mainMenuButton, "/com/example/keepcode/main-view.fxml"));
    }

    private void selectionChanged(Observable observable) {
        Country selectedItem = availableNumbersList.getSelectionModel().getSelectedItem();

        if (Objects.nonNull(selectedItem)) {
            List<NumberDetail> phoneNumbers = client.getNumberDetails(selectedItem.getId());
            ObservableList<NumberDetail> numberDetails = FXCollections.observableArrayList(phoneNumbers);
            numberDetailList.setCellFactory(param -> new ListCell<>() {
                @Override
                protected void updateItem(NumberDetail numberDetail, boolean empty) {
                    super.updateItem(numberDetail, empty);
                    if (empty || numberDetail == null || numberDetail.getNumber() == null) {
                        setText(null);
                    } else {
                        setText(numberDetail.getNumber());
                    }
                }
            });
            numberDetailList.setItems(FXCollections.observableList(numberDetails));
        }
    }
}
