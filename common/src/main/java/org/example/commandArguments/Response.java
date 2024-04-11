package org.example.commandArguments;


import lombok.Getter;

import java.io.Serializable;

/**
 * Представляет ответ сервера, содержащий информацию об успешности выполнения команды и сообщение.
 */


public record Response(boolean success, String message) implements Serializable {
    /**
     * Конструктор для создания ответа сервера.
     *
     * @param success Успешно ли была выполнена команда.
     * @param message Сообщение, связанное с выполнением команды.
     */

    public Response {
    }
}
