module com.example.keepcode {
    requires javafx.controls;
    requires javafx.fxml;
    requires jackson.annotations;
    requires jackson.core;
    requires jackson.databind;
    requires java.sql;
    requires jackson.datatype.jsr310;


    opens com.example.keepcode to javafx.fxml;
    exports com.example.keepcode;
    exports com.example.keepcode.controller;
    exports com.example.keepcode.dto;
    exports com.example.keepcode.entity;
    exports com.example.keepcode.util;
    opens com.example.keepcode.controller to javafx.fxml;
}