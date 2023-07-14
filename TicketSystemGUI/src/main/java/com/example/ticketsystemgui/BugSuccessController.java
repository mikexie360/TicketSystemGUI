package com.example.ticketsystemgui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class BugSuccessController implements Initializable {
    @FXML
    public Label SuccessMessage;
    public static ResourceBundle resourceBundle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SuccessMessage.setText(LanguageManager.getString("BugSuccessfullySubmitted"));
    }
}
