package org.example.commands;


import org.example.utils.LabWorkCollection;

import java.util.List;


/**
 * Команда для вывода информации о коллекции.
 */
public class InfoCommand extends Command {
    private final LabWorkCollection labWorkCollection;

    /**
     * Конструктор команды Info.
     *
     * @param labWorkCollection Коллекция, о которой выводится информация.
     */
    public InfoCommand(LabWorkCollection labWorkCollection) {
        this.labWorkCollection = labWorkCollection;
    }

    /**
     * Выводит информацию о коллекции.
     *
     * @return Информация о коллекции.
     */
    @Override
    public String execute(List<Object> args) {
        return labWorkCollection.getInfo();
    }
}
