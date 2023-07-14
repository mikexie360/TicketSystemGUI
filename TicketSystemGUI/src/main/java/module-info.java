module com.example.ticketsystemgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires unirest.java;


    opens com.example.ticketsystemgui to javafx.fxml;
    exports com.example.ticketsystemgui;
}