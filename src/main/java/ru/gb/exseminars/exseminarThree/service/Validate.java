package ru.gb.exseminars.exseminarThree.service;

import ru.gb.exseminars.exseminarThree.exception.FailedValidation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Validate {

    private final String incoming;
    private String surname;

    public Validate(String incoming) {
        this.incoming = incoming;
    }

    public String getSurname() {
        return surname;
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
            LocalDate date = LocalDate.now();
            list.stream()
                    .filter(s -> s.matches("\\d{2}.\\d{2}.\\d{4}"))
                    .filter(s -> LocalDate.parse(s.split("\\.")[2] + "-" +
                            s.split("\\.")[1] + "-" +
                            s.split("\\.")[0],
                                    DateTimeFormatter.ISO_LOCAL_DATE)
                            .isBefore(date))
                    .findFirst()
                    .orElseThrow();
        } catch (NoSuchElementException | DateTimeParseException e) {
            throw new FailedValidation("Вы не корректно ввели дату рождения");
        }
        List<String> result = list.stream()
                .filter(s -> s.length() > 1)
                .filter(s -> s.chars().allMatch(Character::isLetter))
                .collect(Collectors.toList());
        long count = result.size();
        if (count != 3) {
            throw new FailedValidation("Вы не корректно ввели Фамилию Имя Отчество");
        }
        surname = result.get(0);
    }
}
