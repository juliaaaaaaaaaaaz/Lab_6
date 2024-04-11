package org.example.data;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Представление LabWork.
 */
@Setter
@Getter
public class LabWork implements Serializable {


    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long minimalPoint; //Поле может быть null, Значение поля должно быть больше 0
    private long maximumPoint; //Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле не может быть null
    private Discipline discipline; //Поле может быть null

    public LabWork(long id, String name, Coordinates coordinates, Date creationDate, Long minimalPoint, long maximumPoint, Difficulty difficulty, Discipline discipline) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.minimalPoint = minimalPoint;
        this.maximumPoint = maximumPoint;
        this.difficulty = difficulty;
        this.discipline = discipline;
    }

    public String detailedToString() {
        return "LabWork{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", minimalPoint=" + minimalPoint +
                ", maximumPoint=" + maximumPoint +
                ", difficulty=" + difficulty +
                ", discipline=" + discipline +
                '}';
    }
}