package org.example;

import org.example.client.ClientManager;
import org.example.client.CommandInterpreter;
import org.example.client.Task;
import org.example.commandArguments.CommandData;
import org.example.commandArguments.Response;
import org.example.data.Messages;
import org.example.utils.LabWorkReader;
import org.example.utils.Validator;

import java.util.Scanner;

/**
 * Главный класс клиентского приложения. Запускает клиента и обрабатывает пользовательский ввод.
 */

public class Main {
    /**
     * Точка входа клиентского приложения. Инициализирует необходимые компоненты и обрабатывает ввод пользователя.
     *
     * @param args Аргументы командной строки. В этом приложении не используются.
     */
    public static void main(String[] args) {
        Validator validator = new Validator();
        LabWorkReader labWorkReader = new LabWorkReader(validator);
        CommandInterpreter commandInterpreter = new CommandInterpreter(labWorkReader);

        System.out.println(Messages.WELCOME.getMessage());
        System.out.println(Messages.ENTER_HELP.getMessage());

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if ("exit".equalsIgnoreCase(input)) {
                break;
            }

            try {
                CommandData commandData = commandInterpreter.interpret(input, scanner);

                ClientManager clientManager = new ClientManager("localhost", 12345);
                clientManager.connect();

                Task task = new Task(commandData);
                Response response = task.execute(clientManager);
                System.out.println(response.message());

                clientManager.disconnect();
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
}