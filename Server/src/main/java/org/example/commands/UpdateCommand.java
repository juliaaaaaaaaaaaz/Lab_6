package org.example.commands;


import org.example.data.LabWork;
import org.example.utils.LabWorkCollection;

import java.util.List;

/**
 * Команда для обновления объекта LabWork в коллекции по указанному ID.
 * Проверяет аргументы команды и обновляет объект, если он найден.
 */
public class UpdateCommand extends Command {
    private final LabWorkCollection labWorkCollection;

    /**
     * Конструктор команды для обновления объекта.
     *
     * @param labWorkCollection Коллекция, в которой будет обновлен объект.
     */
    public UpdateCommand(LabWorkCollection labWorkCollection) {
        this.labWorkCollection = labWorkCollection;
    }

    /**
     * Выполняет обновление объекта в коллекции.
     *
     * @param args Список аргументов команды. Ожидается ID для обновления и сам объект LabWork.
     * @return Сообщение о результате операции обновления.
     */
    @Override
    public String execute(List<Object> args) {
        if (args.size() < 2 || !(args.get(0) instanceof Long)) {
            return "Invalid arguments for update command.";
        }

        try {
            long id = (Long) args.get(0);
            LabWork updatedLabWork = (LabWork) args.get(1); // Убедитесь, что аргумент действительно LabWork

            boolean updated = labWorkCollection.update(id, updatedLabWork);

            return updated ? "LabWork with ID " + id + " updated successfully." : "LabWork with ID " + id + " not found.";
        } catch (ClassCastException e) {
            return "Error updating lab work: " + e.getMessage();
        }
    }
}
