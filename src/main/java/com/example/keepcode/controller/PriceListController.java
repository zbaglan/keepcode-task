package com.example.keepcode.controller;

import com.example.keepcode.entity.Country;
import com.example.keepcode.entity.PriceDetail;
import com.example.keepcode.util.CommonUtil;
import com.google.common.collect.Iterables;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PriceListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Country> countriesListView;

    @FXML
    private Button mainMenuButton;

    @FXML
    private Label priceLabel;

    @FXML
    private ListView<PriceDetail> servicesListView;

    @FXML
    private TextField searchTextField;

    private static final String URL = "https://onlinesim.io/price-list";

    private static final Map<String, Map<String, PriceDetail>> countryServicesMap = new HashMap<>();

    @FXML
    void initialize() {

        mainMenuButton.setOnAction(actionEvent -> CommonUtil.openNewWindow(mainMenuButton, "/com/example/keepcode/main-view.fxml"));

        try {
            Document document = getDocument(URL);
            List<Country> countries = getCountries(document);

            CommonUtil.setCountriesInListView(countriesListView, countries);

            servicesListView.getSelectionModel().selectedItemProperty().addListener(observable -> {
                PriceDetail selectedItem = servicesListView.getSelectionModel().getSelectedItem();
                priceLabel.setText(selectedItem.getPrice());
            });

            countriesListView.getSelectionModel().selectedItemProperty().addListener(observable -> {
                Country country = countriesListView.getSelectionModel().getSelectedItem();

                Map<String, PriceDetail> priceDetailMap;
                if (countryServicesMap.containsKey(country.getName())) {
                    priceDetailMap = countryServicesMap.get(country.getName());
                } else {
                    priceDetailMap = getPriceDetailsMap(country);
                    countryServicesMap.put(country.getName(), priceDetailMap);
                }

                CommonUtil.setPriceDetailsListView(servicesListView, searchTextField, priceDetailMap.values());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Country> getCountries(Document document) {
        Elements allCountryNameElements = document.getElementsByClass("country-name");
        return allCountryNameElements.stream()
                .map(element -> {
                    Element parent = element.parent();
                    String idAttr = parent.attributes().get("id");
                    String countryCodeString = idAttr.replace("country-", "");
                    Country country = new Country();
                    country.setId(Integer.valueOf(countryCodeString));
                    country.setName(parent.text());
                    return country;
                })
                .toList();
    }

    private PriceDetail getPriceDetail(Element element) {
        Elements priceNames = element.getElementsByClass("price-name");
        Elements prices = element.getElementsByClass("price-text");
        Element priceName = Iterables.getFirst(priceNames, null);
        Element price = Iterables.getFirst(prices, null);
        PriceDetail detail = new PriceDetail();
        detail.setName(priceName.text());
        detail.setPrice(price.text());
        return detail;
    }

    private Map<String, PriceDetail> getPriceDetailsMap(Country country) {
        String countryServicesUrl = URL + "?country=" + country.getId() + "&type=receive";
        Document servicesDocument = getDocument(countryServicesUrl);
        Elements serviceBlockElements = servicesDocument.getElementsByClass("service-block");

        return serviceBlockElements.stream()
                .map(this::getPriceDetail)
                .collect(Collectors.toMap(PriceDetail::getName, Function.identity()));
    }

    private Document getDocument(String url) {
        WebDriver driver = getDriver();
        driver.get(url);
        String pageSource = driver.getPageSource();
        return Jsoup.parse(pageSource);
    }

    private WebDriver getDriver() {

        File file = Paths.get("drivers\\chromedriver.exe").toFile();
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "headless");

        return new ChromeDriver(options);
    }
}
