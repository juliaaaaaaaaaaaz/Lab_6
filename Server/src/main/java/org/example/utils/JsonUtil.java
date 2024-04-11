package org.example.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.data.Messages;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;

/**
 * Утилита для работы с JSON.
 */
public class JsonUtil {
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateSerializer())
            .create();

    /**
     * Загружает объект из файла JSON.
     *
     * @param fileName Имя файла.
     * @param typeOfT  Тип объекта.
     * @param <T>      Тип данных объекта.
     * @return Загруженный объект.
     */
    public static <T> T loadFromFile(String fileName, Type typeOfT) {
        try (FileReader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, typeOfT);
        } catch (IOException e) {
            System.out.println(Messages.FAILED_LOADING_FROM_FILE.getMessage());
            return null;
        }
    }

    /**
     * Сохраняет объект в файл JSON.
     *
     * @param obj      Объект для сохранения.
     * @param fileName Имя файла.
     * @param <T>      Тип данных объекта.
     */
    public static <T> void saveToFile(T obj, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(obj, writer);
        } catch (IOException e) {
            System.out.println(Messages.FAILED_SAVE_TO_FILE.getMessage());
        }
    }
}
