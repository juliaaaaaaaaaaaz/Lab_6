package org.example.commands;

import org.example.data.Messages;
import org.example.utils.LabWorkCollection;

import java.util.List;

/**
 * Команда для очистки коллекции объектов LabWork.
 */

public class ClearCommand extends Command {
    private final LabWorkCollection labWorkCollection;

    /**
     * Конструктор для команды очистки коллекции.
     *
     * @param labWorkCollection Коллекция объектов LabWork для очистки.
     */

    public ClearCommand(LabWorkCollection labWorkCollection) {
        this.labWorkCollection = labWorkCollection;
    }

    /**
     * Выполняет очистку коллекции объектов LabWork.
     *
     * @param args Список аргументов команды, не используется для данной команды.
     * @return Сообщение об успешной очистке коллекции.
     */

    @Override
    public String execute(List<Object> args) {
        labWorkCollection.clear();
        return Messages.LAB_WORK_SUCCESS_CLEAR.getMessage();
    }
}
