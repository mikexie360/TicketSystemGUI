package com.example.ticketsystemgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class TicketClient extends Application {
    @Override
    public void start(Stage st) throws IOException, Exception {
        Locale currentLocale = new Locale("en");
        ResourceBundle bundle = ResourceBundle.getBundle("text",currentLocale);

        LanguageManager.loadResourceBundle("en");

        FXMLLoader fxmlLoader = new FXMLLoader(TicketClient.class.getResource("hello-view.fxml"),bundle);
        Scene scene = new Scene(fxmlLoader.load(), 1000, 750);
        st.setTitle("Ticket GUI");
        st.setScene(scene);
        st.show();
    }

    public static void main(String[] args) {
        launch();
    }
}