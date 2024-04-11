package org.example.commands;

import org.example.data.LabWork;
import org.example.data.Messages;
import org.example.utils.LabWorkCollection;

import java.util.List;


/**
 * Команда для удаления из коллекции всех элементов, превышающих заданный.
 */
public class RemoveGreaterCommand extends Command {
    private final LabWorkCollection labWorkCollection;

    /**
     * Конструктор команды RemoveGreater.
     *
     * @param labWorkCollection Коллекция, из которой будут удаляться элементы.
     */
    public RemoveGreaterCommand(LabWorkCollection labWorkCollection) {
        this.labWorkCollection = labWorkCollection;
    }

    /**
     * Выполняет удаление всех элементов, превышающих указанный.
     *
     * @return Сообщение о количестве удаленных элементов.
     */
    @Override
    public String execute(List<Object> args) {
        LabWork comparisonLabWork = (LabWork) args.get(0);
        int removed = labWorkCollection.removeIf(labWork ->
                labWork.getDiscipline().getPracticeHours() > comparisonLabWork.getDiscipline().getPracticeHours());

        return String.format(Messages.REMOVED_D_ELEMENTS.getMessage(), removed);
    }
}
