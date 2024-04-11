package org.example.commandArguments;


import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/**
 * Представляет данные команды, включая её имя и список аргументов.
 */

public record CommandData(String commandName, List<CommandArgument> arguments) implements Serializable {
    /**
     * Конструктор для создания данных команды.
     *
     * @param commandName Имя команды.
     * @param arguments   Список аргументов команды.
     */

    public CommandData {
    }
}
