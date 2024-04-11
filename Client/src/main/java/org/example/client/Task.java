package org.example.client;


import org.example.commandArguments.CommandData;
import org.example.commandArguments.Response;

/**
 * Задача для выполнения команды клиентом. Содержит данные команды и управляет ее отправкой и получением ответа.
 */

public class Task {
    private final CommandData commandData;

    public Task(CommandData commandData) {
        this.commandData = commandData;
    }

    /**
     * Выполняет задачу: отправляет данные команды на сервер и возвращает полученный ответ.
     *
     * @param clientManager Экземпляр ClientManager для отправки команды и получения ответа.
     * @return Ответ от сервера.
     */

    public Response execute(ClientManager clientManager) {
        clientManager.sendCommand(commandData);
        return clientManager.receiveResponse();
    }
}
