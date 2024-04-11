package org.example.commands;

import org.example.data.LabWork;
import org.example.utils.LabWorkCollection;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Команда для вывода элементов коллекции в порядке возрастания.
 */
public class PrintAscendingCommand extends Command {
    private final LabWorkCollection labWorkCollection;

    /**
     * Конструктор команды PrintAscending.
     *
     * @param labWorkCollection Коллекция для операции.
     */
    public PrintAscendingCommand(LabWorkCollection labWorkCollection) {
        this.labWorkCollection = labWorkCollection;
    }

    /**
     * Выводит элементы коллекции в порядке возрастания.
     *
     * @return Элементы коллекции в порядке возрастания.
     */
    @Override
    public String execute(List<Object> args) {
        return labWorkCollection.getLabWorks().stream()
                .sorted(Comparator.comparingInt(lw -> lw.getDiscipline().getPracticeHours()))
                .map(LabWork::detailedToString)
                .collect(Collectors.joining("\n"));
    }
}
