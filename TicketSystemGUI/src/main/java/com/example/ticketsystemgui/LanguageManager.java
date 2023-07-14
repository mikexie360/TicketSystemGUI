package com.example.ticketsystemgui;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {
    private static ResourceBundle resourceBundle;

    public static void loadResourceBundle(String language) {
        Locale locale = new Locale(language);
        resourceBundle = ResourceBundle.getBundle("text", locale);
    }

    public static String getString(String key) {
        return resourceBundle.getString(key);
    }
}
