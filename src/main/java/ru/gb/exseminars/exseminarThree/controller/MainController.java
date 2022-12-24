package ru.gb.exseminars.exseminarThree.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import ru.gb.exseminars.exseminarThree.exception.FailedValidation;
import ru.gb.exseminars.exseminarThree.service.Validate;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public Label task;
    @FXML
    public Button save;
    @FXML
    public TextArea answer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String TASK = "Введите Ваши:\n\n" +
                "- Фамилия Имя Отчество\n" +
                "- дату рождения (строка формата dd.mm.yyyy)\n" +
                "- номер телефона (целое беззнаковое число\n" +
                "  без форматирования)\n" +
                "- пол (символ латиницей f или m)\n\n" +
                "в произвольном порядке, разделенный пробелом";
        task.setText(TASK);
    }

    public void saveClicked() {
        Validate validate = new Validate(answer.getText());
        try {
            validate.start();
            answer.clear();
        } catch (FailedValidation e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getLocalizedMessage());
            alert.showAndWait();
            answer.clear();
        }
    }
}