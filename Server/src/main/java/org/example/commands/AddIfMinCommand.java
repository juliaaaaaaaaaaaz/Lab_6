package org.example.commands;

import org.example.data.LabWork;
import org.example.utils.LabWorkCollection;

import java.util.Comparator;
import java.util.List;

/**
 * Команда добавления нового объекта LabWork в коллекцию, если его значение минимально.
 */

public class AddIfMinCommand extends Command {
    private final LabWorkCollection labWorkCollection;

    /**
     * Конструктор для команды добавления, если минимально.
     *
     * @param labWorkCollection Коллекция объектов LabWork для сравнения и добавления.
     */

    public AddIfMinCommand(LabWorkCollection labWorkCollection) {
        this.labWorkCollection = labWorkCollection;
    }

    /**
     * Выполняет добавление нового объекта LabWork, если его значение минимальное в коллекции.
     *
     * @param args Список аргументов команды. Ожидается один аргумент - объект LabWork.
     * @return Сообщение об успешном добавлении или не добавлении объекта.
     */

    @Override
    public String execute(List<Object> args) {
        LabWork newLabWork = (LabWork) args.get(0);
        // Используем Comparator для сравнения LabWork объектов по minimalPoint
        boolean isMin = labWorkCollection.getLabWorks().stream()
                .min(Comparator.comparing(LabWork::getMinimalPoint, Comparator.nullsLast(Comparator.naturalOrder())))
                .map(minLabWork -> newLabWork.getMinimalPoint() != null && (minLabWork.getMinimalPoint() == null || newLabWork.getMinimalPoint() < minLabWork.getMinimalPoint()))
                .orElse(true);

        if (isMin) {
            labWorkCollection.add(newLabWork);
            return "New LabWork added as it is the minimum.";
        } else {
            return "New LabWork is not the minimum. Not added.";
        }
    }
}
