package org.example.commands;

import org.example.di.DIContainer;

import java.util.List;
import java.util.Map;

/**
 * Команда для вывода справки по доступным командам.
 */
public class HelpCommand extends Command {
    private final DIContainer diContainer;
    private final Map<String, String> commandDescriptions = Map.ofEntries(
            Map.entry("help", "help : print help for available commands"),
            Map.entry("info", "info : print information about the collection"),
            Map.entry("show", "show : print all elements of the collection"),
            Map.entry("add", "add : add a new element to the collection"),
            Map.entry("add_if_min", "add_if_min : add a new element to the collection if its value is less than the value of the largest element in this collection"),
            Map.entry("update", "update : update the value of a collection element"),
            Map.entry("remove_by_id", "remove_by_id : remove an element from the collection by its ID"),
            Map.entry("clear", "clear : remove all elements from the collection"),
            Map.entry("save", "save : save the collection to a file"),
            Map.entry("execute_script", "execute_script : read and execute a script from the specified file"),
            Map.entry("remove_greater", "remove_greater : remove the greater element from the collection"),
            Map.entry("remove_lower", "remove_lower : remove the lower element from the collection"),
            Map.entry("group_counting_by_discipline", "group_counting_by_discipline : group the elements of the collection by the value of the discipline field, display the number of elements in each group"),
            Map.entry("print_field_descending_discipline", "print_field_descending_discipline : print the value of the discipline field of all elements in descending order"),
            Map.entry("print_ascending", "print_ascending : print the value of the difficulty field of all elements in ascending order"),
            Map.entry("exit", "exit : exit the program")

    );

    /**
     * Конструктор команды Help.
     *
     * @param diContainer Контейнер зависимостей для доступа к списку команд.
     */
    public HelpCommand(DIContainer diContainer) {
        this.diContainer = diContainer;
    }

    /**
     * Выводит информацию о доступных командах.
     *
     * @return Справка по командам.
     */
    @Override
    public String execute(List<Object> args) {
        StringBuilder helpText = new StringBuilder("Available commands:\n");
        diContainer.getCommandNames().forEach(commandName -> {

            String description = commandDescriptions.getOrDefault(commandName, "Unknown command: " + commandName);
            helpText.append(description).append("\n");
        });
        return helpText.toString();
    }
}