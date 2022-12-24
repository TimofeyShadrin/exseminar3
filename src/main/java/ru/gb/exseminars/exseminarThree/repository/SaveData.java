package ru.gb.exseminars.exseminarThree.repository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class SaveData {
    private final String data;
    private final String fileName;

    public SaveData(String fileName, String data) {
        this.data = data;
        this.fileName = fileName;
    }

    public void writeFile() throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName + ".txt", true)) {
            String result = Arrays.stream(data.trim().split(" "))
                    .map(s -> "<" + s + ">")
                    .reduce("", (s, s2) -> s + s2);
            fileWriter.append(result);
            fileWriter.append('\n');
            fileWriter.flush();
        } catch (IOException e) {
            throw new IOException("Файл не найден");
        }
    }
}
