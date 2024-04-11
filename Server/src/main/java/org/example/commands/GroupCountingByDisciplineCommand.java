package org.example.commands;


import org.example.utils.LabWorkCollection;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Команда для группировки объектов LabWork по дисциплинам и подсчета их количества в каждой группе.
 */

public class GroupCountingByDisciplineCommand extends Command {
    private final LabWorkCollection labWorkCollection;

    /**
     * Конструктор для команды группировки и подсчета объектов LabWork по дисциплинам.
     *
     * @param labWorkCollection Коллекция объектов LabWork для обработки.
     */

    public GroupCountingByDisciplineCommand(LabWorkCollection labWorkCollection) {
        this.labWorkCollection = labWorkCollection;
    }

    /**
     * Выполняет группировку и подсчет объектов LabWork по дисциплинам.
     *
     * @param args Список аргументов команды, не используется для данной команды.
     * @return Строковое представление результата группировки и подсчета.
     */

    @Override
    public String execute(List<Object> args) {
        Map<String, Long> counting = labWorkCollection.getLabWorks().stream()
                .collect(Collectors.groupingBy(labWork -> labWork.getDiscipline().getName(), Collectors.counting()));

        return counting.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining("\n"));
    }
}
