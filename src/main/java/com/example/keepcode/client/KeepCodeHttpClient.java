package com.example.keepcode.client;

import com.example.keepcode.dto.ResponseCountry;
import com.example.keepcode.dto.ResponseNumberDetail;
import com.example.keepcode.entity.Country;
import com.example.keepcode.entity.NumberDetail;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class KeepCodeHttpClient {

    private static KeepCodeHttpClient instance;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private KeepCodeHttpClient() {
    }

    public static KeepCodeHttpClient getInstance() {
        if (Objects.isNull(instance)) {
            instance = new KeepCodeHttpClient();
        }
        return instance;
    }

    public List<Country> getCountries() {
        String urlString = "https://onlinesim.ru/api/getFreeCountryList";
        String response = doGet(urlString);

        ResponseCountry responseCountry = null;
        try {
            responseCountry = objectMapper.readValue(response, ResponseCountry.class);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return responseCountry.getCountries();
    }

    public List<NumberDetail> getNumberDetails(Integer countryId) {
        String urlString = "https://onlinesim.ru/api/getFreePhoneList?country=" + countryId;
        String responseString = doGet(urlString);

        try {
            ResponseNumberDetail responseNumberDetail = objectMapper.readValue(responseString, ResponseNumberDetail.class);
            return responseNumberDetail.getNumbers();
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private String doGet(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            System.out.println("\nSending 'GET' request to URL : " + urlString);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response);
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
