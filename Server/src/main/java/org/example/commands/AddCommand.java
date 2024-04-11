package org.example.commands;

import org.example.data.LabWork;
import org.example.data.Messages;
import org.example.utils.LabWorkCollection;

import java.util.List;

/**
 * Команда добавления нового объекта LabWork в коллекцию.
 */

public class AddCommand extends Command {
    private final LabWorkCollection labWorkCollection;

    /**
     * Конструктор для команды добавления.
     *
     * @param labWorkCollection Коллекция объектов LabWork, куда будет добавлен новый элемент.
     */

    public AddCommand(LabWorkCollection labWorkCollection) {
        this.labWorkCollection = labWorkCollection;
    }

    /**
     * Выполняет добавление нового объекта LabWork в коллекцию.
     *
     * @param args Список аргументов команды. Ожидается один аргумент - объект LabWork.
     * @return Сообщение об успешном добавлении объекта.
     */

    @Override
    public String execute(List<Object> args) {
        if (args.isEmpty()) {
            throw new IllegalArgumentException("Add command expects 1 argument, but got none.");
        }

        LabWork labWork;
        try {
            labWork = (LabWork) args.get(0);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Argument for add command is not of type LabWork.");
        }

        labWorkCollection.add(labWork);
        return Messages.LAB_WORK_SUCCESS_ADD.getMessage();
    }
}

