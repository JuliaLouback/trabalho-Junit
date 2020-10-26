package main.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public abstract class MaskFieldUtil {

    
    public static void cpfField(TextField textField) {
        MaskFieldUtil.maxField(textField, 14);
        textField.lengthProperty().addListener((observableValue, number, number2) -> {
            String value = textField.getText();
            value = value.replaceAll("[^0-9]", "");
            value = value.replaceFirst("(\\d{3})(\\d)", "$1.$2");
            value = value.replaceFirst("(\\d{3})(\\d)", "$1.$2");
            value = value.replaceFirst("(\\d{3})(\\d)", "$1-$2");
            try {
	            textField.setText(value);
	            MaskFieldUtil.positionCaret(textField);
            }catch(Exception ex){
            }
        });
     }

    private static void positionCaret(TextField textField) {
        Platform.runLater(() -> {
            if (textField.getText().length() != 0) {
                textField.positionCaret(textField.getText().length());
            }
        });
    }

    public static void maxField(TextField textField, Integer length) {
        textField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue == null || newValue.length() > length) {
                textField.setText(oldValue);
            }
        });
    }

    public static String onlyDigitsValue(TextField field) {
        String result = field.getText();
        if (result == null) {
            return null;
        }
        return result.replaceAll("[^0-9]", "");
    }

    public static String onlyAlfaNumericValue(TextField field) {
        String result = field.getText();
        if (result == null) {
            return null;
        }
        return result.replaceAll("[^0-9]", "");
    }


    

}
