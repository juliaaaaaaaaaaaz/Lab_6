package org.example.commandArguments;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Представляет аргумент команды, содержащий имя, тип и значение.
 */

@Getter
@Setter
public class CommandArgument implements Serializable {
    private String name;
    private String type;
    private String value;

    /**
     * Конструктор для создания аргумента команды.
     *
     * @param name  Имя аргумента.
     * @param type  Тип аргумента.
     * @param value Значение аргумента.
     */

    public CommandArgument(String name, String type, String value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }
}
