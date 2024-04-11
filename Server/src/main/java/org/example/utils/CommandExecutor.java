package org.example.utils;

import org.example.commands.Command;
import org.example.commandArguments.CommandArgument;
import org.example.data.LabWork;
import org.example.di.DIContainer;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Выполняет команды, полученные от клиентов или сгенерированные внутренне сервером.
 * Определяет тип команды и ее аргументы, передавая их соответствующему обработчику команд.
 */
public class CommandExecutor {
    private final DIContainer diContainer;

    /**
     * Конструктор создает экземпляр исполнителя команд.
     *
     * @param diContainer Контейнер зависимостей, предоставляющий доступ к командам.
     */
    public CommandExecutor(DIContainer diContainer) {
        this.diContainer = diContainer;
    }

    /**
     * Выполняет команду с заданными аргументами.
     *
     * @param commandName Имя команды для выполнения.
     * @param args        Список аргументов команды.
     * @return Результат выполнения команды в виде строки.
     */
    public String executeCommand(String commandName, List<CommandArgument> args) {
        Command command = diContainer.getCommand(commandName);
        if (command == null) {
            return "Command not found: " + commandName;
        }

        try {
            List<Object> commandArgs = prepareCommandArguments(args);
            // Это изменение обеспечивает, что ID правильно обрабатывается и передаётся в команды.
            return command.execute(commandArgs);
        } catch (Exception e) {
            return "Error executing command: " + e.getMessage();
        }
    }

    /**
     * Подготавливает аргументы команды, преобразуя их из сериализованного вида в объекты,
     * которые могут быть использованы в командах.
     *
     * @param args Список сериализованных аргументов команды.
     * @return Список подготовленных объектов аргументов.
     * @throws Exception Если десериализация аргумента не удалась.
     */
    private List<Object> prepareCommandArguments(List<CommandArgument> args) throws Exception {
        List<Object> commandArgs = new ArrayList<>();
        for (CommandArgument arg : args) {
            switch (arg.getType()) {
                case "LabWork":
                    LabWork labWork = deserializeLabWork(arg.getValue());
                    commandArgs.add(labWork);
                    break;
                case "Long":
                    Long id = Long.parseLong(arg.getValue());
                    commandArgs.add(id);
                    break;
                default:
                    commandArgs.add(arg.getValue());
            }
        }
        return commandArgs;
    }

    /**
     * Десериализует аргумент команды типа LabWork из строки в объект LabWork.
     *
     * @param serialized Сериализованная строка, представляющая объект LabWork.
     * @return Десериализованный объект LabWork.
     * @throws Exception Если десериализация не удалась.
     */
    private LabWork deserializeLabWork(String serialized) throws Exception {
        byte[] data = Base64.getDecoder().decode(serialized);
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            return (LabWork) ois.readObject();
        }
    }
}
