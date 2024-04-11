package org.example.commands;

import org.example.commandArguments.CommandArgument;
import org.example.commandArguments.CommandData;
import org.example.data.Coordinates;
import org.example.data.Difficulty;
import org.example.data.Discipline;
import org.example.di.DIContainer;
import org.example.utils.CommandExecutor;
import org.example.data.LabWork;
import org.example.utils.IdGenerator;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 * Команда для выполнения скрипта, содержащего другие команды.
 */

public class ExecuteScriptCommand extends Command {
    private final DIContainer diContainer;
    private static final Set<String> scriptsBeingExecuted = new HashSet<>();

    /**
     * Конструктор для команды выполнения скрипта.
     *
     * @param diContainer Контейнер зависимостей для доступа к другим командам и сервисам.
     */

    public ExecuteScriptCommand(DIContainer diContainer) {
        this.diContainer = diContainer;
    }

    /**
     * Выполняет скрипт, содержащийся в переданном аргументе. Скрипт должен содержать строки с командами.
     * Предотвращает рекурсию при выполнении скриптов.
     *
     * @param args Список аргументов, где первый аргумент - строка, содержащая скрипт.
     * @return Результат выполнения скрипта в виде строки, содержащей результаты выполнения каждой команды.
     */

    @Override
    public String execute(List<Object> args) {
        String scriptContent = (String) args.get(0); // Содержимое скрипта передается напрямую
        if (scriptsBeingExecuted.contains(scriptContent)) {
            return "Recursion detected. Skipping script.";
        }

        try {
            scriptsBeingExecuted.add(scriptContent);
            Scanner scanner = new Scanner(scriptContent); // Используем сканер для разбора содержимого скрипта
            return executeScriptContent(scanner);
        } finally {
            scriptsBeingExecuted.remove(scriptContent);
        }
    }

    /**
     * Выполняет содержимое скрипта, разбирая каждую команду и выполняя её.
     *
     * @param scanner Scanner, инициализированный содержимым скрипта.
     * @return Строка с результатами выполнения команд скрипта.
     */

    private String executeScriptContent(Scanner scanner) {
        StringBuilder result = new StringBuilder();
        CommandExecutor commandExecutor = diContainer.getService(CommandExecutor.class);

        while (scanner.hasNextLine()) {
            String commandLine = scanner.nextLine().trim();
            if (commandLine.isEmpty() || commandLine.startsWith("#")) continue;

            CommandData commandData = interpretCommandLine(commandLine, scanner);
            String output = commandExecutor.executeCommand(commandData.commandName(), commandData.arguments());
            result.append(output).append("\n");
        }

        scanner.close();
        return result.toString();
    }

    /**
     * Интерпретирует строку команды из скрипта, создавая CommandData для последующего выполнения.
     *
     * @param commandLine Строка команды для интерпретации.
     * @param scanner     Scanner, используемый для чтения дополнительных данных команды из скрипта.
     * @return Объект CommandData, представляющий собой данные команды для выполнения.
     */

    private CommandData interpretCommandLine(String commandLine, Scanner scanner) {
        String[] parts = commandLine.split("\\s+");
        String commandName = parts[0].toLowerCase();
        List<CommandArgument> arguments = new ArrayList<>();
        LabWork labWork;
        String serializedLabWork;
        switch (commandName) {
            case "add":
            case "add_if_min":
            case "remove_lower":
            case "remove_greater":
                labWork = readLabWorkFromScript(scanner);
                serializedLabWork = serializeLabWork(labWork);
                arguments.add(new CommandArgument("labWork", "LabWork", serializedLabWork));
                break;
            case "update":
                if (parts.length > 1) {
                    long id = Long.parseLong(parts[1]); // Получаем ID прямо из аргументов команды
                    labWork = readLabWorkFromScript(scanner); // Считываем данные для LabWork из последующих строк
                    serializedLabWork = serializeLabWork(labWork);
                    arguments.add(new CommandArgument("id", "Long", String.valueOf(id)));
                    arguments.add(new CommandArgument("labWork", "LabWork", serializedLabWork));
                }
                break;
            case "remove_by_id":
                if (parts.length > 1) {
                    long id = Long.parseLong(parts[1]); // Аналогично получаем ID для remove_by_id
                    arguments.add(new CommandArgument("id", "Long", String.valueOf(id)));
                }
                break;
        }

        return new CommandData(commandName, arguments);
    }


    /**
     * Читает данные для создания объекта LabWork из скрипта.
     *
     * @param scanner Scanner, используемый для чтения данных объекта LabWork из скрипта.
     * @return Объект LabWork, созданный из прочитанных данных.
     */

    private LabWork readLabWorkFromScript(Scanner scanner) {
        String name = scanner.nextLine().trim();
        float x = Float.parseFloat(scanner.nextLine().trim());
        double y = Double.parseDouble(scanner.nextLine().trim());
        Coordinates coordinates = new Coordinates();
        coordinates.setX(x);
        coordinates.setY(y);
        long minimalPoint = Long.parseLong(scanner.nextLine().trim());
        long maximumPoint = Long.parseLong(scanner.nextLine().trim());
        Difficulty difficulty = Difficulty.valueOf(scanner.nextLine().trim().toUpperCase());
        String disciplineName = scanner.nextLine().trim();
        int practiceHours = Integer.parseInt(scanner.nextLine().trim());
        int selfStudyHours = Integer.parseInt(scanner.nextLine().trim());
        Discipline discipline = new Discipline();
        discipline.setName(disciplineName);
        discipline.setPracticeHours(practiceHours);
        discipline.setSelfStudyHours(selfStudyHours);


        return new LabWork(IdGenerator.generateUniqueId(), name, coordinates, new Date(), minimalPoint, maximumPoint, difficulty, discipline);
    }


    /**
     * Сериализует объект LabWork в строку, используя Base64 кодирование.
     *
     * @param labWork Объект LabWork для сериализации.
     * @return Строка, представляющая сериализованный объект LabWork, закодированный в Base64.
     * @throws RuntimeException Если происходит ошибка сериализации.
     */

    private String serializeLabWork(LabWork labWork) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(labWork);
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize LabWork", e);
        }
    }
}
