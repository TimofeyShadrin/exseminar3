package ru.gb.exseminars.exseminarThree.service;

import ru.gb.exseminars.exseminarThree.exception.FailedValidation;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class Validate {

    private final String incoming;

    public Validate(String incoming) {
        this.incoming = incoming;
    }

    public void start() throws FailedValidation {
        List<String> list = Arrays.asList(incoming.trim().split(" "));
        String message;
        if (list.size() != 6) {
            if (list.size() < 6) {
                message = "Вы ввели данных меньше, чем требуется!";
            } else {
                message = "Вы ввели данных больше, чем требуется!";
            }
            throw new FailedValidation(message);
        }
        try {
            list.stream()
                    .filter(s -> s.length() == 1 &&
                            (s.equalsIgnoreCase("m") || s.equalsIgnoreCase("f")))
                    .findFirst()
                    .orElseThrow();
        } catch (NoSuchElementException e) {
            throw new FailedValidation("Вы не корректно ввели пол");
        }
        try {
            list.stream()
                    .filter(s -> s.matches("\\d{11}"))
                    .findFirst()
                    .orElseThrow();
        } catch (NoSuchElementException e) {
            throw new FailedValidation("Вы не корректно ввели номер телефона");
        }
        try {
            list.stream()
                    .filter(s -> s.matches("\\d{2}.\\d{2}.\\d{4}"))
                    .findFirst()
                    .orElseThrow();
        } catch (NoSuchElementException e) {
            throw new FailedValidation("Вы не корректно ввели дату рождения");
        }
        long count = list.stream()
                .filter(s -> s.length() > 1)
                .filter(s -> s.chars().allMatch(Character::isLetter))
                .count();
        if (count != 3) {
            throw new FailedValidation("Вы не корректно ввели Фамилию Имя Отчество");
        }
    }
}
