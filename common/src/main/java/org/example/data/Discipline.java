package org.example.data;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Представление дисциплины для элемента LabWork.
 */
@Setter
@Getter
public class Discipline implements Serializable {

    private String name; //Поле не может быть null, Строка не может быть пустой
    private int practiceHours;
    private int selfStudyHours;

    @Override
    public String toString() {
        return "Discipline{" +
                "name='" + name + '\'' +
                ", practiceHours=" + practiceHours +
                ", selfStudyHours=" + selfStudyHours +
                '}';
    }
}