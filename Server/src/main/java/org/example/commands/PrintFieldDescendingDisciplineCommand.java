package org.example.commands;

import org.example.data.LabWork;
import org.example.utils.LabWorkCollection;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Команда для вывода элементов коллекции по убыванию значения поля дисциплины.
 */
public class PrintFieldDescendingDisciplineCommand extends Command {
    private final LabWorkCollection labWorkCollection;

    /**
     * Конструктор команды PrintFieldDescendingDiscipline.
     *
     * @param labWorkCollection Коллекция, над которой проводится операция.
     */
    public PrintFieldDescendingDisciplineCommand(LabWorkCollection labWorkCollection) {
        this.labWorkCollection = labWorkCollection;
    }

    /**
     * Выводит элементы коллекции в порядке убывания значений поля дисциплины.
     *
     * @return Отсортированные элементы коллекции.
     */
    @Override
    public String execute(List<Object> args) {
        return labWorkCollection.getLabWorks().stream()
                .filter(labWork -> labWork.getDiscipline() != null)
                .sorted(Comparator.comparingInt((LabWork lw) -> lw.getDiscipline().getPracticeHours()).reversed())
                .map(lw -> lw.getDiscipline().getName() + " - Practice hours: " + lw.getDiscipline().getPracticeHours())
                .collect(Collectors.joining("\n"));
    }
}
