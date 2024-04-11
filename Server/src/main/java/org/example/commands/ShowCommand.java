package org.example.commands;


import org.example.utils.LabWorkCollection;

import java.util.List;


/**
 * Команда для отображения всех элементов коллекции.
 */
public class ShowCommand extends Command {
    private final LabWorkCollection labWorkCollection;

    /**
     * Конструктор команды Show.
     *
     * @param labWorkCollection Коллекция для отображения.
     */
    public ShowCommand(LabWorkCollection labWorkCollection) {
        this.labWorkCollection = labWorkCollection;
    }

    /**
     * Отображает все элементы коллекции.
     *
     * @return Строка с представлением всех элементов коллекции.
     */
    @Override
    public String execute(List<Object> args) {
        return labWorkCollection.show();
    }
}
