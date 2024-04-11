package org.example.commands;

import java.util.List;

/**
 * Абстрактный базовый класс для всех команд, выполняемых сервером.
 */

public abstract class Command {
    /**
     * Выполняет команду с заданными аргументами.
     *
     * @param args Список аргументов команды.
     * @return Результат выполнения команды.
     */

    public abstract String execute(List<Object> args);
}
