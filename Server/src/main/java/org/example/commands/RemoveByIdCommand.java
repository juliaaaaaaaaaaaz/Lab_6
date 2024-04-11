package org.example.commands;


import org.example.utils.LabWorkCollection;

import java.util.List;


/**
 * Команда для удаления объекта LabWork из коллекции по указанному ID.
 * Проверяет, предоставлен ли ID и имеет ли он корректный формат,
 * и выполняет удаление объекта, если он найден в коллекции.
 */


public class RemoveByIdCommand extends Command {

    private final LabWorkCollection labWorkCollection;

    /**
     * Конструктор команды для удаления по ID.
     *
     * @param labWorkCollection Коллекция, из которой будет удален объект.
     */
    public RemoveByIdCommand(LabWorkCollection labWorkCollection) {
        this.labWorkCollection = labWorkCollection;
    }

    /**
     * Выполняет удаление объекта из коллекции.
     *
     * @param args Список аргументов команды. Ожидается, что первый аргумент будет ID объекта для удаления.
     * @return Сообщение об успешном или неуспешном удалении объекта.
     */
    @Override
    public String execute(List<Object> args) {
        if (args.isEmpty() || !(args.get(0) instanceof Long)) {
            return "ID is not provided or has an incorrect format.";
        }

        try {
            long id = (Long) args.get(0);
            boolean removed = labWorkCollection.removeById(id);
            return removed ? "Lab work removed successfully." : "No lab work found with the provided id.";

        } catch (NumberFormatException e) {
            return "Invalid ID format. Please enter a valid number.";
        }

    }
}

