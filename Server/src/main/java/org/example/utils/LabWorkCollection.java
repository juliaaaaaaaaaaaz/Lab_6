package org.example.utils;

import com.google.gson.reflect.TypeToken;
import org.example.data.LabWork;
import org.example.data.Messages;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Управляет коллекцией объектов LabWork, включая загрузку из файла и сохранение в файл.
 */
public class LabWorkCollection {
    private final String fileName;
    private Set<LabWork> labWorkSet;
    private final LocalDateTime initializationDate;

    /**
     * Конструктор коллекции LabWork.
     *
     * @param fileName Имя файла для загрузки и сохранения коллекции.
     */
    public LabWorkCollection(String fileName) {
        this.fileName = fileName;
        this.labWorkSet = new LinkedHashSet<>();
        this.initializationDate = LocalDateTime.now();
        System.err.println("LabWorkCollection created");
        loadFromFile();
    }

    /**
     * Загружает коллекцию из файла, используя утилиту JsonUtil.
     */
    private void loadFromFile() {
        Type collectionType = new TypeToken<Set<LabWork>>() {
        }.getType();
        labWorkSet = JsonUtil.loadFromFile(fileName, collectionType);
        if (labWorkSet == null) {
            labWorkSet = new LinkedHashSet<>();
            System.out.println(Messages.STARTING_WITH_EMPTY_COLLECTION.getMessage());
        }
    }

    /**
     * Сохраняет текущее состояние коллекции в файл.
     */
    public void saveToFile() {
        JsonUtil.saveToFile(labWorkSet, fileName);
    }

    /**
     * Добавляет новый объект LabWork в коллекцию.
     *
     * @param labWork Объект LabWork для добавления.
     */
    public void add(LabWork labWork) {
        // Добавление нового элемента
        labWorkSet.add(labWork);
    }

    /**
     * Удаляет объект из коллекции по его ID.
     *
     * @param id ID объекта для удаления.
     * @return true, если объект был удален.
     */
    public boolean removeById(long id) {
        return labWorkSet.removeIf(labWork -> labWork.getId() == id);
    }

    /**
     * Очищает коллекцию от всех элементов.
     */
    public void clear() {
        labWorkSet.clear();
    }

    /**
     * Возвращает строковое представление всех объектов коллекции.
     *
     * @return Строка с данными всех объектов коллекции.
     */
    public String show() {
        return labWorkSet.stream()
                .map(LabWork::detailedToString)
                .collect(Collectors.joining("\n\n"));
    }

    /**
     * Возвращает информацию о коллекции.
     *
     * @return Строка с информацией о типе, дате инициализации и размере коллекции.
     */
    public String getInfo() {
        return String.format("Collection type: %s\nInitialization date: %s\nNumber of elements: %d",
                labWorkSet.getClass().getSimpleName(), initializationDate, labWorkSet.size());
    }

    public Set<LabWork> getLabWorks() {
        return new LinkedHashSet<>(labWorkSet);
    }

    public LabWork findById(long id) {
        return labWorkSet.stream()
                .filter(labWork -> labWork.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Обновляет объект в коллекции с указанным ID на новое значение.
     *
     * @param id         ID объекта для обновления.
     * @param newLabWork Новое значение объекта LabWork.
     * @return true, если обновление прошло успешно.
     */
    public boolean update(long id, LabWork newLabWork) {
        LabWork existingLabWork = findById(id);
        if (existingLabWork != null) {
            existingLabWork.setName(newLabWork.getName());
            existingLabWork.setCoordinates(newLabWork.getCoordinates());
            existingLabWork.setMinimalPoint(newLabWork.getMinimalPoint());
            existingLabWork.setMaximumPoint(newLabWork.getMaximumPoint());
            existingLabWork.setDifficulty(newLabWork.getDifficulty());
            existingLabWork.setDiscipline(newLabWork.getDiscipline());
            return true;
        }
        return false;
    }

    /**
     * Удаляет элементы, удовлетворяющие заданному условию.
     *
     * @param filter Предикат, определяющий условие удаления.
     * @return Количество удаленных элементов.
     */
    public int removeIf(Predicate<LabWork> filter) {
        int initialSize = labWorkSet.size();
        labWorkSet.removeIf(filter);
        return initialSize - labWorkSet.size();
    }
}

