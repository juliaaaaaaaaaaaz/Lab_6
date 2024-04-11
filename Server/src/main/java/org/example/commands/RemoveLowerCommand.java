package org.example.commands;

import org.example.data.LabWork;
import org.example.data.Messages;
import org.example.utils.LabWorkCollection;

import java.util.List;


/**
 * Команда для удаления всех элементов из коллекции, которые меньше заданного.
 */
public class RemoveLowerCommand extends Command {
    private final LabWorkCollection labWorkCollection;

    /**
     * Конструктор команды RemoveLower.
     *
     * @param labWorkCollection Коллекция, из которой будут удаляться элементы.
     */
    public RemoveLowerCommand(LabWorkCollection labWorkCollection) {
        this.labWorkCollection = labWorkCollection;
    }

    /**
     * Удаляет из коллекции все элементы, которые меньше заданного.
     *
     * @return Сообщение о количестве удаленных элементов.
     */
    @Override
    public String execute(List<Object> args) {
        LabWork comparisonLabWork = (LabWork) args.get(0);


        int removedCount = labWorkCollection.removeIf(labWork ->
                labWork.getMinimalPoint() != null &&
                        comparisonLabWork.getMinimalPoint() != null &&
                        labWork.getMinimalPoint() < comparisonLabWork.getMinimalPoint()
        );

        return Messages.REMOVED.getMessage() + removedCount + Messages.ELEMENTS_LOWER.getMessage();
    }
}
