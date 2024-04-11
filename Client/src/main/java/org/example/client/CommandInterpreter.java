package org.example.client;

import org.example.commandArguments.CommandArgument;
import org.example.commandArguments.CommandData;
import org.example.utils.LabWorkReader;
import org.example.data.LabWork;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

/**
 * Интерпретирует пользовательский ввод в команды и аргументы для них.
 */

public class CommandInterpreter {
    private final LabWorkReader labWorkReader;

    public CommandInterpreter(LabWorkReader labWorkReader) {
        this.labWorkReader = labWorkReader;
    }

    /**
     * Интерпретирует введенную пользователем строку в команду и ее аргументы.
     *
     * @param input   Введенная пользователем строка.
     * @param scanner Сканер для считывания дополнительного пользовательского ввода, если необходимо.
     * @return Сформированные данные команды.
     */

    public CommandData interpret(String input, Scanner scanner) {
        String[] parts = input.trim().split("\\s+", 2);
        String commandName = parts[0].toLowerCase();
        List<CommandArgument> arguments = new ArrayList<>();
        String argument = parts.length > 1 ? parts[1] : null;
        LabWork labWork;
        switch (commandName) {
            case "add":
            case "add_if_min":
            case "remove_lower":
            case "remove_greater":
                labWork = labWorkReader.readLabWork(LabWorkReader.generateId(), scanner::nextLine);
                arguments.add(new CommandArgument("labWork", "LabWork", serializeLabWork(labWork)));
                break;
            case "update":
                validateArgumentPresent(argument, "ID is required for update.");
                labWork = labWorkReader.readLabWork(Long.parseLong(argument), scanner::nextLine);
                arguments.add(new CommandArgument("id", "Long", argument));
                arguments.add(new CommandArgument("labWork", "LabWork", serializeLabWork(labWork)));
                break;
            case "remove_by_id":
                validateArgumentPresent(argument, "ID is required for remove_by_id.");
                arguments.add(new CommandArgument("id", "Long", argument));
                break;
            case "execute_script":
                validateArgumentPresent(argument, "File name is required for execute_script.");
                String scriptContent = readScriptFile(argument);
                arguments.add(new CommandArgument("script", "String", scriptContent));
                break;


        }

        return new CommandData(commandName, arguments);
    }

    /**
     * Читает содержимое файла скрипта.
     *
     * @param fileName Имя файла скрипта.
     * @return Содержимое файла скрипта в виде строки.
     * @throws RuntimeException Если чтение файла не удалось.
     */

    private String readScriptFile(String fileName) {
        try {
            return Files.readString(Paths.get(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read script file: " + fileName, e);
        }
    }

    /**
     * Проверяет наличие аргумента команды.
     *
     * @param argument     Аргумент для проверки.
     * @param errorMessage Сообщение об ошибке, если аргумент отсутствует.
     * @throws IllegalArgumentException Если аргумент отсутствует.
     */

    private void validateArgumentPresent(String argument, String errorMessage) {
        if (argument == null || argument.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    /**
     * Сериализует объект LabWork в строку.
     *
     * @param labWork Объект LabWork для сериализации.
     * @return Сериализованное представление объекта LabWork.
     * @throws RuntimeException Если сериализация не удалась.
     */

    private String serializeLabWork(LabWork labWork) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(labWork);
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize LabWork", e);
        }
    }
}
