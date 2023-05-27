package com.example.keepcode.util;

import com.example.keepcode.entity.Country;
import com.example.keepcode.entity.PriceDetail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collection;

public class CommonUtil {

    private CommonUtil() {
        throw new IllegalStateException("It's util class");
    }

    public static void openNewWindow(Button button, String path) {
        button.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CommonUtil.class.getResource(path));

        try {
            loader.load();
        } catch (IOException ignored) {
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void setCountriesInListView(ListView<Country> listView, Collection<Country> countries) {
        ObservableList<Country> countryObservableList = FXCollections.observableArrayList(countries);
        listView.setCellFactory(param -> new ListCell<>() {
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
        listView.setItems(countryObservableList);
    }

    public static void setPriceDetailsListView(ListView<PriceDetail> listView, TextField searchTextField, Collection<PriceDetail> priceDetails) {
        ObservableList<PriceDetail> priceDetailObservableList = FXCollections.observableArrayList(priceDetails);
        FilteredList<PriceDetail> filteredList = new FilteredList<>(priceDetailObservableList, b -> true);
        listView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(PriceDetail priceDetail, boolean empty) {
                super.updateItem(priceDetail, empty);
                if (empty || priceDetail == null || priceDetail.getName() == null) {
                    setText(null);
                } else {
                    setText(priceDetail.getName());
                }
            }
        });
        listView.setItems(filteredList);

        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(priceDetail -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCase = newValue.toLowerCase();
                return priceDetail.getName().contains(lowerCase);
            });
        });
    }
}
