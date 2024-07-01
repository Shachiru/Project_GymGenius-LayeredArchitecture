package lk.ijse.pos.util;

import javafx.scene.control.TextField;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ValidateUtil {
    public static void validation(LinkedHashMap<TextField, Pattern> map2) {
        for (TextField key : map2.keySet()) {
            Pattern pattern = map2.get(key);
            if (pattern.matcher(key.getText()).matches()) {
                removeError(key);
            } else {
                addError(key);
            }
        }
    }

    private static void addError(TextField key) {
        key.setStyle("-fx-border-color: red; -fx-border-radius: 10; -fx-background-radius: 15");

    }

    private static void removeError(TextField key) {
        key.setStyle("-fx-border-color: green; -fx-border-radius: 10; -fx-background-radius: 15");
    }
}